
package com.proba.service;

import com.proba.model.Messages;
import com.proba.repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marina
 */
@Service("messageService")
public class MessagesService {
    @Autowired
    private MessagesRepository mrepo;
    public List<Messages> findMessagesByParentsId(Integer idParents) {
         return mrepo.findMessagesByParentsId(idParents);
         
        }
    public List<Messages> findMessagesByTeachersId(Integer idTeachers) {
         return mrepo.findMessagesByTeachersId(idTeachers);
         
        }
    public Messages save(Messages m){
        m.setTextMessages(m.getTextMessages());
        m.setMessageIdTeacher(m.getMessageIdTeacher());
        m.setMessageIdParent(m.getMessageIdParent());
        return mrepo.save(m);
    }
    public Messages get(Integer id) {
        return mrepo.findById(id).get();
    }
     
    public void delete(Integer id) {
        mrepo.deleteById(id);
    }
}
