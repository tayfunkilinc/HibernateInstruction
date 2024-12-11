package com.tpe.hb01.basicannotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


/*
Configuration, Hibernate'in başlangıç aşamasında kullanılır
ve Hibernate'in çalışması için gerekli ayarların (örneğin, veritabanı bağlantısı)
 yapılmasını sağlar. Hibernate ile çalışırken tipik olarak bir Configuration
  nesnesi oluşturulur, ardından bu nesne kullanılarak bir SessionFactory oluşturulur.

SessionFactory, Hibernate'in temel bileşenlerinden biridir
Veritabanı işlemleri gerçekleştirmek için Session nesneleri üretir.
Uygulama boyunca genellikle bir kez oluşturulur ve tüm uygulama tarafından paylaşılır.

Session, Hibernate ile veritabanı arasında bağlantıyı sağlar.
Veritabanı üzerinde Create, Read, Update, Delete (CRUD) işlemlerini gerçekleştirir.
Her işlem için yeni bir Session oluşturulması önerilir.
 */

public class RunnerSave01 {
    public static void main(String[] args) {

        Student student1 = new Student();
        student1.setId(1001);
        student1.setName("Jack Sparrow");
        student1.setGrade(99);

        Student student2 = new Student();
        student2.setId(1002);
        student2.setName("Herry Poter");
        student2.setGrade(98);

        Student student3 = new Student();
        student3.setId(1003);
        student3.setName("Sherlock Holmes");
        student3.setGrade(98);


        //configure metoduna parametre girilmezse defaultta "hibernate.cfg.xml"
        // dosyasına göre konfig. yapar.
        Configuration config=new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Student.class).addAnnotatedClass(Employee.class); // burda bakmasi gereken tum classlari ekliyoruz

        SessionFactory sessionFactory =config.buildSessionFactory();  //tum uygulamada bir tane sessionFactory olusturulur ve session'lar olusturularak islemler yapilir

        Session session =sessionFactory.openSession(); //veritabaninda bir oturum baslatiyoruz ve data uzerinde CRUD operasyonlarini gerceklestirme imkani buluyoruz

        //oto-commit false durumundadir biz kendimiz comiit ederek verilerin kalici olarak kaydedilmesini saglariz
        //hibernatede default olarak auto-commit:false
        //db de işlemlerin kalıcı olması için transaction başlatılıp onaylanması gerekir

        Transaction transaction = session.beginTransaction(); // burda transaction baslatildi - yani database'de atomik en kucuk islem birimi baslatildi.

        //student1 i tabloya ekleyelim
        //"INSET INTO t_student VALUES(......)"  -- bu islemi hibernate otomatik hale getirmistir
        session.save(student1); // verilen objeyi tabloya tek tek ekleme islemini yamis olur - yukarda yazilan SQL sorgusunu kendi yapar
        session.save(student2);
        session.save(student3);// CREATE islemi gerceklestirdik



        transaction.commit(); // -- transaction onaylar ve sonlandirilir
        session.close();
        sessionFactory.close();



    }
}
