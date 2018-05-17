package com.pelindo.controller;

import com.pelindo.dto.LoginForm;
import com.pelindo.dto.ResponseData;
import com.pelindo.entity.User;
import com.pelindo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, Errors errors){

        ResponseData response = new ResponseData();
        if(errors.hasErrors()){
            for(ObjectError err: errors.getAllErrors()){
                response.getMessages().add(err.getDefaultMessage());
            }
            response.setSuccess(false);
            return ResponseEntity.ok(response);
        }
        try {
            User output = userService.register(user);
            response.setData(output);
            response.setSuccess(true);
            return ResponseEntity.ok(response);
        }catch (Exception ex){
            response.getMessages().add(ex.getMessage());
            response.setSuccess(false);
            return ResponseEntity.ok(response);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public User login(@RequestBody LoginForm form) throws Exception {

        return userService.login(form.getEmail(), form.getPassword());
    }

}
