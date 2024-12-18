package com.tpe.hb08.manytomany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_student08")
public class Student08 {
    @Id
    private Integer id;

    @Column(name = "student_name",nullable = false,unique = true,length = 50)//default : varchar(255)
    private String name;

    private int grade;

    @ManyToMany  //iliskiyi kurar : JOIN TABLE ile yani ucuncu bir tablo ile bu iliskini kurulmasi saglanir
    @JoinTable(name = "student08_course", joinColumns = {@JoinColumn(name ="std_id")},
            inverseJoinColumns = {@JoinColumn(name = "coure_id")}) // opsiyonel isterseniz varsayilan ilede devam edebilirsiniz
    private List<Course> courseList = new ArrayList<>();

    //const
    public Student08() {
    }

    public Student08(Integer id, String name, int grade) {
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

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    //toString
    @Override
    public String toString() {
        return "Student08{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
