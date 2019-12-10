/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proba.repository;

import com.proba.model.Parents;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository("parentsRepository")
public interface ParentsRepository extends JpaRepository<Parents, Integer> {
      Parents findByEmail(String email);
    Parents  findByUsername(String username);
    @Query(value="SELECT * id_parents from e_diary.parents where email=?1", nativeQuery = true)
    @Transactional 
   List <Parents> findParentsByEmail (@Param ("email") String email);

   
   
    @Query(value="SELECT * FROM e_diary.parents", nativeQuery=true)
          @Transactional
    List <Parents> findParents();
   
    public List<Parents> findByNameLike(String name);
   
   
}
