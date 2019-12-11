/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.proba.service;

import com.proba.model.Parents;
import com.proba.model.UserRole;
import com.proba.repository.ParentsRepository;
import com.proba.repository.UserRoleRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lenovo
 */

@Service("parentsService")
public class ParentsService {
    
private ParentsRepository parentsRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRoleRepository userroleRepository;
    
     /**
     *
     * @param parentsRepository
     * @param userroleRepository
     * @param bCryptPasswordEncoder
     */
    
    
     @Autowired
    public ParentsService(ParentsRepository parentsRepository, UserRoleRepository userroleRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
            this.parentsRepository = parentsRepository;
            this.userroleRepository = userroleRepository;
             this.bCryptPasswordEncoder = bCryptPasswordEncoder;    
    }
     public Parents findParentsByEmail(String email) {
        return parentsRepository.findByEmail(email) ; 
    }
    public List <Parents> findAll() {
        return parentsRepository.findAll();
    }
     
    public List <Parents> listaroditelja(){
        return parentsRepository.findParents();
    }
     
    public Parents saveParents(Parents parents) {
        parents.setPassword(bCryptPasswordEncoder.encode(parents.getPassword()));
        parents.setActive(1);
        UserRole parentRole = userroleRepository.findByIdStatus(4);
        parents.setStatusIdStatus(parentRole);
       return parentsRepository.save(parents); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Parents> findByName(String name) {
        return parentsRepository.findByNameLike("%"+name+"%");
    }
    public Parents get(Integer id) {
        return parentsRepository.findById(id).get();
    }
    public void delete(Integer id) {
        parentsRepository.deleteById(id);
    }
     }
    

