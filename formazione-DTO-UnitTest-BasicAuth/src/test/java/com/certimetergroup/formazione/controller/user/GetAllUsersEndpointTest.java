package com.certimetergroup.formazione.controller.user;

import com.certimetergroup.formazione.FormazioneApplication;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FormazioneApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetAllUsersEndpointTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wepAppContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wepAppContext).build();
    }

    private final String ENDPOINT_RESOURCE_BASE_URL = "/users";

    /**********     RESPONSE json    **********/
    private final String getAllUsersOnInitResponseJSON = "{" +
            "\"code\":0," +
            "\"message\":\"SUCCESS\"," +
            "\"users\":[" +
                "{\"idUser\":12,\"firstname\":\"Loris\",\"lastname\":\"Cernich\",\"birthday\":\"1991-08-13\",\"age\":28}" +
            "],\"size\":1" +
        "}";



    @Test
    public void successOnInit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                //response
                .andExpect(jsonPath("$.code").exists())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("SUCCESS"))
                .andExpect(jsonPath("$.size").exists())
                .andExpect(jsonPath("$.size").value(1))
                .andExpect(jsonPath("$.users").exists())
                .andExpect(jsonPath("$.users").isArray())
                .andExpect(jsonPath("$.users", hasSize(1)))
                // 'users'
                .andExpect(jsonPath("$.users[0].idUser").exists())
                .andExpect(jsonPath("$.users[0].idUser").value(12))
                .andExpect(jsonPath("$.users[0].firstname").exists())
                .andExpect(jsonPath("$.users[0].firstname").value("Loris"))
                .andExpect(jsonPath("$.users[0].lastname").exists())
                .andExpect(jsonPath("$.users[0].lastname").value("Cernich"))
                .andExpect(jsonPath("$.users[0].birthday").exists())
                .andExpect(jsonPath("$.users[0].birthday").value("1991-08-13"))
                .andExpect(jsonPath("$.users[0].age").exists())
                .andExpect(jsonPath("$.users[0].age").value(28))

                .andDo(print());
    }

    @Test
    public void successOnInitAlternative() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                //response
                .andExpect(content().json(getAllUsersOnInitResponseJSON))

                .andDo(print());
    }

}
