package com.safaria.backend;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safaria.backend.DTO.UserLoginDTO;
import com.safaria.backend.entity.Country;
import com.safaria.backend.entity.Role;
import com.safaria.backend.entity.TourProvider;
import com.safaria.backend.entity.User;
import com.safaria.backend.repository.TourProviderRepository;
import com.safaria.backend.repository.UserRepository;



@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = TestConfig.class)
public class LogInTests {
    @Autowired
    private  MockMvc mockMvc;
    @Autowired
    private  ObjectMapper objectMapper;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private   TourProviderRepository tourProviderRepository;
    @Autowired
    private   PasswordEncoder passwordEncoder;


    @BeforeAll
    void  fillMySqlDB()
    {  
        userRepository.deleteAll();
        tourProviderRepository.deleteAll();
        User user = new User(null, "Ahmed Ali", "ahmed.provider1@gmail.com",
            passwordEncoder.encode("123456789"), Role.TOUR_PROVIDER, null, null,null);
        user =userRepository.save(user);
        TourProvider tourProvider = new TourProvider(null,user, "Bla Bla Bla", "01555524305",
        "we provide best tours", true, Country.valueOf("Egypt"), LocalDateTime.now());
        tourProviderRepository.save(tourProvider); 
        user = new User(null, "Ahmed Ali", "ahmed.provider2@gmail.com",
            passwordEncoder.encode("123456789"), Role.TOUR_PROVIDER, null, null,null);
        user =userRepository.save(user);
         tourProvider = new TourProvider(null,user, "Bla Bla Bla","01555524305",
        "we provide best tours", false, Country.valueOf("Egypt"), LocalDateTime.now());
        tourProviderRepository.save(tourProvider); 

        user = new User(null, "Ahmed Ali", "ahmed.tourist@gmail.com",
            passwordEncoder.encode("123456789"), Role.TOURIST, null, null,null);
        user = userRepository.save(user);

        user = new User(null, "Ahmed Ali", "ahmed.admin@gmail.com",
            passwordEncoder.encode("123456789"), Role.ADMIN, null, null,null);
        userRepository.save(user);





        
    }

    @Test
    void existingtouristLogin_mustSucceed()  {
        try{
        mockMvc.perform(post("/api/auth/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(new UserLoginDTO("ahmed.tourist@gmail.com","123456789"))))
            .andExpect(status().isOk());
        }catch(Exception e){
            Assertions.fail("Exception thrown during test: " + e.getMessage());
        }

    }
    @Test
    void existingenabledtourproviderLogin_mustSucceed()  {
        try{
        mockMvc.perform(post("/api/auth/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(new UserLoginDTO("ahmed.provider1@gmail.com","123456789"))))
            .andExpect(status().isOk());
            }catch(Exception e){
            Assertions.fail("Exception thrown during test: " + e.getMessage());
        }

    }
    @Test
    void existingdisabledtourproviderLogin_mustFail()  {
        try{
            mockMvc.perform(post("/api/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new UserLoginDTO("ahmed.provider2@gmail.com","123456789"))))
            .andExpect(status().isForbidden());
            }catch(Exception e){
            Assertions.fail("Exception thrown during test: " + e.getMessage());
        }
}    

    @Test
    void existingadminLogin_mustSucceed()  {
        try{
        mockMvc.perform(post("/api/auth/login")
            .contentType("application/json")
           .content(objectMapper.writeValueAsString(new UserLoginDTO("ahmed.admin@gmail.com","123456789"))))
            .andExpect(status().isOk());
        }catch(Exception e){
            Assertions.fail("Exception thrown during test: " + e.getMessage());
        }}
    @Test
     void notExistingUderLogin_mustFail()  {
        try{
        mockMvc.perform(post("/api/auth/login")
            .contentType("application/json")
           .content(objectMapper.writeValueAsString(new UserLoginDTO("ahmed.xxx@gmail.com","123456789"))))
            .andExpect(status().isUnauthorized());
        }catch(Exception e){
            Assertions.fail("Exception thrown during test: " + e.getMessage());
        }}
     @Test    
        void existingUserWrongPasswordLogin_mustFail()  {
             try{
        mockMvc.perform(post("/api/auth/login")
            .contentType("application/json")
           .content(objectMapper.writeValueAsString(new UserLoginDTO("ahmed.tourist@gmail.com","1234s56789"))))
            .andExpect(status().isUnauthorized());
        }catch(Exception e){
            Assertions.fail("Exception thrown during test: " + e.getMessage());
        }}
     @Test
        void nullEmailLogin_mustFail()  {
             try{
        mockMvc.perform(post("/api/auth/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(new UserLoginDTO(null,"123456789"))))
            .andExpect(status().isBadRequest());
        }catch(Exception e){
            Assertions.fail("Exception thrown during test: " + e.getMessage());
        }}
        @Test
        void nullPasswordLogin_mustFail()  {
            try{
        mockMvc.perform(post("/api/auth/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(new UserLoginDTO("ahmed.tourist@gmail.com",null))))
            .andExpect(status().isBadRequest());
        }catch(Exception e){
            Assertions.fail("Exception thrown during test: " + e.getMessage());
        }
        }
        @Test
         void nullEmailAndPasswordLogin_mustFail()  {
            try{
        mockMvc.perform(post("/api/auth/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(new UserLoginDTO(null,null))))
            .andExpect(status().isBadRequest());
        }catch(Exception e){
            Assertions.fail("Exception thrown during test: " + e.getMessage());
        }
        }
        @Test
         void notValidEmailFormLogin_mustFail()  {
              try{
        mockMvc.perform(post("/api/auth/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(new UserLoginDTO("ahmed.touristgmail.com","123456789"))))
            .andExpect(status().isBadRequest());
        }catch(Exception e){
            Assertions.fail("Exception thrown during test: " + e.getMessage());
        }
    }
    @Test
        void lessThan8CharPasswordLogin_mustFail()  {
            try{
        mockMvc.perform(post("/api/auth/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(new UserLoginDTO("ahmed.touristgmail.com","1234567"))))
            .andExpect(status().isBadRequest());
        }catch(Exception e){
            Assertions.fail("Exception thrown during test: " + e.getMessage());
        }}
            


            
            

             



        
        }
