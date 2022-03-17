package com.perspective.restwebservices.controllers;

import com.perspective.restwebservices.dao.UserDao;
import com.perspective.restwebservices.exception.UserNotFoundException;
import com.perspective.restwebservices.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
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
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        if(this.userDao.getUserById(id) == null){
            throw new UserNotFoundException(id);
        }
           Optional<User> deletedUser = userDao.deleteUser(id);
        if(deletedUser.isPresent()){
            return new ResponseEntity<>(deletedUser.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not delete User", HttpStatus.INTERNAL_SERVER_ERROR);


    }





}
