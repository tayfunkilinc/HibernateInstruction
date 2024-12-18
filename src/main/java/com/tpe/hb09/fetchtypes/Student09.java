package com.tpe.hb09.fetchtypes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
2.taraf Many ise default olarak Lazy , One ise default olarak EAGER yapar :

OneToMany       --> LAZY
ManyToMany      --> LAZY
OneToOne        --> EAGER
ManyToOne       --> EAGER
*/

@Entity
@Table(name ="t_student09")
public class Student09 {
    @Id
    private Integer id;

    @Column(name = "student_name",nullable = false,unique = true,length = 50)//default : varchar(255)
    private String name;

    private int grade;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/) //mappedBy = "student" ucuncu tablo olusturmadik diger taraftan bu iliskiyi alacagiz
    //default fetchtype : lazy
    //fetch = FetchType.EAGER default olai bu sekilde degistirebiliyoruz
    //cascade = CascadeType.ALL buradada kullanilabilir buradada kullanilirse iliskinin iki taraftada set edilmesi gerekir
    private List<Book09> bookList = new ArrayList<>();

    //const
    public Student09() {
    }

    public Student09(Integer id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    //getter-setter

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

    public List<Book09> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book09> bookList) {
        this.bookList = bookList;
    }
    //to string
    @Override
    public String toString() {
        return "Student09{" +
                "grade=" + grade +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
