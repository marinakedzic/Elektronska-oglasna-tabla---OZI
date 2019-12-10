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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "messages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messages.findAll", query = "SELECT m FROM Messages m"),
    @NamedQuery(name = "Messages.findByIdMessages", query = "SELECT m FROM Messages m WHERE m.idMessages = :idMessages"),
    @NamedQuery(name = "Messages.findByTextMessages", query = "SELECT m FROM Messages m WHERE m.textMessages = :textMessages"),
    @NamedQuery(name = "Messages.findByTime", query = "SELECT m FROM Messages m WHERE m.time = :time")})
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_messages")
    private Integer idMessages;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "text_messages")
    private String textMessages;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "message_id_parent", referencedColumnName = "id_parents")
    @ManyToOne(optional = false)
    private Parents messageIdParent;
    @JoinColumn(name = "message_id_teacher", referencedColumnName = "id_teachers")
    @ManyToOne(optional = false)
    private Teachers messageIdTeacher;

    public Messages() {
    }

    public Messages(Integer idMessages) {
        this.idMessages = idMessages;
    }

    public Messages(Integer idMessages, String textMessages, Date time) {
        this.idMessages = idMessages;
        this.textMessages = textMessages;
        this.time = time;
    }

    public Integer getIdMessages() {
        return idMessages;
    }

    public void setIdMessages(Integer idMessages) {
        this.idMessages = idMessages;
    }

    public String getTextMessages() {
        return textMessages;
    }

    public void setTextMessages(String textMessages) {
        this.textMessages = textMessages;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    public Parents getMessageIdParent() {
        return messageIdParent;
    }

    public void setMessageIdParent(Parents messageIdParent) {
        this.messageIdParent = messageIdParent;
    }

    public Teachers getMessageIdTeacher() {
        return messageIdTeacher;
    }

    public void setMessageIdTeacher(Teachers messageIdTeacher) {
        this.messageIdTeacher = messageIdTeacher;
    

    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMessages != null ? idMessages.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messages)) {
            return false;
        }
        Messages other = (Messages) object;
        if ((this.idMessages == null && other.idMessages != null) || (this.idMessages != null && !this.idMessages.equals(other.idMessages))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proba.model.Messages[ idMessages=" + idMessages + " ]";
    }
    
}
