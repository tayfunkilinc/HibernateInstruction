package com.tpe.hb13.entityLifeCycle;

import javax.persistence.*;

@Entity
public class Student13 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer grade;

    public Student13() {
    }

    public Student13(String name, Integer grade) {
        this.name = name;
        this.grade = grade;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student13{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }

    //---------------------------------------TEST ASAMASINDA KULLANILACAKLAR------------------------------------------
    // NOT: @PrePersist ve @PostPersist problemlerin tespiti asamalarinda kullanilir
    @PrePersist //kaydetmeden once ulasmamizi sagiliyor // DB'ye kayit yapmadan once gerceklesecek islemlerin onceliklendirilmesi icin kullanilir
    public void prePersist(){
        System.out.println("----------Ogrenci Kayit Ediliyor...");
    }
    @PostPersist //DB'ye kayittan hemen sonra kayit bilgisini bilgilendirme almak cin bu kullanilir
    public void postPersist(){
        System.out.println("--------------Ogrenci Kaydedildi...");
    }
    @PreUpdate
    public void preUpdate(){
        System.out.println("------------------Ogrenci Guncelleniyor..");
    }
    @PostUpdate
    public void postUpdate(){
        System.out.println("------------------Ogrenci Guncellendi..");
    }
    @PreRemove
    public void preRemove(){
        System.out.println("------------------Ogrenci Siliniyor..");
    }
    @PostRemove
    public void postRemove(){
        System.out.println("------------------Ogrenci Silindi..");
    }
    @PostLoad
    public void postLoad(){
        System.out.println("------------------Ogrenci Uygulamaya Yuklendi..");
    }
}
