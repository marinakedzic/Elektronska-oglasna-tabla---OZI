/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "opendoor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opendoor.findAll", query = "SELECT o FROM Opendoor o"),
    @NamedQuery(name = "Opendoor.findByIdOpendoor", query = "SELECT o FROM Opendoor o WHERE o.idOpendoor = :idOpendoor"),
    @NamedQuery(name = "Opendoor.findByDateAndTime", query = "SELECT o FROM Opendoor o WHERE o.dateAndTime = :dateAndTime")})
public class Opendoor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_opendoor")
    private Integer idOpendoor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_and_time")
    private String dateAndTime;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    @JoinColumn(name = "parents_id_parents", referencedColumnName = "id_parents")
    @ManyToOne(optional = false)
    private Parents parentsIdParents;
    @JoinColumn(name = "teachers_id_teachers", referencedColumnName = "id_teachers")
    @ManyToOne(optional = false)
    private Teachers teachersIdTeachers;

    public Opendoor() {
    }

    public Opendoor(Integer idOpendoor) {
        this.idOpendoor = idOpendoor;
    }

    public Opendoor(Integer idOpendoor, String dateAndTime) {
        this.idOpendoor = idOpendoor;
        this.dateAndTime = dateAndTime;
    }

    public Integer getIdOpendoor() {
        return idOpendoor;
    }

    public void setIdOpendoor(Integer idOpendoor) {
        this.idOpendoor = idOpendoor;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Parents getParentsIdParents() {
        return parentsIdParents;
    }

    public void setParentsIdParents(Parents parentsIdParents) {
        this.parentsIdParents = parentsIdParents;
    }

    public Teachers getTeachersIdTeachers() {
        return teachersIdTeachers;
    }

    public void setTeachersIdTeachers(Teachers teachersIdTeachers) {
        this.teachersIdTeachers = teachersIdTeachers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpendoor != null ? idOpendoor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opendoor)) {
            return false;
        }
        Opendoor other = (Opendoor) object;
        if ((this.idOpendoor == null && other.idOpendoor != null) || (this.idOpendoor != null && !this.idOpendoor.equals(other.idOpendoor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proba.model.Opendoor[ idOpendoor=" + idOpendoor + " ]";
    }
    
}
