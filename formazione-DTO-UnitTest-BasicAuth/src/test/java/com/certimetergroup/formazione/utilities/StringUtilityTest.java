package com.certimetergroup.formazione.utilities;

import com.certimetergroup.formazione.FormazioneApplication;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FormazioneApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StringUtilityTest {

    @Test
    public void testTrueNullAsValue(){
        assertTrue( StringUtility.isEmptyString(null) );
    }
    @Test
    public void testTrueEmptyStringAsValue(){
        assertTrue( StringUtility.isEmptyString("") );
    }
    @Test
    public void testFalse(){
        assertFalse( StringUtility.isEmptyString("test") );
    }

}
