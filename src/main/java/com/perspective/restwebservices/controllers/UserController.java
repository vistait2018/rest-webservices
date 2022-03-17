package com.perspective.restwebservices.controllers;

import com.perspective.restwebservices.dao.UserDao;
import com.perspective.restwebservices.exception.UserNotFoundException;
import com.perspective.restwebservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("users")
@RestController
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping({"","/"})
    public List<User> getUsers(){
       return userDao.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserswithId(@PathVariable int id){
        User user = userDao.getUserById(id);
       if(user == null)
           throw new UserNotFoundException(id);

       return user;
    }


    @PostMapping("")
    public ResponseEntity<Object> createUser(@RequestBody User user){
       User savedUser = userDao.createUser(user);
        URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

       return ResponseEntity.created(location).build();
    }

    @PostMapping("/{id}")
    public User editUser(@PathVariable int id, @RequestBody User user) {
           if(this.userDao.getUserById(id) == null){
               throw new UserNotFoundException(id);
           }
             User editedUser =userDao.editUser(id,user);
           return editedUser;


    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        if(this.userDao.getUserById(id) == null){
            throw new UserNotFoundException(id);
        }
            userDao.deleteUser(id);


    }





}
