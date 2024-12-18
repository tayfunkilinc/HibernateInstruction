package com.tpe.hb06.onetomany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="t_student06")
public class Student06 { //one

    @Id
    private Integer id;

    @Column(name = "student_name",nullable = false,unique = true,length = 50)//default : varchar(255)
    private String name;

    private int grade;

    @OneToMany //tablolar arasında ilişkinin kurulmasını sağlar
    @JoinColumn(name = "student_id") //bunu kullanimi zorunlu
    //JOIN TABLE iptal, book tablosuna FK ekler:ZORUNLU
    //join column kullanılmazsa JOIN TABLE oluşturulur. - burada JoinColumn yamamak ekstra yardimci tablo olusturur FK leri tutmak icin
    private List<Book> bookList = new ArrayList<>(); // bu ogrencinin kitaplari nelerdir.

    //const
    public Student06() {
    }

    public Student06(Integer id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    //getter-setter


    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

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

    //toString

    @Override
    public String toString() {
        return "Student06{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
