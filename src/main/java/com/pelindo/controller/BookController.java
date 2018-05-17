package com.pelindo.controller;


import com.pelindo.dto.PriceForm;
import com.pelindo.dto.ResponseData;
import com.pelindo.dto.SearchForm;
import com.pelindo.entity.Book;
import com.pelindo.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepo repo;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<?> saveBook(@Valid @RequestBody Book book, Errors errors){

        ResponseData response  = new ResponseData();
        if(errors.hasErrors()){
            for(ObjectError err : errors.getAllErrors()){
                response.getMessages().add(err.getDefaultMessage());
            }
            response.setSuccess(false);
            return ResponseEntity.ok(response);
        }else {
            response.getMessages().add("Book saved");
            response.setSuccess(true);
            response.setData(repo.save(book));
            return ResponseEntity.ok(response);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book findById(@PathVariable("id") Long id) {
        return repo.findOne(id);

    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Book> findAll(){
        return  repo.findAll();
    }

    @RequestMapping(value = "/author/{id}",method = RequestMethod.GET)
    public List<Book> findByAuthorId(@PathVariable("id") Long id){
        return repo.findByAuthorId(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Book> findByTitle(@RequestBody SearchForm form){
        return repo.findByTitleContaining(form.getKey());
    }

    @RequestMapping(value = "/price", method = RequestMethod.POST)
    public List<Book> findByPrice(@RequestBody PriceForm price){
        return repo.findByPriceBetween(price.getMin(), price.getMax());
    }
}
