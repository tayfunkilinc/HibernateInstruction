package com.tpe.hb03.uni_onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RunnerFetch03 {
    public static void main(String[] args) {
        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student03.class).addAnnotatedClass(Diary.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();

        //id:1001 olan ogrenciyi getirelim
        Student03 student= session.get(Student03.class, 1001);

        //id:11 olan gunlugu getirelim
        Diary diary = session.get(Diary.class,11);

        System.out.println("*********************************************");
        System.out.println(student);
        System.out.println("*********************************************");
        System.out.println(diary);
        System.out.println("*********************************************");

        //------------gunluk kime ait - tekrar DB'e gitmeye gerek yok bize ogrenciyide yaninda getiriyor
        System.out.println(diary.getStudent()); // burda sorgu gondermeden dogurudan kime ait oldugunu bulabiliyoruz

        //id: 1002 olan ogrencinin gunlugu hangisidir
        Student03 student2 = session.get(Student03.class, 1002);
        //student2.getDiary() - burda ulasamadim field olmadigi icin -> java kodlari ile ulasamiyoruz fakat DB'den ulasabilirim
        String diaryName = (String) session.createSQLQuery("SELECT name FROM diary WHERE std_id=1002").uniqueResult();
        System.out.println("*********************************************");
        System.out.println(diaryName);

        //PROBLEM: sorgu yazmadan diaryden studenta , studentdan diarye ulaşmak istersem?
        //uni_directional (tek yonlu) : diary'den student'a ulasilir ama ttam tersi olmaz
        //bi_directional (cift yonlu) : diary <-> Student cift yonlu ersilebilir (veri tabaninda birsey degismez)



        session.close();
        sf.close();
    }
}
