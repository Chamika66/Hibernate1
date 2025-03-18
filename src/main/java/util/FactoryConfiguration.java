package util;

import entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration instance;
    private SessionFactory factory;

    private FactoryConfiguration(){
        Configuration configure = new Configuration().configure().addAnnotatedClass(StudentEntity.class);
        factory=configure.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return instance==null ? instance=new FactoryConfiguration() : instance;
    }

    public Session getSession(){
        return factory.openSession();
    }
}
