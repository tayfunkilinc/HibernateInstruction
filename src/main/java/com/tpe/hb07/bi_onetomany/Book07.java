package com.tpe.hb07.bi_onetomany;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book07 {

    @Id
    private Integer book_id;

    private String name;

    @ManyToOne //iliskiyi kurar : FK ekler
    //@JoinColumn --> opsiyonel
    private Student07 student; // bu kitap hangi ogrenciye ait sorusuna cevap vermek icin bunu kullandik

    public Book07() {
    }

    public Book07(Integer book_id, String name) {
        this.book_id = book_id;
        this.name = name;
    }

    public Student07 getStudent() {
        return student;
    }

    public void setStudent(Student07 student) {
        this.student = student;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", name='" + name + '\'' +
                '}';
    }
}
