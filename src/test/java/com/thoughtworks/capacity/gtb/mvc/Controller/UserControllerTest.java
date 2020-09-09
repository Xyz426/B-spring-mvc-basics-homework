package com.thoughtworks.capacity.gtb.mvc.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capacity.gtb.mvc.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldSuccessAddUser() throws Exception {
        User user = User.builder().username("hello").password("21321").email("xx@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/register")
                .content(jsonUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldFailAddUser() throws Exception {
        User user = User.builder().username("hello").password("2").email("xx@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/register")
                .content(jsonUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("密码不合法"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldSuccessLogin() throws Exception {
        User user = User.builder().username("world").password("21321").email("xx@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/register")
                .content(jsonUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/login?username=world&password=21321")
                .content(jsonUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("world"))
                .andExpect(jsonPath("$.email").value("xx@qq.com"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailLogin() throws Exception {
        User user = User.builder().username("q12345").password("21321").email("xx@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/register")
                .content(jsonUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/login?username=q1234&password=21321")
                .content(jsonUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("用户名或密码错误"))
                .andExpect(status().isBadRequest());
    }
}
