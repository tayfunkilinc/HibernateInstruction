package com.tpe.hb07.bi_onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch007 {
    public static void main(String[] args) {
        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student07.class).addAnnotatedClass(Book07.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();

        //db de değişikliklerin kalıcı olması için transaction gereklidir
        Transaction t =session.beginTransaction();


        // !!! Kitab bilgisi(iliskisi olan) olan bir ogrenciyi silmek istersek
        // 1.yol ) once Book tablosunda iliskili oldugu booklar silinir daha sonra istenen student objesi silinebilir
        // 2.yol ) CascadeType.REMOVE / orphanRemoval

        //id:1002 olan öğrenciyi silelim //cascade = CascadeType.REMOVE*/ /*2.yol:*/orphanRemoval = true bu ikiside ayni islevi yapar
        /*Student07 student = session.get(Student07.class, 1002);
        session.delete(student);*/

        // not: orphanRemoval sadece onetomany'de kullanilir'


        //id:1001 olan öğrencinin kitap listesinden ilkini silelim
        Student07 student2 = session.get(Student07.class, 1001);
        student2.getBookList().remove(0); // 1001: 101,102-->102 kalmis oldu
        //2.yol: student2.getBookList().set(0,null); //1001: 102-> null//bununlada bulunan indexi null yapmak iliskiyide koparmis oluyor
        //collectiondan bir eleman silinir veya null yapılırsa
        // referansı olmayan bu nesneyi tablodan da siler

        //--------------------------------
        //1-std-book-->std kitabı iade etti-->listeden kaldırdık-->tablodan silmemeliyiz-orphanRemoval:false
        //2-müşteri-sipariş-->sipariş listesi(1,2,3)
        //                 -->siparişi iptal(1)-->sipariş listesi(2,3)
        //müşterinin oluşturduğu siparişi tabloda tutmaya gerek var mı--orphanRemoval:true
        //-------------------





        t.commit();
        session.close();
        sf.close();
    }
}
