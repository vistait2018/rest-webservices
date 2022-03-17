package com.perspective.restwebservices.dao;

import com.perspective.restwebservices.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserDao {


       private static List<User> users = new ArrayList<>();

        static {
            users.add(new User(1, "Jide", LocalDate.of(2004,11,14)));
            users.add(new User(2, "Wale", LocalDate.of(2003, 05, 12)));
            users.add(new User(3, "Sumbo", LocalDate.of(2004, 02, 15)));
            users.add(new User(4, "Modina", LocalDate.of(1978, 02, 13)));
            users.add(new User(5, "Moraba", LocalDate.of(1984, 02, 13)));
            users.add(new User(6, "Bukky", LocalDate.of(1994, 04, 01)));
            users.add(new User(7, "Tawa", LocalDate.of(1964, 06, 12)));
            users.add(new User(8, "AKeem", LocalDate.of(1924, 07, 19)));

        }
        private static int userCount = users.size();
        public List<User>  getUsers(){
            return users;
        }

        public User getUserById(int id){
            User requiredUser= null;
            for(User user :users){
              if(user.getId() == id)   return user;
            }
            return null;
        }

        public User createUser(User user){
            int userCounter = this.userCount;
            if( user.getId() == null){
                user.setId(++userCounter);
            }
            users.add(user);
            return user;
        }


        public User editUser(Integer id,User user)  {

            Optional<User> userToEdit = users.stream().filter(e->e.getId() == id).findFirst();
            if(userToEdit.isPresent()){
                userToEdit.get().setName(user.getName());
                userToEdit.get().setBirthDate(user.getBirthDate());
                return userToEdit.get();
            }
            return null;
        }


        public Optional<User> deleteUser(Integer id)  {

            Optional<User> userToDelete = users.stream().filter(e->e.getId() == id).findFirst();
           if(userToDelete.isPresent()){
               users.remove(userToDelete.get());
               return userToDelete;
           }
           return null;
        }

}
