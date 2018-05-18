package com.pelindo.controller;


import com.pelindo.dto.ResponseData;
import com.pelindo.dto.SearchForm;
import com.pelindo.entity.Author;
import com.pelindo.repo.AuthorRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Author.")
@RestController
@RequestMapping("/author")
@Transactional
public class AuthorController {

    @Autowired
    AuthorRepo repo;

    @Autowired
    DataSource dataSource;


    @ApiOperation("Save an Author to the system.")
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

    @RequestMapping(method = RequestMethod.GET, value="/procedure")
    public List<Author> getAll(){
        return repo.getAllAuthor();
    }

    @RequestMapping(method = RequestMethod.POST, value="/email")
    public Author getByEmail(@RequestBody SearchForm form){
        return repo.getAuthorByEmail(form.getKey());
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public List<Author> findByName(@RequestBody SearchForm form){
        return repo.findByName("%"+form.getKey()+"%");
    }


}
