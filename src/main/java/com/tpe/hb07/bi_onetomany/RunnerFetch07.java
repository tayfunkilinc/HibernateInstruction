package com.tpe.hb07.bi_onetomany;

import com.tpe.hb06.onetomany.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch07 {
    public static void main(String[] args) {
        Configuration config=new Configuration().configure().
                addAnnotatedClass(Student07.class).addAnnotatedClass(Book07.class);
        SessionFactory sf=config.buildSessionFactory();
        Session session =sf.openSession();

      /*  //id:101 olan kitabı getirelim
        Book07 book = session.get(Book07.class, 101);
        System.out.println(book);
        //bu kitabin sahibi olan ogrenciyi gormek istersem
        System.out.println(book.getStudent());//burda sorgu yazmaya gerek yok direk kitabin sahibine ulassabiliyoruz

        //id:1002 olan öğrencinin kitaplarını getirelim
        Student07 student = session.get(Student07.class, 1002);
        System.out.println(student.getBookList());*/


        //db de degisikliklerin kalici olmasi icin transaction gereklidir.
        Transaction t = session.beginTransaction();

        //book07 tablosunda tüm kayıtları silelim
        String hql ="DELETE FROM Book07"; //class ismi yazarak hql de calisiyoruz
        int deletedBooks = session.createQuery(hql).executeUpdate(); //executeUpdate silinen kayitlari geri donderirir
        System.out.println("Silinen Kitap Sayisi: " + deletedBooks);

        //student07 tablosunda tüm kayıtları silelim
        String hql2 = "DELETE FROM Student07";
        int deletedStudents = session.createQuery(hql2).executeUpdate();
        System.out.println("Silinen Ogrenci Sayisi: " + deletedStudents);

        //İsmi Sefiller olan kitabı HQL ile silelim.
        String hql3 = "DELETE FROM Book07 b WHERE b.name='Sefiller'";
        int deletedById = session.createQuery(hql3).executeUpdate();
        System.out.println("Silinen Sayi: " + deletedById);



        t.commit();
        session.close();
        sf.close();
    }
}
