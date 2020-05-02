package com.certimetergroup.formazione.service.user;

import com.certimetergroup.formazione.FormazioneApplication;
import com.certimetergroup.formazione.model.User;
import com.certimetergroup.formazione.service.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FormazioneApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    @Autowired
    private UserService userService;




    @Test
    public void testGetUsersSizeOnInit(){
        assertSame(1, userService.getUsers().size());
    }

    @Test
    public void testGetUsersValueOnInit(){
        List<User> usersActual = userService.getUsers();

        assertSame(1, usersActual.size());

        User loris = new User();
        loris.setIdUser(12);
        loris.setFirstname("Loris");
        loris.setLastname("Cernich");
        loris.setUsername("lovrenco");
        loris.setPassword("pa$$w0rd");
        loris.setBirthday( LocalDate.of(1991, Month.AUGUST, 13) );
        assertEquals(loris, usersActual.get(0));
    }

    @Test
    public void testGetUsersValueFailureOnInit(){
        List<User> usersExpected = Arrays.asList();
        assertNotSame(usersExpected, userService.getUsers());
    }

}
