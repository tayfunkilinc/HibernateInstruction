package com.tpe.hb13.entityLifeCycle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Runner13 {
    public static void main(String[] args) {
        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student13.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();
        Transaction transaction=session.beginTransaction();

        Student13 student = session.get(Student13.class,1); //persisted :Hibernate tarafindan takip ediliyor
        student.setName("Mehmet"); //1,Mehmet,99

        session.delete(student); // removed: DB de karsiligi yok


        transaction.commit();
        session.close();
        sf.close();
    }
}
