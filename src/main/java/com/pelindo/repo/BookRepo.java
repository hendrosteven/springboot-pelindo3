package com.pelindo.repo;


import com.pelindo.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {

    public List<Book> findByAuthorId(Long id);

    public List<Book> findByTitleContaining(String title);

    public List<Book> findByPriceBetween(double min, double max);
}
