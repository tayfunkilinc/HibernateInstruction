package com.tpe.hb09.fetchtypes;

import javax.persistence.*;

@Entity
public class Book09 {//many
    @Id
    private Integer id;
    private String name;

    //cascade = CascadeType.ALL:bir kitabı DB ye kaydettiğimde bu kitabın
    // sahibi olan öğrenciyi de otomatik olarak kaydeder
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //default olarak fetchtype : aeger
    private Student09 student;

    //const
    public Book09() {
    }

    public Book09(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Student09 getStudent() {
        return student;
    }

    public void setStudent(Student09 student) {
        this.student = student;
    }

    //tostring

    @Override
    public String toString() {
        return "Book09{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
