package com.tpe.hb01.basicannotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RunnerOdev01 {
    public static void main(String[] args) {
        //practice:HQL ile
        //1-ismi Harry Potter olan öğrencileri getirelim
        //2-tüm öğrencilerin sadece isimlerini getirelim
        //SQL ile
        //1-tüm öğrencilerin sadece isimlerini getirelim

        Configuration config = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class);
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        System.out.println("-------------------1---------------------");
        //practice:HQL ile
        //1-ismi Harry Potter olan öğrencileri getirelim
        String hql3="SELECT s FROM Student s WHERE s.name='Harry Potter'";
        //burda * kullanamadigimiz icin elyis  yani degisken kullanamaliyis Student s seklinde
        List<Student> resultList2=session.createQuery(hql3, Student.class).getResultList(); // gecek olan datanin tipi belli oldugu icin Student.class tipini belirttik
        for (Student s:resultList2){
            System.out.println(s);
        }

        System.out.println("-------------------2---------------------");
        //2-tüm öğrencilerin sadece isimlerini getirelim
        String hql4="SELECT name FROM Student";
        List<String> resultList3=session.createQuery(hql4, String.class).getResultList();
        for (String s:resultList3){
            System.out.println(s);
        }
        System.out.println("-------------------3---------------------");
        //SQL ile
        //3-tüm öğrencilerin sadece isimlerini getirelim
        String sql3="SELECT student_name FROM t_student";
        List<String> isimler=session.createSQLQuery(sql3).getResultList();
        for (String s:isimler){
            System.out.println(s);
        }

        session.close();
        sessionFactory.close();
    }
}
