package com.certimetergroup.formazione.controller.user;

import com.certimetergroup.formazione.FormazioneApplication;
import com.certimetergroup.formazione.enumeration.ResponseErrorEnum;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FormazioneApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InsertNewUserEndpointTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wepAppContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wepAppContext).build();
    }

    private final String ENDPOINT_RESOURCE_BASE_URL = "/users";

    /**********     REQUEST json    **********/
    private final String marioRossiInsertRequestBodyJSON = "{\"idUser\":null,\"firstname\":\"Mario\",\"lastname\":\"Rossi\",\"birthday\":\"1959-09-10\",\"age\":null}";
    private final String lucaVerdiInsertFailureForEmptyFieldRequestBodyJSON = "{\"idUser\":null,\"firstname\":null,\"lastname\":\"Verdi\",\"birthday\":\"2012-12-12\",\"age\":null}";

    /**********     RESPONSE json    **********/
    private final String insertNewUserMarioRossiOnInitResponseJSON = "{" +
            "\"code\":0," +
            "\"message\":\"SUCCESS\"," +
            "\"userInserted\": {\"idUser\":13,\"firstname\":\"Mario\",\"lastname\":\"Rossi\",\"birthday\":\"1959-09-10\",\"age\":60}" + ", " +
            "\"users\":[" +
                "{\"idUser\":12,\"firstname\":\"Loris\",\"lastname\":\"Cernich\",\"birthday\":\"1991-08-13\",\"age\":28}" + ", " +
                "{\"idUser\":13,\"firstname\":\"Mario\",\"lastname\":\"Rossi\",\"birthday\":\"1959-09-10\",\"age\":60}" +
            "]," +
            "\"size\":2" +
        "}";
    private final String insertNewUserMarioRossiOnInitInvalidFieldResponseJSON = "{\"code\":"+ ResponseErrorEnum.ERR_1.id +", \"message\":\""+ ResponseErrorEnum.ERR_1.description +"\"}";



    @Test
    public void successOnInit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_RESOURCE_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content( marioRossiInsertRequestBodyJSON )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //response
                .andExpect(content().json( insertNewUserMarioRossiOnInitResponseJSON ))
                .andDo(print());
    }

    @Test
    public void failureForEmptyField() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_RESOURCE_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content( lucaVerdiInsertFailureForEmptyFieldRequestBodyJSON )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                //response
                .andExpect(content().json( insertNewUserMarioRossiOnInitInvalidFieldResponseJSON ))
                .andDo(print());
    }

}
