package com.pelindo.repo;

import com.pelindo.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

    public User findByEmail(String email);
    public User findByEmailAndPassword(String email, String password);

}
