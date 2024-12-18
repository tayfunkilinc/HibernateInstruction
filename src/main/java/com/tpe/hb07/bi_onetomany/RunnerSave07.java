package com.tpe.hb07.bi_onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave07 {
    public static void main(String[] args) {

        Student07 student1=new Student07(1001,"Jack",99);
        Student07 student2=new Student07(1002,"Harry",89);
        Student07 student3=new Student07(1003,"Fred",95);

        Book07 book1=new Book07(101,"Sefiller");
        Book07 book2=new Book07(102,"Tutunamayanlar");
        Book07 book3=new Book07(103,"Suç Ve Ceza");
        Book07 book4=new Book07(104,"Aşk Ve Gurur");

        //iliskinin kuruldugu tarafta yani sahibi olan tarafta set islemi yapilmali
        book1.setStudent(student1);
        book2.setStudent(student1);
        book3.setStudent(student2);
        book4.setStudent(student2);
        //student1.getBookList().add(); //-->mappedBy ile otomatik eslestirilir bu durumda bunu yapmaamiza gerek yok


        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student07.class).addAnnotatedClass(Book07.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();
        Transaction transaction=session.beginTransaction();

        session.save(student1);
        session.save(student2);
        session.save(student3);

        session.save(book1);
        session.save(book2);
        session.save(book3);
        session.save(book4);


        transaction.commit();
        session.close();
        sf.close();
    }
}
