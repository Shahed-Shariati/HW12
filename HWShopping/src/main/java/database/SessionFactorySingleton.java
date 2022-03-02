package database;

import model.Customer;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public  class SessionFactorySingleton {
 private SessionFactorySingleton(){

 }


 public static class LazyHolder{

     private static SessionFactory sessionFactory;
     static {

         ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                 .configure()
                 .build();

         sessionFactory = new MetadataSources(serviceRegistry)
                 .addAnnotatedClass(Customer.class)

                 .buildMetadata()
                 .buildSessionFactory();

     }
 }
 public static SessionFactory getSessionFactory(){
     return LazyHolder.sessionFactory;
 }
}
