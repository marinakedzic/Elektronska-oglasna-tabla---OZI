package com.proba.service;

import com.proba.model.News;
import com.proba.repository.NewsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marina
 */
@Service("newsService")
public class NewsService {
    @Autowired
    private NewsRepository nrepo;
    public List<News> findNews() {
         return nrepo.findNews();
    }
    public News save(News n){
        n.setMessages(n.getMessages());
        return nrepo.save(n);
    }
    public News get(Integer id) {
        return nrepo.findById(id).get();
    }
     
    public void delete(Integer id) {
        nrepo.deleteById(id);
    }
}
