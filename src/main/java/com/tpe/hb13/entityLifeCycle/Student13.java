package com.tpe.hb13.entityLifeCycle;

import javax.persistence.*;

/*

	Entity State :

		1) Transient : Objenin newlenmiş hali, DB ile ilişkisi yok. - uygulama icinde gecici bir opjedir
		 Bu durumda, nesne henüz bir Hibernate session'ına kaydedilmemiştir ve veritabanında bir karşılığı
		yoktur. Geçici durumdaki bir nesne, session'ın kapatılmasıyla birlikte kaybolur.

		2) Persisted or Managed : DB de row a karşılık geldiği durum, save(),get() vs. yapıldığı
		zamana denk geliyor. Bu durumda, nesne bir Hibernate session'ına kaydedilmiştir ve
		veritabanında bir karşılığı vardır. Kalıcı durumdaki bir nesne, session kapatılsa
		bile veritabanında kalır ve daha sonra yeniden kullanılabilir.

		3) Detached :  Bu durumda, nesne bir Hibernate session'ından ayrılmıştır. Artık bu
		session tarafından yönetilmiyor, ancak veritabanında hala bir karşılığı var. Ayrılmış
		durumdaki bir nesne, başka bir session'a bağlanarak kullanılabilir.

		4) Removed : obje remove yapıldığı zamanki durum.
*/

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

    //---------------------------------------TEST ASAMASINDA KULLANILACAKLAR - LOG KAYITLARI GIBI-----------------------
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
