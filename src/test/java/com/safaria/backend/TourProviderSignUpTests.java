package com.safaria.backend;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.safaria.backend.entity.Role;
import com.safaria.backend.entity.TourProvider;
import com.safaria.backend.entity.User;
import com.safaria.backend.repository.TourProviderRepository;
import com.safaria.backend.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
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
public class TourProviderSignUpTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TourProviderRepository tourProviderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${file.upload-dir}")
    private String uploadDir;

    // 1 - invalid email format -> validation failure
    @Test
    @Order(1)
    void testEmailFormat_Invalid() throws Exception {
       MockMultipartFile businessLicense =
            new MockMultipartFile(
                    "businessLicense",
                    "license.pdf",
                    "application/pdf",
                    "dummy-content".getBytes()
            );

    mockMvc.perform(
            multipart("/api/auth/signup/tour-providers")
                    .file(businessLicense)
                    .param("name", "John")
                    .param("email", "invalid-email") // ❌ invalid
                    .param("password", "password123")
                    .param("phone", "01234567890")
                    .param("country", "Egypt")
                    .param("description", "Tour provider description")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
    )
    .andExpect(status().isBadRequest());
    }

    // 2 - password length < 8 -> validation failure
    @Test
    @Order(2)
    void testPasswordTooShort() throws Exception {
        MockMultipartFile businessLicense =
            new MockMultipartFile(
                    "businessLicense",
                    "license.pdf",
                    "application/pdf",
                    "dummy-content".getBytes()
            );

    mockMvc.perform(
            multipart("/api/auth/signup/tour-providers")
                    .file(businessLicense)
                    .param("name", "John")
                    .param("email", "ahmed.moh3011@gmail.com") // ❌ invalid
                    .param("password", "pass")
                    .param("phone", "01234567890")
                    .param("country", "Egypt")
                    .param("description", "Tour provider description")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
    )
    .andExpect(status().isBadRequest());
    }

    // 3 - name not blank -> validation failure
    @Test
    @Order(3)
    void testNameNotBlank() throws Exception {
       MockMultipartFile businessLicense =
            new MockMultipartFile(
                    "businessLicense",
                    "license.pdf",
                    "application/pdf",
                    "dummy-content".getBytes()
            );

    mockMvc.perform(
            multipart("/api/auth/signup/tour-providers")
                    .file(businessLicense)
                    .param("name", "")
                    .param("email", "ahmed.moh3011@gmail.com") // ❌ invalid
                    .param("password", "password123")
                    .param("phone", "01234567890")
                    .param("country", "Egypt")
                    .param("description", "Tour provider description")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
    )
    .andExpect(status().isBadRequest());
    }

    
    // 7 - bad domain (service-level) -> expect BAD_REQUEST if service validates domain
    @Test
    @Order(7)
    void testBadEmailDomain() throws Exception {
     MockMultipartFile businessLicense =
            new MockMultipartFile(
                    "businessLicense",
                    "license.pdf",
                    "application/pdf",
                    "dummy-content".getBytes()
            );

    mockMvc.perform(
            multipart("/api/auth/signup/tour-providers")
                    .file(businessLicense)
                    .param("name", "John")
                    .param("email", "ahmed.moh3011@g.mail.com") // ❌ invalid
                    .param("password", "password123")
                    .param("phone", "01234567890")
                    .param("country", "Egypt")
                    .param("description", "Tour provider description")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
    )
    .andExpect(status().isBadRequest());
    }

    // 8 - email domain not found (service-level) -> expect BAD_REQUEST if service performs DNS check
    @Test
    @Order(8)
    void testEmailDomainNotFound() throws Exception {
        MockMultipartFile businessLicense =
            new MockMultipartFile(
                    "businessLicense",
                    "license.pdf",
                    "application/pdf",
                    "dummy-content".getBytes()
            );

    mockMvc.perform(
            multipart("/api/auth/signup/tour-providers")
                    .file(businessLicense)
                    .param("name", "John")
                    .param("email", "ahmed.moh3011@notexsgsfist.com") // ❌ invalid
                    .param("password", "password123")
                    .param("phone", "01234567890")
                    .param("country", "Egypt")
                    .param("description", "Tour provider description")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
    )
    .andExpect(status().isBadRequest());
    }

    // 9 - existing email -> seed a user then attempt signup and expect CONFLICT
    @Test
    @Order(9)
    void testExistingEmail() throws Exception {
        // seed existing user (will be rolled back)
        User existing = new User(null, "Existing", "existing@gmail.com",
                passwordEncoder.encode("password123"), Role.TOUR_PROVIDER, null, null, null);
        userRepository.save(existing);

  MockMultipartFile businessLicense =
            new MockMultipartFile(
                    "businessLicense",
                    "license.pdf",
                    "application/pdf",
                    "dummy-content".getBytes()
            );

    mockMvc.perform(
            multipart("/api/auth/signup/tour-providers")
                    .file(businessLicense)
                    .param("name", "John")
                    .param("email", "existing@gmail.com") // ❌ invalid
                    .param("password", "password123")
                    .param("phone", "01234567890")
                    .param("country", "Egypt")
                    .param("description", "Tour provider description")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
    )
    .andExpect(status().isConflict());
    }

    // 10 - success -> expect 200 and success message
    @Test
    @Order(10)
    void testSuccess() throws Exception {
        MockMultipartFile businessLicense =
            new MockMultipartFile(
                    "businessLicense",
                    "license.pdf",
                    "application/pdf",
                    "dummy-content".getBytes()
            );

        String email = "ahmed.moh3011@gmail.com";

        mockMvc.perform(
                multipart("/api/auth/signup/tour-providers")
                        .file(businessLicense)
                        .param("name", "John")
                        .param("email", email)
                        .param("password", "password123")
                        .param("phone", "01234567890")
                        .param("country", "Egypt")
                        .param("description", "Tour provider description")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        )
        .andExpect(status().isOk());

        // Check that the file path in the database exists in the filesystem
        User user = userRepository.findByEmail(email).orElseThrow();
        TourProvider tourProvider = tourProviderRepository.findByUserId(user.getId())
        .orElseThrow();
        // tourProviderRepository.findByUserId(user.getId()).orElse(null);
        if (tourProvider == null) {
            Assertions.fail("TourProvider should not be null");
        }
        // Assuming the User entity or a related entity has a getBusinessLicensePath() method
        String licensePath = tourProvider.getApprovalDocumentPath();
        assertNotNull(licensePath, "Business license path should not be null");
        System.out.println("License Path: " + uploadDir + licensePath);
        java.nio.file.Path path = java.nio.file.Paths.get(uploadDir+ "/" + licensePath);
        assertTrue(java.nio.file.Files.exists(path), "Business license file should exist in filesystem: " + licensePath);
    }
}