package com.pelindo.service;

import com.pelindo.entity.User;
import com.pelindo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =  repo.findByEmail(email);
        if(user==null){
            throw new
                    UsernameNotFoundException("No user with this email");
        }else{
            List<String> roles = new ArrayList<String>();
            roles.add("ADMIN");
            return new User(user,roles);
        }
    }

    public User register(User user) throws Exception{
        if(repo.findByEmail(user.getEmail())!=null){
            throw new Exception("Email already registered");
        }
        return repo.save(user);
    }

    public User login(String email, String password) throws Exception{
        User user = repo.findByEmailAndPassword(email, password);
        if(user!=null){
            return user;
        }else{
            throw new Exception("Login fail");
        }
    }
}
