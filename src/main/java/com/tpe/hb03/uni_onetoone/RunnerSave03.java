package com.tpe.hb03.uni_onetoone;

import com.tpe.hb02.embeddable.Student02;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave03 {
    public static void main(String[] args) {

        Student03 student1 = new Student03(1001, "Ali", 99);
        Student03 student2=new Student03(1002,"Ayşe",98);
        Student03 student3=new Student03(1003,"Fatma",99);

        Diary diary1 = new Diary();
        diary1.setId(11);
        diary1.setName("X");

        Diary diary2 = new Diary();
        diary2.setId(22);
        diary2.setName("Y");

        Diary diary3 = new Diary();
        diary3.setId(33);
        diary3.setName("Z");

        //---------------------------------------------------
        //burda sadece one to one iliski kuruldu ama FK sutunu doldurulmadi onuda biz yapiyoruz
        //hangi ogrenciye hangi gunluk denk gelecek bunu bilmedigi icin biz ogrencileri tek tek isledik
        diary1.setStudent(student1);// diary1 ile student1 'i iliskilendirmis olduk
        diary2.setStudent(student2);
        diary3.setStudent(student3);
        //yani !! FK sutunune studentlarin idlerini eklemis olduk DIKKAT:unique olmali (tekrarsiz olmali cunku one-to-one)
        //NOT: FK sutunu null olabilir atamada yapilmayabilir


        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student03.class).addAnnotatedClass(Diary.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(student1);
        session.save(student2);
        session.save(student3);

        session.save(diary1);
        session.save(diary2);
        session.save(diary3);

        transaction.commit();
        session.close();
        sf.close();
    }
}
