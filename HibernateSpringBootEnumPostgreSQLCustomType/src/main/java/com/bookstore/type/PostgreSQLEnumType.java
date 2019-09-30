package com.bookstore.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

public class PostgreSQLEnumType extends EnumType {

    @Override
    public void nullSafeSet(PreparedStatement ps, Object obj, int index,
            SharedSessionContractImplementor session) throws HibernateException, SQLException {        
        if (obj == null) {
            ps.setNull(index, Types.OTHER);
        } else {             
            ps.setObject(index, obj.toString(), Types.OTHER);
        }
    }
}
