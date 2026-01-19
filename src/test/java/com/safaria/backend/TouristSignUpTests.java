package com.safaria.backend;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safaria.backend.DTO.TouristSignUpDTO;
import com.safaria.backend.entity.Role;
import com.safaria.backend.entity.User;
import com.safaria.backend.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional // each test runs in a transaction and will be rolled back after the test
@ContextConfiguration(classes = TestConfig.class)
public class TouristSignUpTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 1 - invalid email format -> validation failure
    @Test
    @Order(1)
    void testEmailFormat_Invalid() throws Exception {
        TouristSignUpDTO dto = new TouristSignUpDTO();
        dto.setName("John");
        dto.setEmail("invalid-email");
        dto.setPassword("password123");
        mockMvc.perform(post("/api/auth/signup/tourists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    // 2 - password length < 8 -> validation failure
    @Test
    @Order(2)
    void testPasswordTooShort() throws Exception {
        TouristSignUpDTO dto = new TouristSignUpDTO();
        dto.setName("John");
        dto.setEmail("john@example.com");
        dto.setPassword("short"); // < 8
        mockMvc.perform(post("/api/auth/signup/tourists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    // 3 - name not blank -> validation failure
    @Test
    @Order(3)
    void testNameNotBlank() throws Exception {
        TouristSignUpDTO dto = new TouristSignUpDTO();
        dto.setName(""); // blank
        dto.setEmail("john@example.com");
        dto.setPassword("password123");
        mockMvc.perform(post("/api/auth/signup/tourists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    // 4 - name not null -> send explicit null, validation failure
    @Test
    @Order(4)
    void testNameNotNull() throws Exception {
        String json = """
            {"name":null,"email":"john@example.com","password":"password123"}
            """;
        mockMvc.perform(post("/api/auth/signup/tourists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    // 5 - email not null -> send explicit null, validation failure
    @Test
    @Order(5)
    void testEmailNotNull() throws Exception {
        String json = """
            {"name":"John","email":null,"password":"password123"}
            """;
        mockMvc.perform(post("/api/auth/signup/tourists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    // 6 - password not null -> send explicit null, validation failure
    @Test
    @Order(6)
    void testPasswordNotNull() throws Exception {
        String json = """
            {"name":"John","email":"john@example.com","password":null}
            """;
        mockMvc.perform(post("/api/auth/signup/tourists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    // 7 - bad domain (service-level) -> expect BAD_REQUEST if service validates domain
    @Test
    @Order(7)
    void testBadEmailDomain() throws Exception {
        TouristSignUpDTO dto = new TouristSignUpDTO();
        dto.setName("John");
        dto.setEmail("john.doe@g.com"); // example of a bad domain per your requirements
        dto.setPassword("password123");
        mockMvc.perform(post("/api/auth/signup/tourists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    // 8 - email domain not found (service-level) -> expect BAD_REQUEST if service performs DNS check
    @Test
    @Order(8)
    void testEmailDomainNotFound() throws Exception {
        TouristSignUpDTO dto = new TouristSignUpDTO();
        dto.setName("John");
        dto.setEmail("john@nonexistentdomain.example"); // domain unlikely to resolve
        dto.setPassword("password123");
        mockMvc.perform(post("/api/auth/signup/tourists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    // 9 - existing email -> seed a user then attempt signup and expect CONFLICT
    @Test
    @Order(9)
    void testExistingEmail() throws Exception {
        // seed existing user (will be rolled back)
        User existing = new User(null, "Existing", "existing@example.com",
                passwordEncoder.encode("password123"), Role.TOURIST, null, null, null);
        userRepository.save(existing);

        TouristSignUpDTO dto = new TouristSignUpDTO();
        dto.setName("Jane");
        dto.setEmail("existing@example.com");
        dto.setPassword("password123");
        mockMvc.perform(post("/api/auth/signup/tourists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict());
    }

    // 10 - success -> expect 200 and success message
    @Test
    @Order(10)
    void testSuccess() throws Exception {
        TouristSignUpDTO dto = new TouristSignUpDTO();
        dto.setName("Alice");
        dto.setEmail("unique@gmail.com");
        dto.setPassword("password123");
        mockMvc.perform(post("/api/auth/signup/tourists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Tourist registered successfully"));
    }
}
