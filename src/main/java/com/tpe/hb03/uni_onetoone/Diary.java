package com.tpe.hb03.uni_onetoone;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Diary {
    @Id
    private Integer id;
    private String name;

    @OneToOne
    @JoinColumn(name = "std_id", unique = true)  //bu damamen opsiyonel bunu ekstra ozellik eklemek icin kullanilir
    //NOT: one-to-one iliskide FK sutunu tekrar eden deger alamaz unique = true eklenmeli
    private Student03 student; // bu satirla ONO-TO-ONE iliskisini kurduk. @OneToOne ile iliskiyi kurmus olduk
    //diary ile t_student03 arasında 1-1 ilişki kurulmasını sağlar
    //bunun için diary e FK ekler: default ismi : student_id - icerisindeki degerler Student03 un idleri eklenir
    //ana tablodaki id burda fk olmus oluyor


    public Student03 getStudent() {
        return student;
    }

    public void setStudent(Student03 student) {
        this.student = student;
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

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
