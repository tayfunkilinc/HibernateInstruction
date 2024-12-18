package com.tpe.hb10.idGeneration;

import javax.persistence.*;

/*
-------------Strategy'ler----
IDENTITY:1 den başlar 1er artırarak id leri generate eder. - id uretilmesi DB tarafindan kontrol eder

TABLE:id üretmek için tablo oluşturur,EN YAVAŞ, bu sebeple pek tercih edilmez

SEQUENCE:id set oluşturur,başlangıç değeri verebiliriz,HIZLIDIR - hibernate kismindan baslangicta ayarlanir

AUTO:Kullanılan DB ye göre stratejiyi belirler
       Oracle DB - PostgreSQL ---> Sequence ( kontrolü developera bırakır, Id üretilirken
            başlangıç değeri veya kaç tane id cachelenecek bu gibi bilgileri developer setliyebilir)

       MySQL - Microsoft SQL   ---> IDENTITY ( kontrol DB de , kendi yapısına göre ıd oluşturur,
            içlerindeki en basitidir)
 */

@Entity
@Table(name = "t_student10")
public class Student10 {
    //1.YOL
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer id;

    //2.YOL
    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence",
            sequenceName = "idsequence", //default olarak hibernate_sequence
            initialValue = 1000, //default: 1 baslangic degeri
            allocationSize = 10)  //id setinde kac adet id hair olarak bulunacak, default:50
    private Integer id;

    //3.YOL AUTO bunu kullaninca DB'e gore IDENTITY , SEQUENCE kullanir


    @Column(name = "student_name",nullable = false)
    private String name;

    //getter-setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    /*public void setId(Integer id) {
        this.id = id;
    }*/
}
