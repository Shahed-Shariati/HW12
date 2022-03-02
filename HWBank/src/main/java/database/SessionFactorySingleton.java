package database;

import model.Account;
import org.hibernate.SessionFactory;


import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class SessionFactorySingleton {

    private SessionFactorySingleton() {
    }

    public static class LazyHolder{
        static SessionFactory INSTANCE;


        static {
            ServiceRegistry registryBuilder =  new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            INSTANCE = new MetadataSources( registryBuilder)
                    .addAnnotatedClass(Account.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }
    public static SessionFactory getSessionFactory(){
        return LazyHolder.INSTANCE;
    }
}
