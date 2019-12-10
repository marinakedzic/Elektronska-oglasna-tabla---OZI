package com.proba.repository;
import java.util.List;
import com.proba.model.News;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
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
@Repository("newsRepository")
public interface NewsRepository extends JpaRepository<News, Integer>{
    @Query(value=" SELECT * FROM e_diary.news", nativeQuery=true)
          @Transactional
    List <News> findNews();
}
