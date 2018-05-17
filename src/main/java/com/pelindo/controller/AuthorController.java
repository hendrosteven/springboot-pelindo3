package com.pelindo.controller;


import com.pelindo.dto.ResponseData;
import com.pelindo.dto.SearchForm;
import com.pelindo.entity.Author;
import com.pelindo.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
@Transactional
public class AuthorController {

    @Autowired
    AuthorRepo repo;

    @Autowired
    DataSource dataSource;


    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<?> saveAuthor(@Valid @RequestBody Author author, Errors errors){
        ResponseData response  = new ResponseData();
        if(errors.hasErrors()){
            for(ObjectError err : errors.getAllErrors()){
                response.getMessages().add(err.getDefaultMessage());
            }
            response.setSuccess(false);
            return ResponseEntity.ok(response);
        }else {
            response.getMessages().add("Author saved");
            response.setData(repo.save(author));
            response.setSuccess(true);
            return ResponseEntity.ok(response);
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Author findById(@PathVariable("id") Long id){
        return repo.findOne(id);
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
