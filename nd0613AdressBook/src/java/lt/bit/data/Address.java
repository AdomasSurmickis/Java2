/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;

import java.util.Objects;
import lt.bit.db.DB;

/**
 *
 * @author Dedelis
 */
public class Address {

    private static Integer nextId = 1;
    private Integer id;
    private String address;
    private String city;
    private String postalCode;
    
    public Address(String adress, String city, String postalCode) {
        this.address = adress;
        this.city = city;
        this.postalCode = postalCode;
        synchronized (nextId) {//sinchronizacija
            this.id = nextId++;
        }
    }

   
     
    public Address() {
        synchronized (nextId) {//sinchronizacija
            this.id = nextId++;
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }
    
     public static Integer getNextId() {
        return nextId;
    }

   
    
//    static {
//        Address a = new Address("PirmasAdresas", "PirmasMiestas", "00001");
//        
//        a = new Address("AntrasAdresas", "AntrasMiestas", "00002");
//        addresses.add(a);
//    }

//            ir prideti id, adresu id turi but unikalus (nesvaru, kad nesutaps su Person id)
//            defaultini konstruktoriu, konstruktorus su viskup (be id0
//            geteriai seteriai, equols ir hascode pagal id ir prideti to String)
 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", adress=" + address + ", city=" + city + ", postalCode=" + postalCode + '}';
    }

}
