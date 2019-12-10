/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "parents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parents.findAll", query = "SELECT p FROM Parents p"),
    @NamedQuery(name = "Parents.findByIdParents", query = "SELECT p FROM Parents p WHERE p.idParents = :idParents"),
    @NamedQuery(name = "Parents.findByName", query = "SELECT p FROM Parents p WHERE p.name = :name"),
    @NamedQuery(name = "Parents.findBySurname", query = "SELECT p FROM Parents p WHERE p.surname = :surname"),
    @NamedQuery(name = "Parents.findByUsername", query = "SELECT p FROM Parents p WHERE p.username = :username"),
    @NamedQuery(name = "Parents.findByPassword", query = "SELECT p FROM Parents p WHERE p.password = :password"),
    @NamedQuery(name = "Parents.findByActive", query = "SELECT p FROM Parents p WHERE p.active = :active"),   
    @NamedQuery(name= "Parents.findByEmail", query="SELECT p FROM Parents p WHERE p.email = :email" )})
public class Parents implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parents")
    private Integer idParents;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 235)
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private Integer active;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 235)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentsIdParents")
    private Set<Opendoor> opendoorSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "messageIdParent")
    private Set<Messages> messagesSet;
    @JoinColumn(name = "status_id_status", referencedColumnName = "id_status")
    @ManyToOne(optional = false)
    private UserRole statusIdStatus;

    public Parents() {
    }

    public Parents(Integer idParents) {
        this.idParents = idParents;
    }

    public Parents(Integer idParents, String name, String surname, String username, String password) {
        this.idParents = idParents;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public Integer getIdParents() {
        return idParents;
    }

    public void setIdParents(Integer idParents) {
        this.idParents = idParents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Set<Opendoor> getOpendoorSet() {
        return opendoorSet;
    }

    public void setOpendoorSet(Set<Opendoor> opendoorSet) {
        this.opendoorSet = opendoorSet;
    }

    @XmlTransient
    public Set<Messages> getMessagesSet() {
        return messagesSet;
    }

    public void setMessagesSet(Set<Messages> messagesSet) {
        this.messagesSet = messagesSet;
    }

    public UserRole getStatusIdStatus() {
        return statusIdStatus;
    }

    public void setStatusIdStatus(UserRole statusIdStatus) {
        this.statusIdStatus = statusIdStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
       public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParents != null ? idParents.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parents)) {
            return false;
        }
        Parents other = (Parents) object;
        if ((this.idParents == null && other.idParents != null) || (this.idParents != null && !this.idParents.equals(other.idParents))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " " + name + " " + surname + " ";
    }
    
}
