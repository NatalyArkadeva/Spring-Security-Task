package com.natya.springSecurityJwtService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natya.springSecurityJwtService.service.JwtTokenService;
import com.natya.springSecurityJwtService.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SpringSecurityJwtServiceApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void getUnsecuredData() throws Exception {

        this.mockMvc.perform(
                        get("/api/v1/all")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Unsecured data"));
    }

    @Test
    public void getUserData() throws Exception {

        this.mockMvc.perform(
                        get("/api/v1/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
