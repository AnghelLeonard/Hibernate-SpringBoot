package com.bookstore.config;

import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.logging.Logger;

// this component is needed just for monitoring batching
@Component
public class DatasourceProxyBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger
            = Logger.getLogger(DatasourceProxyBeanPostProcessor.class.getName());

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        if (bean instanceof DataSource) {

            logger.info(() -> "DataSource bean has been found: " + bean);

            final ProxyFactory proxyFactory = new ProxyFactory(bean);

            proxyFactory.setProxyTargetClass(true);
            proxyFactory.addAdvice(new ProxyDataSourceInterceptor((DataSource) bean));

            return proxyFactory.getProxy();
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    private static class ProxyDataSourceInterceptor implements MethodInterceptor {

        private final DataSource dataSource;

        public ProxyDataSourceInterceptor(final DataSource dataSource) {
            super();
            this.dataSource = ProxyDataSourceBuilder.create(dataSource)
                    .name("DATA_SOURCE_PROXY")
                    .logQueryBySlf4j(SLF4JLogLevel.INFO)
                    .multiline()
                    .build();
        }

        @Override
        public Object invoke(final MethodInvocation invocation) throws Throwable {

            final Method proxyMethod = ReflectionUtils.
                    findMethod(this.dataSource.getClass(),
                            invocation.getMethod().getName());

            if (proxyMethod != null) {
                return proxyMethod.invoke(this.dataSource, invocation.getArguments());
            }

            return invocation.proceed();
        }
    }
}