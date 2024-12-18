package com.tpe.hb12.getLoad;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/*
   get -> dönen objeye hemen ihtiyaç duyulursa get kullanılır.
       -> hemen db ye başvurur
       -> obje yoksa null döner
       -> id ile obje olduğuna emin değilsek get kullanılmalı

   load -> proxy(gölge) döner -- dogrudan DB'e gitmez oncelikle gecici bir obje getirir
        -> hemen db ye başvurmaz->ne zaman ihtiyaç duyulursa gerçek nesneyi döner
        -> obje yoksa not found exception fırlatır
        -> id ile obje olduğuna eminsek load kullanılmalı
        -> objeye reference olarak ihtiyaç duyulursa kullanılmalı

 */
public class RunnerFetch12 {
    public static void main(String[] args) {
        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student12.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();

        //get
        System.out.println("____________________get methodundan once");
        Student12 student =session.get(Student12.class, 1);
        System.out.println("____________________get methodundan sonra");
        System.out.println("ID: " + student.getId());
        System.out.println("Ogrencinin Adi: : " + student.getName());


        //load: DB 'den datalarin uygulama icerisine yuklemesini sagliyor
        System.out.println("____________________load methodundan once");
        Student12 student2= session.load(Student12.class,1);
        System.out.println("____________________load methodundan sonra");

        System.out.println("ID: " + student2.getId()); //bunu bizim verdigimiz id 'den anlayip db'e gitmeden getirir ama ekstra bir sey isteyince db'ye gider getirir
        System.out.println("Ogrencinin Adi: : " + student2.getName());

        //tabloda olmayan bir kayit icin kullanilirsa
        //get
        Student12 student3 = session.get(Student12.class,100); // get methodu veri bulamassa geriye null donderir.
        if (student3 !=null){
            System.out.println(student3.getName()); //NullPointerException bu ihtimal icin bunu if ile kontrol etmeliyiz
        }
        else {
            System.out.println("******Ogrenci Bulunamadi");
        }

        //load
        try {
            Student12 student4 = session.load(Student12.class, 200); //load methodu database e gitmedigi icin olup olmadigini bilmedigi icin bir uyari vermez ama ekrana yazidirirsak
            System.out.println(student4); //ObjectNotFoundException firlatir veri bulunamadigi icin bunun icin try-catch ile hatayi yakaliyoruz

        } catch (Exception e) {
            System.out.println("******Ogrenci Bulunamadi");
        }





        session.close();
        sf.close();
    }
}
