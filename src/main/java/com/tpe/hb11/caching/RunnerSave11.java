package com.tpe.hb11.caching;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave11 {
    public static void main(String[] args) {

        Student11 student1=new Student11("Ali",99);
        Student11 student2=new Student11("Veli",100);
        Student11 student3=new Student11("Can",98);
        Student11 student4=new Student11("Deniz",89);


        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student11.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();
        Transaction transaction=session.beginTransaction();

        session.save(student1);
        session.save(student2);
        session.save(student3);
        session.save(student4);
        // session.persist(); ---> save ile ayni isleve sahip -- save methodu depricate oldu bundan sonra hibernate 5 den sonra persist() kullanilacak



        transaction.commit();
        session.close();
        sf.close();
    }
}
