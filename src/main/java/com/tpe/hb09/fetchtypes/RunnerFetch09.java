package com.tpe.hb09.fetchtypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RunnerFetch09 {
    public static void main(String[] args) {
        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student09.class).addAnnotatedClass(Book09.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();

        //student getirelim: LAZY (onetomany oldugu icin)
        Student09 student = session.get(Student09.class, 1001);  //burda sadece ogrenciyi getirir
        System.out.println(student.getBookList()); // burdada sadece booklari getirdi LAZY oldugu icin

        //book getirelim:EAGER (manytoone oldugu icin)
        Book09 book = session.get(Book09.class, 33);
        System.out.println(book.getStudent());


        session.close();
        sf.close();

        //System.out.println(student.getBookList()); // LAZY oldugu icin burda DB kapandigi icin tekrar gitmesi gerekli
        //System.out.println(book.getStudent()); //burda yazdirabiliyorum cunku EAGER oldugu icin DB'den tamamini geitrmisti DB'ye gitmesine gerek yok

    }
}
