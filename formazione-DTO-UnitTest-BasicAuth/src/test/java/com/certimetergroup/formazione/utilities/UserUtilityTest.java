package com.certimetergroup.formazione.utilities;

import com.certimetergroup.formazione.FormazioneApplication;
import com.certimetergroup.formazione.dto.DTOUser;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FormazioneApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserUtilityTest {


    @Test
    public void testTrue() {
        DTOUser dtoUser = new DTOUser(null, "Loris", "Cernich", LocalDate.of(1991, Month.AUGUST, 13), "lovrenco", 28 );
        assertTrue( UserUtility.isValidForInsert(dtoUser) );
    }

    @Test
    public void testFalseIdUserNotNull() {
        DTOUser dtoUser = new DTOUser(12, "Loris", "Cernich", LocalDate.of(1991, Month.AUGUST, 13), "lovrenco", 28);
        assertFalse( UserUtility.isValidForInsert(dtoUser) );
    }
    @Test
    public void testFalseFirstnameNull() {
        DTOUser dtoUser = new DTOUser(null, null, "Cernich", LocalDate.of(1991, Month.AUGUST, 13), "lovrenco", 28);
        assertFalse( UserUtility.isValidForInsert(dtoUser) );
    }
    @Test
    public void testFalseLastnameNull() {
        DTOUser dtoUser = new DTOUser(null, "Loris", null, LocalDate.of(1991, Month.AUGUST, 13), "lovrenco", 28);
        assertFalse( UserUtility.isValidForInsert(dtoUser) );
    }
    @Test
    public void testFalseFirstnameEmptyString() {
        DTOUser dtoUser = new DTOUser(null, "", "Cernich", LocalDate.of(1991, Month.AUGUST, 13), "lovrenco", 28);
        assertFalse( UserUtility.isValidForInsert(dtoUser) );
    }
    @Test
    public void testFalseLastnameEmptyString() {
        DTOUser dtoUser = new DTOUser(null, "Loris", "", LocalDate.of(1991, Month.AUGUST, 13), "lovrenco", 28);
        assertFalse( UserUtility.isValidForInsert(dtoUser) );
    }
}
