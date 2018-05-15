package com.pelindo.controller;

import com.pelindo.dto.PriceForm;
import com.pelindo.dto.SearchForm;
import com.pelindo.entity.Book;
import com.pelindo.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepo repo;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Book saveBook(@RequestBody Book book){
        return repo.save(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Book> findById(@PathVariable("id") Long id){
        return repo.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Book> findAll(){
        return repo.findAll();
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
