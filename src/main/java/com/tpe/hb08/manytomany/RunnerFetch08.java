package com.tpe.hb08.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch08 {
    public static void main(String[] args) {
        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student08.class).addAnnotatedClass(Course.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();
        Transaction transaction=session.beginTransaction();

        //id:1001 ogrenciyi gorelim
        Student08 student = session.get(Student08.class, 1001);
        System.out.println(student);
        System.out.println(student.getCourseList());

        System.out.println("---------------------------------------------------------");
        //id:11 olan course gorelim
        Course course = session.get(Course.class, 11);
        System.out.println(course);


        transaction.commit();
        session.close();
        sf.close();
    }
}
