package com.proba.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.proba.model.Messages;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marina
 */
@Repository("messagesRepository")
public interface MessagesRepository extends JpaRepository<Messages, Integer> {
    @Query(value=" SELECT * FROM e_diary.messages"
            + " where messages.message_id_parent=?1 ", nativeQuery=true)
          @Transactional
    List <Messages> findMessagesByParentsId (@Param ("id_parents") Integer idParents);
    
    @Query(value=" SELECT * FROM e_diary.messages"
            + " where messages.message_id_teacher=?1 ", nativeQuery=true)
          @Transactional
    List <Messages> findMessagesByTeachersId (@Param ("id_teacher") Integer idParents);
}
