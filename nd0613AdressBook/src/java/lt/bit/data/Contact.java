/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;

import java.util.Objects;

/**
 *
 * @author Dedelis
 */
public class Contact {

    private static Integer nextId = 1;
    private Integer id;
    private String contact;
    private String type;

    public Contact(String type, String contact) {
        this.contact = contact;
        this.type = type;
        synchronized (nextId) {//sinchronizacija
            this.id = nextId++;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getContact() {
        return contact;
    }

    public String getType() {
        return type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    //            ir prideti id, adresu id turi but unikalus (nesvaru, kad nesutaps su Person id)
    //            defaultini konstruktoriu, konstruktorus su viskup (be id0
    //            geteriai seteriai, equols ir hascode pagal id ir prideti to String)
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
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", contact=" + contact + ", type=" + type + '}';
    }

    public Contact() {
        synchronized (nextId) {//sinchronizacija
            this.id = nextId++;
        }
    }

    public static Integer getNextId() {
        return nextId;
    }

}
