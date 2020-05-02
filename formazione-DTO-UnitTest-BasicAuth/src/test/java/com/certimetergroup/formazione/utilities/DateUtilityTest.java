package com.certimetergroup.formazione.utilities;

import com.certimetergroup.formazione.FormazioneApplication;
import com.certimetergroup.formazione.exception.FailureException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FormazioneApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DateUtilityTest {

    @Test
    public void testSuccess(){
        LocalDate today = LocalDate.of(2020, Month.APRIL, 4);
        LocalDate birthday = LocalDate.of(1991, Month.AUGUST, 13);
        assertSame( 28, DateUtility.calculateAgeOf(birthday, today) );

    }

    @Test
    public void testBirthDayNullValue(){
        LocalDate today = LocalDate.of(2020, Month.APRIL, 4);
        LocalDate birthday = null;
        assertNull( DateUtility.calculateAgeOf(birthday, today) );
    }

    @Test
    public void testTodayNullValue(){
        LocalDate today = null;
        LocalDate birthday = LocalDate.of(1991, Month.AUGUST, 13);
        assertThrows( FailureException.class, ()->DateUtility.calculateAgeOf(birthday, today) );
    }

}
