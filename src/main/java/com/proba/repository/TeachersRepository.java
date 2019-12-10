

package com.proba.repository;

import com.proba.model.Teachers;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */

/*@Repository("teachersRepository")
public interface TeachersRepository  extends  JpaRepository <Teachers, Integer> {
    Teachers findByUsername(String username);
    Teachers findByEmail(String email);
    List <Teachers> findAll();
}*/
@Repository("teachersRepository")
public interface TeachersRepository  extends  JpaRepository <Teachers, Integer> {
    Teachers findByUsername(String username);
    Teachers findByEmail(String email);
    
    @Query(value="SELECT * FROM e_diary.teachers", nativeQuery=true)
          @Transactional
    List <Teachers> findTeachers();

    public List<Teachers> findByNameLike(String string);
    
}
