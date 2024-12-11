package com.tpe.hb01.basicannotations;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

//uygulamaya veri tabanindan data cekme
public class RunnerFetch01 {
    public static void main(String[] args) {

        /*
        DB den data çekmek için : fetch islemlerinde transaction'a gerek yoktur sadece data goruntuleme yapiyoruz . bir data kaydinda onaylama durumu yok
        Task:id=1001/1002/1003 olan öğrenciyi tüm fieldlarıyla getirmek(fetch) istiyoruz.
        1) sessionın metodu:get():en pratik ama kullanım alanı kısıtlı
           2) SQL : DB ce
           3) HQL(Hibernate Query Language): Javaca

           HQL (Hibernate Query Language), Hibernate ORM tarafından sağlanan,
           nesne odaklı bir sorgulama dilidir. HQL, SQL'e benzer şekilde çalışır
           ancak doğrudan veritabanı tablolarıyla değil,
           Java sınıfları (entity'ler) ve onların özellikleriyle çalışır
         */

        Configuration config = new Configuration().configure().addAnnotatedClass(Student.class);

        SessionFactory sf = config.buildSessionFactory();
        Session session = sf.openSession();

        //1.yol - get
        Student student = session.get(Student.class, 1001); // burdan 1001 aydiliyi bulup direk getiriyor
       //buraya primary key verilebilir - sadece id bilgisi verilerek veri cekmek icin kullanilir bazi dez avantajlari vardir
        System.out.println("----------------get methodu--------------------");
        System.out.println(student);

        //2.yol - SQL sorgusu ile
        String sql = "SELECT * FROM t_student WHERE id = 1002";
        Object[] student2 = (Object[]) session.createSQLQuery(sql).uniqueResult(); //sorgu sonucunda tek satir gelecegini biliyorsak uniqueResult kullanilir
        //uniqueResult():sorgunun tek satır getireceğini biliyorsak kullanılır
        //geriye bir satirdan birden fazla data geldiği için data tipleri farklı oldugu icin
        //Object[] içine alınır.
        System.out.println("----------------SQL--------------------");
        System.out.println(Arrays.toString(student2));

        //3.yol - HQL - sorgunu yazarken javaca konusabilirsin -
        String hql = "FROM Student WHERE id = 1003";  //burda stunlarin yerine direk sunlarin olusturuldugu class isimleriyle yazabiliriz tablo isimlerini bilmeye gerek yok
        //Student = t_student,   id=id  tablodaki karsiliklari
        // burda FROM yazmak SELECT * yazmamiza gerek kalmadan islem yapmamizi HQL agliyor
        Student student3 = session.createQuery(hql, Student.class).uniqueResult();
        //uniqueResult():sorgunun tek satır getireceğini biliyorsak kullanılır
        System.out.println("----------------HQL--------------------");
        System.out.println(student3);

        //tum kayitlari cekelim

        //1.yol - HQL
        List<Student> studentList = session.createQuery("FROM Student", Student.class).getResultList(); //birden fazla sonuc gelecek bize liste olarak al demis olduk
        //getResultList birden fazla kayıt geleceği zaman kullanılır
        System.out.println("--------Tum Ogrenciler------");
        for (Student s : studentList){
            System.out.println(s);
        }
        System.out.println("-----------grade=98 olanlar------------------");
        //2.YOL - SQL  --ODEV
        System.out.println("----------------SQL-----------------------");
        String sql2="SELECT * FROM t_student";
        List<Object[]> result=session.createSQLQuery(sql2).getResultList();
        for (Object[] objects:result){
            System.out.println(Arrays.toString(objects));
        }
        //NOT: SQL sorgularinda geriye donus degeri belli olmadigi icin Object yapariz yada tipini belirmemiz gerekir geriye bu deger donuyor diye addEntity(Student.class) bu sekilde
        //-----------------------------
        // HQL ile grade degeri 98 olan ogrencilerin id ve name bilgilerini getirelim
        String hql2 = "SELECT s.id, s.name FROM Student s WHERE s.grade = 98"; //Student s burda bir degisken olusturup gecici olarak digerlerinide bu sekilde s.grade yazmak bir kultur guzel olur
        List<Object[]> resultList = session.createQuery(hql2).getResultList();
        //burda geri donus tipini liste yaptik ama degerlerin tamamini almadigimiz icin Student.class belirtecini kullanmadik -
        // geriye donus tipide farklilik gosterdigi icin List<Object[]> Object olarak donus tipini belirttik
        for (Object[] oa : resultList){
            System.out.println(Arrays.toString(oa));
        }

        session.close();
        sf.close();

    }

    ///ODEV
    //practice:HQL ile
    //1-ismi Harry Potter olan öğrencileri getirelim
    //2-tüm öğrencilerin sadece isimlerini getirelim
    //SQL ile
    //1-tüm öğrencilerin sadece isimlerini getirelim

}
