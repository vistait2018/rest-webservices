package com.perspective.restwebservices.dao;

import com.perspective.restwebservices.model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private UserDao ud = new UserDao();

    @Test
    void getUsers() {
        //given
        List<User> users = ud.getUsers();

        //when
        int count = users.size();
        Optional<User> jide =users.stream().findFirst();
        String name = jide.get().getName();
        //then
        // assertThat(name).isEqualTo("Jide");
      assertThat(count).isEqualTo(8);
    }

    @Test
    @Disabled
    void getUserById() {
    }

    @Test
    @Disabled
    void createUser() {
    }

    @Test
    @Disabled
    void editUser() {
    }

    @Test
    @Disabled
    void deleteUser() {
    }
}