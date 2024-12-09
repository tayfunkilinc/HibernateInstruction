package com.tpe.hb01.basicannotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class RunnerSave01 {
    public static void main(String[] args) {
        Configuration config=new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Student.class);

        SessionFactory sessionFactory =config.buildSessionFactory();

        Session session =sessionFactory.openSession();
    }
}
