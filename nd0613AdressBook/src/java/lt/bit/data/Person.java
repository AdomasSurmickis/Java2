/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dedelis
 */
public class Person {

    private static Integer nextId = 1;
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private BigDecimal salary;
    private List<Address> addresses;
    private List<Contact> contacts;

    public Person(String firstName, String lastName, Date birthData, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthData;
        this.salary = salary;
        synchronized (nextId) {//sinchronizacija
            this.id = nextId++;

            this.addresses = new ArrayList();
//            Address a = new Address("PirmasAdresas", "PirmasMiestas", "00001");//testam
//            addresses.add(a);                                                  //testam
            this.contacts = new ArrayList();
        }
    }

    public Person() {
        synchronized (nextId) {
            this.id = nextId++;
            this.addresses = new ArrayList();
            this.contacts = new ArrayList();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthData=" + birthDate + ", salary=" + salary + '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }    

    public static Integer getNextId() {
        return nextId;
    }

}

//ND0613:
//padaryti adresu knygute:
//sukurti webprojekta, jame sukurti pakckage lt.bit.data
//objektai bus
//turesim saugot info apie person:
//gali tureti daug adresu, gali tureti daug kontaktu
//
//lt.bit.da kalse person
//reik atsizvelgit, kad busena gali keistis bet kuriuo metu (pvz. vienu metu nori kazkas istriti ta pati elemneta
//siekiant to isvengti sistemose blokam suteikiam savo identifikatoriu (unikalu savo sugalvota koda);
// dazniausiai toks laukas vadinas ID or PK; ty skaicius, bet NEDETI niekada primityvu (ne int, o Integer);
//si klase skirta saugoti duomenim, jos vadinasi BEAN klases (neturi funkcionalumo, bet saugo busena);
//reik kiekvienam objektui priskirti unikalu id (dazniausiai realiuose projektuose tai daro duomenu baze);
//sito projekto atveju i duombaze nesaugosim, saugosim atminty;
//id turi but static; prie statiinio metodo pridedam 1 ir priskiriame id konstruktoriuje
// jei daryti visikai tvarkingai, mes turime sinchronizuoti priejima prie next id (kad atsitiktinai nesukurti to paites id)
// todel sinchronicuoti reik and objekto (nes nemano primityvo); (sinchronized ties nextId);
//sukurti klase Adress ir klase Contact;


//UZDUOTIS INDEX HTML trinti, sukurti indext.jsp; sukurti CRUD (Create, read, update and delete)(tai ka darem 0612) visiem 3 tipa
//first name, lastname, Bday, salary edit; delete;
//prie kikvieno mygtukas edit, delete
//tiek add tiek edit turi rodyti i forma, kur galima ivesti informacija apie nauja (taip kaip darem sustringais)
//turi but savePersonServlet grazina i pradine forma
//delete turi buti delete person servlet; grazindti i pakrindine forma

//prie kiekvieno zmogaus turi buti po mygtuka to zmogaus adresam ir top zmogaus kontaktam;
//adresas nurodo i personAdressList, kuriam rodo tik to zmogaus adresus (adress, city, postal code); turi buti mygtukas add
//prie kiekvieno is adresu mygtuaki edit ir delete turi buti; nuspaudus add arba edit turi eiti i forma personAdressEdit
//Person adres edit todo i laukus adress, city ir postal code. cia turi mygtukas save ir cancel; mygtukas save turi buti
//i savePerosAdressServlet, o grizti i to paties person adressu sarasa. ten pat grizti nuspaudus cancel;
//adress liste turi buti mygtukas back, grizta ir pagr (person list);
//adrsu sarase dele grizta i to paties person adresu sarasa
//su contaktais analogiskai adress (tel, email, n tel, ir pan);

//template yra. keli niuansai: kaip isversti data (nes ateina kai string); skaiciu isversti i bigDecimal;
//jei saugom person, is kart gausim id. jei editinam tai id nekinta;
//reiks pagalvoti kaip kaip is adressu editinimo grizti i adresus;
//dizainas nesvarbu, svarbu funkcionalumas;