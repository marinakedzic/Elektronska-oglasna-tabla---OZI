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
@Table(name = "teachers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teachers.findAll", query = "SELECT t FROM Teachers t"),
    @NamedQuery(name = "Teachers.findByIdTeachers", query = "SELECT t FROM Teachers t WHERE t.idTeachers = :idTeachers"),
    @NamedQuery(name = "Teachers.findByName", query = "SELECT t FROM Teachers t WHERE t.name = :name"),
    @NamedQuery(name = "Teachers.findBySurname", query = "SELECT t FROM Teachers t WHERE t.surname = :surname"),
    @NamedQuery(name = "Teachers.findByUsername", query = "SELECT t FROM Teachers t WHERE t.username = :username"),   
    @NamedQuery(name = "Teachers.findByActive", query = "SELECT t FROM Teachers t WHERE t.active = :active"),
    @NamedQuery(name = "Teachers.findByPassword", query = "SELECT t FROM Teachers t WHERE t.password = :password"),
    @NamedQuery(name = "Teachers.findByEmail", query = "SELECT t FROM Teachers t WHERE t.email = :email")})

public class Teachers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_teachers")
    private Integer idTeachers;
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
    @Size(min = 1, max = 245)
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private Integer active;
     @Size(max = 255)
     @Size(min = 1, max = 45)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
     private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teachersIdTeachers")
    private Set<Opendoor> opendoorSet;
    @JoinColumn(name = "status_id_status", referencedColumnName = "id_status")
    @ManyToOne(optional = false)
    private UserRole statusIdStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "messageIdTeacher")
    private Set<Messages> messagesSet;

    public Teachers() {
    }

    public Teachers(Integer idTeachers) {
        this.idTeachers = idTeachers;
    }

    public Teachers(Integer idTeachers, String name, String surname, String username, String password, String email) {
        this.idTeachers = idTeachers;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email= email;
    }

    public Integer getIdTeachers() {
        return idTeachers;
    }

    public void setIdTeachers(Integer idTeachers) {
        this.idTeachers = idTeachers;
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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
    
    
public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    @XmlTransient
    public Set<Opendoor> getOpendoorSet() {
        return opendoorSet;
    }

    public void setOpendoorSet(Set<Opendoor> opendoorSet) {
        this.opendoorSet = opendoorSet;
    }

    public UserRole getStatusIdStatus() {
        return statusIdStatus;
    }

    public void setStatusIdStatus(UserRole statusIdStatus) {
        this.statusIdStatus = statusIdStatus;
    }

    @XmlTransient
    public Set<Messages> getMessagesSet() {
        return messagesSet;
    }

    public void setMessagesSet(Set<Messages> messagesSet) {
        this.messagesSet = messagesSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTeachers != null ? idTeachers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teachers)) {
            return false;
        }
        Teachers other = (Teachers) object;
        if ((this.idTeachers == null && other.idTeachers != null) || (this.idTeachers != null && !this.idTeachers.equals(other.idTeachers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " " + name + " " + surname + " ";
    }
    
}
