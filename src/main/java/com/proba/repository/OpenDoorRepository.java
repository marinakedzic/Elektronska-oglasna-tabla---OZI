/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marina
 */
package com.proba.repository;
        
import com.proba.model.Opendoor;
import com.proba.model.Parents;
import com.proba.model.Teachers;
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
@Repository("opendoorRepository")
public interface OpenDoorRepository extends JpaRepository<Opendoor, Integer>{
    @Query(value="SELECT * FROM e_diary.opendoor where opendoor.parents_id_parents=?1" ,nativeQuery=true)
     @Transactional
  List<Opendoor> findOpenDoorByParentsId (@Param ("id_parents") Integer idParents);

 @Query(value="SELECT * FROM e_diary.opendoor inner join e_diary.parents on ( opendoor.parents_id_parents = parents.id_parents) where parents.email=?1",nativeQuery=true)
  @Transactional
  List<Opendoor> findOpenDoorByParentsEmail (@Param ("email") String email);
         
         
  @Query(value="SELECT * FROM e_diary.opendoor where opendoor.teachers_id_teachers=?1" ,nativeQuery=true)
     @Transactional
  List<Opendoor> findOpenDoorByTeachersId (@Param ("id_teachers") Integer idTeachers);

 @Query(value="SELECT * FROM e_diary.opendoor inner join e_diary.teachers on ( opendoor.teachers_id_teachers = teachers.id_teachers) where teachers.email=?1",nativeQuery=true)
  @Transactional
  List<Opendoor> findOpenDoorByTeachersEmail (@Param ("email") String email);
      

}

