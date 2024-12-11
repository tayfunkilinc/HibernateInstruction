package com.tpe.hb02.embeddable;

import javax.persistence.*;

@Entity
@Table(name = "t_student02")
public class Student02 {
    @Id//id sütununa PK kısıtlamasının eklenmesini sağlar
    //@Column(name = "std_id")
    private Integer id;

    @Column(name = "student_name",nullable = false,unique = true,length = 50)//default : varchar(255)
    private String name;//not null
    private int grade;

    /*private String street;
    private String city;
    private String country;
    private String zipcode;*/ //bunlari Address objesinde tutacagim
    @Embedded //gomulu - diger tanimi buraya entegre ettik : Embedded opsiyonel yazmasakta ekler Address Embeddable olduguicin direk gomer
    private Address address;


    //NOT: ihtiyacimiz olmasa bile parametresiz constroctor'a kullandigimiz hibernate ihtiyac duyabilir.
    //hibernate fetch islemlerinde default const kullanir
    public Student02() {
    }

    //parametreli constructor
    public Student02(Integer id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    //getter-setter
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "Student02{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
