/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.repository;

import com.proba.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository("adminRepository")
public interface AdminRepository extends JpaRepository<Admin, String>{          
    Admin findByEmail(String email);
  Admin findByUsername(String username);
    
    
    
}
