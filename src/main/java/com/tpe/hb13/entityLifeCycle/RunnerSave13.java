package com.tpe.hb13.entityLifeCycle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave13 {
    public static void main(String[] args) {

        Student13 student1 = new Student13("Jack",99); //Transient durumunda : DB'de karsiligi yok
        Student13 student2 = new Student13("Harry",99);

        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student13.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();
        Transaction transaction=session.beginTransaction();

        session.save(student1); //-->persisted/manged durumunda : DB'ye kayit yapildi. hibernate tarafindan sesion'dan takip ediliyor
        student1.setName("Sherlock");

        session.save(student2); //persisted
        student2.setGrade(111);//

        session.evict(student2);// entityi sesiondan detach eder
        student2.setName("Ali");

        session.update(student2); //persist: detach duruma cekilenler update ile db'ye yansimis olur

        transaction.commit();
        session.close();

        student1.setName("Ahmet"); //detached durumunda : artik cache'den ayrilmistir bundan sonra takip edemez : db'de bir satira karsilik gelir

        Session session2=sf.openSession();
        Transaction transaction2=session2.beginTransaction();

        session2.update(student1); //burda entitynin yeniden sesiona baglanmasini saglar : persisted edildi
                                    //DB deki karsiligini gunceller
        student1.setGrade(10);
        //3--> Ahmet,10

        transaction2.commit();
        session2.close();



        sf.close();
    }
}
