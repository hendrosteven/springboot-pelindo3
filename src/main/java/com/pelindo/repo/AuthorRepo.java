package com.pelindo.repo;

import com.pelindo.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepo extends CrudRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE a.name LIKE :name")
    public List<Author> findByName(@Param("name") String name);


}
