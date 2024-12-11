package com.tpe.hb01.basicannotations;

//BU CLASS OBJELERINI KALICI HALA GETIRMEK ICIN

//hedef:
//dataları persist etmek için bu classa karşılık bir tablo gerekli
//tablonun sütunları:id,name,grade
//create table student(id int, name varchar...)
//hibernate(ORM) bizim için bu hedefi otomatik olarak yapar.

import javax.persistence.Column;
import javax.persistence.Entity; // DB de burda bulunana degiskenlere karsilik gelen otomatik bir tablo olusturulmasini sagliyor
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //bu sinifin DB de bir tabloya karsilik gelmesini sagliyoruz, tablonun adı class ismi olur: student //id , name, grade ismindede stunlari olusturulur
@Table (name = "t_student") //tablonun ismini kendimiz verebiliriz.OPSİYONEL
//!!! Javaca konuşurken bu sınıfı belirtirken Student,
//SQLce konuşurken t_student kullanırız.
public class Student {

    @Id //Entity kullanildiysa Id annatation kullanilmaidir burda id stununu primary key kisitini eklemis olduk
    // -- dikkat burda @Id nin alttaki id ile baglantisi yok alttaki id herhangi bir isim olabilir
    //@Column(name = "std_id") //-- bu sekilde stun isimlerinide istedigimiz gibi degistirebiliriz
    private Integer id;
    @Column(name = "student_name", nullable = false, unique = true, length = 50)
    // nullable: bos gecilemez, unique: benzersiz olsun, length varchar default degerini 50 olarak degistirdik normalde varsayilan varchar(255)
    private String name;
    private int grade;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
