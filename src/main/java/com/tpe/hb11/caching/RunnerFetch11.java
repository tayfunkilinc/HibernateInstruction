package com.tpe.hb11.caching;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
1)  First Level Cache --->
            * nesne için kullanılır
            * defaultta açık geliyor , kapatma durumu yok
            * Aynı session içinde kayıt alır
            * session kapanınca içindekiler silinir

2) Second Level Cache ---> bunu kullanmak icin depedndency kismina ve hibernate.cfg.xml sayfalarina ekleme yapilacak
            * nesne için kullanılır
            * Defaultta kapalıdır
            * Session factory seviyesinde cacheleme yapar, yani farklı
                    sessionlar arasında data kullanılabiliyor
            * hibernate.cfg.xml den active edilebilir
            *aynı data aynı sessionda first level cacheden gelir,
             aynı data farklı sessionda second level cacheden gelir.


3) Query Cache
            * Query ler için kullanılıyor
            * hibernate.cfg.xml den active edilebilir
            * first/second level cache ile kullanılabilir
            * aynı sorgunun sonucu cache e alınır.
 */

public class RunnerFetch11 {
    public static void main(String[] args) {



        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student11.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();

        System.out.println("***************************** 1 idli ogrenci icin ilk get islemi");
        Student11 student = session.get(Student11.class, 1);
        System.out.println(student);

        session.clear(); // cache'i temizler

        System.out.println("***************************** 1 idli ogrenci icin ikinci kez get islemi");
        Student11 student2 = session.get(Student11.class, 1);
        System.out.println(student2);

        session.close(); //cache silindi
        //first level cache silindi bundan sonra goremeyecegiz

        System.out.println("********************* session kapandi");
        //yeni session actik : session2
        Session session2 = sf.openSession();
        System.out.println("***************************** 1 idli ogrenci icin farkli sesionda get islemi");
        Student11 student3 = session2.get(Student11.class, 1);
        System.out.println(student3);

       /* System.out.println("***************************** 1 idli ogrenci icin farkli sesionda 2.kez get islemi");
        Student11 student4 = session2.get(Student11.class, 1);
        System.out.println(student4); // burdada ayni session icerisinde oldugu icin on bellekten cekti DB'ye gitmedi*/


        session2.close();



        sf.close();
    }
}
