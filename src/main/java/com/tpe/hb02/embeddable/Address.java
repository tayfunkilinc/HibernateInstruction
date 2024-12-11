package com.tpe.hb02.embeddable;

import javax.persistence.Embeddable;

/*@Embeddable Anotasyonu
@Embeddable, gömülü bir bileşen sınıfını tanımlamak için kullanılır.
Bu sınıf bir entity değildir; yalnızca bir entity'nin bir parçası olarak kullanılır.
@Embeddable sınıfı bağımsız bir tablo oluşturmaz.
        Veritabanında, bu sınıfın alanları ana entity'nin
alanlarıyla aynı tabloya eklenir.

@Embedded Anotasyonu
@Embedded, bir entity içinde @Embeddable sınıfın kullanılacağını belirtir.
Entity, @Embedded sınıfın özelliklerini kendi özellikleri gibi kabul eder
ve veritabanı tablosunda bunları ayrı sütunlar olarak saklar.

Kullanım Senaryoları:
Bir entity'deki adres, iletişim bilgileri vb.
gibi bir grup alanı(field) düzenli bir şekilde modellemek için uygundur.*/

@Embeddable //gomulebilir - bu class icindeki fiel'lari sutun olarak olusturup Student tablosunun icine gomecegim
public class Address {

    private String street;
    private String city;
    private String country;
    private String zipcode;

    //parametresiz cont kullanmadik bakalim ne olacak : hibernate kullandigi icin olusturmaliyiz
    public Address() {
    }

    //parametreli const olusturalim ki sonra yeni adresi direk set ederiz
    public Address(String street, String city, String country, String zipcode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

    //getter-setter
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    //toString
    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
