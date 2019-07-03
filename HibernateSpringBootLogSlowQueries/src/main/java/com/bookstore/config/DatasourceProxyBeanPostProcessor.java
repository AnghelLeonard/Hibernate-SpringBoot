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
import java.util.List;
import java.util.logging.Logger;
import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;

@Component
public class DatasourceProxyBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger
            = Logger.getLogger(DatasourceProxyBeanPostProcessor.class.getName());
    private static final long THRESHOLD_MILLIS = 30;

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

            SLF4JQueryLoggingListener listener = new SLF4JQueryLoggingListener() {

                @Override
                public void afterQuery(ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
                    // call query logging logic only when it took more than threshold
                    if (THRESHOLD_MILLIS <= execInfo.getElapsedTime()) {
                        logger.info("Slow SQL detected ...");
                        super.afterQuery(execInfo, queryInfoList);
                    }

                }
            };
            
            listener.setLogLevel(SLF4JLogLevel.WARN);

            this.dataSource = ProxyDataSourceBuilder.create(dataSource)
                    .name("DATA_SOURCE_PROXY")                    
                    .multiline()
                    .listener(listener)
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
