/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.service;

import com.proba.model.Opendoor;
import com.proba.model.Parents;
import com.proba.model.Teachers;
import com.proba.repository.OpenDoorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** n
 *
 * @author Lenovo
 */
@Service("opendoorServices")
public class OpenDoorServices {
    @Autowired
    private OpenDoorRepository opendoorRepository;
    
   public void addOpenDoor (Opendoor opendoor, Parents parents, Teachers teachers){
        opendoor.setParentsIdParents(parents);
        opendoor.setTeachersIdTeachers(teachers);
        opendoorRepository.save(opendoor);
    }
   
     public void addOpenDoora (Opendoor opendoor, Teachers teachers){
        opendoor.setTeachersIdTeachers(teachers);
        opendoorRepository.save(opendoor);
    }
    public List <Opendoor> findByParentsId (Integer idParents){
       return opendoorRepository.findOpenDoorByParentsId(idParents);
    }
    public List <Opendoor> findByTeachersId (Integer idTeachers) {
        return opendoorRepository.findOpenDoorByTeachersId(idTeachers);
    }
    
    
     public List<Opendoor> findopenDoorsByParentsEmail( String email) {
         return opendoorRepository.findOpenDoorByParentsEmail(email);
         
        }
     
     
     
     public List<Opendoor> findOpenDoorByTeachersEmail( String email) {
         return opendoorRepository.findOpenDoorByTeachersEmail(email);
         
        }
     
     public Opendoor save(Opendoor o){
         o.setActive(true);
       
        return opendoorRepository.save(o);
    }
     
     
}
