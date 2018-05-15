package com.pelindo.controller;


import com.pelindo.dto.SearchForm;
import com.pelindo.entity.Author;
import com.pelindo.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
@Transactional
public class AuthorController {

    @Autowired
    AuthorRepo repo;


    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Author saveAuthor(@RequestBody Author author){
        return repo.save(author);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Optional<Author> findById(@PathVariable("id") Long id){
        return repo.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Author> findAll(){
        return repo.findAll();
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public List<Author> findByName(@RequestBody SearchForm form){
        return repo.findByName("%"+form.getKey()+"%");
    }


}
