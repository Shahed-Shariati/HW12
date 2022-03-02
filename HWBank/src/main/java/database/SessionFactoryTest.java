package database;

import model.Account;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryTest {

    public static class LazyHolder{
       private static SessionFactory INSTANCE;
       static {

           ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                   .configure("hibernatetest.cfg.xml")
                   .build();
           INSTANCE = new MetadataSources(serviceRegistry)
                   .addAnnotatedClass(Account.class)
                   .buildMetadata()
                   .buildSessionFactory();
       }
    }

    public static SessionFactory getInstance(){
        return LazyHolder.INSTANCE;
    }
}
