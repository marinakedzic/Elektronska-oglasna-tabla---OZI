

   
package com.proba.service;

import com.proba.model.Teachers;
import com.proba.model.UserRole;
import com.proba.repository.TeachersRepository;
import com.proba.repository.UserRoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */

@Service("teachersService")
public class TeachersServices {
    
        private TeachersRepository teachersRepository;
      private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRoleRepository userroleRepository;
    
    
    @Autowired
    public TeachersServices(TeachersRepository teachersRepository, UserRoleRepository userroleRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
            this.teachersRepository = teachersRepository;
          this.userroleRepository = userroleRepository;
             this.bCryptPasswordEncoder = bCryptPasswordEncoder;   
    
    }
    
    
    public Teachers findTeachersByusername(String username) {
        return teachersRepository.findByUsername(username);
 
    }
    
    
    public Teachers findByEmail(String email) {
        return teachersRepository.findByEmail(email);
    }
    
    
    public Teachers saveTeacher(Teachers teachers) {
    teachers.setPassword(bCryptPasswordEncoder.encode(teachers.getPassword()));
    teachers.setActive(3);
    UserRole teachersRole = userroleRepository.findByIdStatus(3);
    teachers.setStatusIdStatus(teachersRole);
    return teachersRepository.save(teachers);
    }
    
    
    public List <Teachers> findTeachers() {
         return teachersRepository.findTeachers();
        
    
    }
    
     public List<Teachers> findByNameTeachers(String name) {
        return teachersRepository.findByNameLike("%"+name+"%");
    }
    
    
     public Teachers get(Integer id) {
        return teachersRepository.findById(id).get();
    }
     
    public void delete(Integer id) {
        teachersRepository.deleteById(id);
    }
}
