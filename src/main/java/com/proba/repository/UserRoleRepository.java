/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.repository;

import com.proba.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("UserRoleRepository")
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
    UserRole findByRole(String role);
    UserRole findByIdStatus(Integer idStatus);
    
}
