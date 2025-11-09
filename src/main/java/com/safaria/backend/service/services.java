package com.safaria.backend.service;

import com.safaria.backend.DTO.*;
import com.safaria.backend.entity.*;
import com.safaria.backend.repository.*;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Service
public class services {

   

    public static String decryptAES(String encryptedText) {
        String SECRET_KEY = "dsvbsduf76A1xZ9g";
        String IV = "1234567890123456";
        try {
            // Base64 decode the encrypted text
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

            // Initialize AES Cipher (CBC mode, PKCS5Padding)
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes("UTF-8"));

            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            // Perform decryption
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isPdfOrImage(MultipartFile file) {
        // Get content type from the file
        String contentType = file.getContentType();

        // Check for PDF or image MIME types
        if (contentType != null) {
            if (contentType.equals("application/pdf")) {
                return true; // It's a PDF
            } else if (contentType.startsWith("image/")) {
                return false; // It's an image (jpeg, png, gif, etc.)
            }
        }
        return false; // Neither PDF nor image
    }

    @Autowired
    private TouristRepository touristRepository;
    @Autowired
    private TourProviderRepository tourProviderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CheckEmailService checkEmailService;
    @Autowired
    private FileSystemService fileSystemService;
    // @Autowired
    // private ChatRepository chatRepository;

    // @Autowired
    // private ReportRepository reportRepository;
    // @Autowired
    // private BlogRepository blogRepository;
    // @Autowired
    // private BlogReviewRepository blogReviewRepository;
    // @Autowired
    // private RewardRepository rewardRepository;
    // @Override
    // public ResponseEntity<UserInfoDTO> touristlogin(String email, String
    // password) {
    // Optional<Tourist> tourist = touristRepository.findByEmail(email);
    // if (tourist.isPresent()) {
    // if (passwordEncoder.matches(password, tourist.get().getPassword())) {
    // UserInfoDTO dto = new UserInfoDTO(tourist.get());
    // dto.setType("Tourist");
    // return ResponseEntity.status(200).body(dto);
    // } else {
    // return ResponseEntity.status(404).build();
    // }
    // }
    // return ResponseEntity.status(404).build();
    // }
    // @Override
    // public ResponseEntity<UserInfoDTO> tourProviderlogin(String email, String
    // password) {
    // Optional<TourProvider> tourProvider =
    // tourProviderRepository.findByEmail(email);
    // if (tourProvider.isPresent()) {
    // if (passwordEncoder.matches(password, tourProvider.get().getPassword())) {
    // UserInfoDTO dto = new UserInfoDTO(tourProvider.get());
    // if(tourProvider.get().getType())
    // dto.setType("Tour Guide");
    // else
    // dto.setType("Company");
    // return ResponseEntity.status(200).body(dto);
    // } else {
    // return ResponseEntity.status(404).build();
    // }
    // public ResponseEntity<Admin> adminlogin(String email, String password) {

    //     // email=URLDecoder.decode(email, StandardCharsets.UTF_8);
    //     // password=URLDecoder.decode(password, StandardCharsets.UTF_8);
    //     // try {
    //     // email=decryptAES(email);
    //     // password=decryptAES(password);
    //     // }catch (Exception e){System.out.println(e.getMessage());}
    //     Optional<Admin> admin = adminRepository.findByEmail(email);
    //     if (admin.isPresent()) {
    //         if (passwordEncoder.matches(password, admin.get().getPassword())) {

    //             return ResponseEntity.status(200).body(admin.get());
    //         } else {
    //             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //         }

    //     }
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

    // }

    
    // in admin service

    // @Override
    // public ResponseEntity<List<MessageDTO>> getMessages(MessageRequestDTO
    // requestDTO) {
    // Optional<List<Chat>> chats =
    // Optional.ofNullable(this.chatRepository.findMessagesBetweenUsers(requestDTO.getUsername1(),
    // requestDTO.getUsername2()));
    // List<MessageDTO> messages = chats.get().stream()
    // .map(chat -> new MessageDTO(chat))
    // .collect(Collectors.toList());
    // return ResponseEntity.status(200).body(messages);
    // }
    // @Override
    // public ResponseEntity<String> setMessage(MessageDTO messageDTO) {
    // Chat chat = new Chat(messageDTO);
    // this.chatRepository.save(chat);
    // return ResponseEntity.status(200).body("message saved");
    // }
    // @Override
    // public ResponseEntity<String> deleteMessage(Integer message_id) {
    // this.chatRepository.deleteById(message_id);
    // return ResponseEntity.status(200).body("message deleted");
    // }
    // @Override
    // public ResponseEntity<String> addReport(ReportDTO reportDTO) {
    // Report report = new Report(reportDTO);
    // this.reportRepository.save(report);
    // return ResponseEntity.status(200).body("report saved");
    // }
    // @Override
    // public ResponseEntity<List<ReportDTO>> getReports() {
    // Optional<List<Report>> reports =
    // Optional.ofNullable(this.reportRepository.findAll());
    // if (reports.isPresent()) {
    // List<ReportDTO> reportsDTO = reports.get().stream()
    // .map(report -> new ReportDTO((Report) report))
    // .collect(Collectors.toList());
    // return ResponseEntity.status(200).body(reportsDTO);
    // }
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    // }
    // @Override
    // public ResponseEntity<String> deleteReport(Integer reportId) {
    // this.reportRepository.deleteById(reportId);
    // return ResponseEntity.status(200).body("report deleted");
    // }
    // public String[] saveBlogPhotos(MultipartFile[] photos) {
    // String directory = "Documents/Blogs";
    // String[] relativeFilePaths = new String[photos.length];
    // int index = 0;
    // for (MultipartFile photo : photos) {
    // String uniqueFileName =
    // this.fileSystemService.generateUniqueFileName(directory, "jpg");
    // String relativeFilePath = Paths.get(directory, uniqueFileName).toString();
    // try {
    // this.fileSystemService.storeFile(photo.getBytes(), relativeFilePath);
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // relativeFilePaths[index++] = relativeFilePath;
    // }
    // return relativeFilePaths;
    // }
    // @Override
    // public ResponseEntity<String> addBlog(BlogDTO blogDTO) {
    // if (blogDTO.getBlogId() != null) {
    // Optional<Blog> blog = this.blogRepository.findById(blogDTO.getBlogId());
    // if (blog.isPresent()) {
    // if (blogDTO.getContent() != null) {
    // blog.get().setContent(blogDTO.getContent());
    // }
    // if (blogDTO.getPhoto() != null) {
    // blog.get().setPhoto(saveBlogPhotos(blogDTO.getPhoto()));
    // }
    // this.blogRepository.save(blog.get());
    // return ResponseEntity.status(200).body("blog edited");
    // }
    // }
    // if (blogDTO.getPhoto() == null) {
    // this.blogRepository.save(new Blog(blogDTO, null));
    // return ResponseEntity.status(200).body("blog saved");
    // }
    // this.blogRepository.save(new Blog(blogDTO,
    // saveBlogPhotos(blogDTO.getPhoto())));
    // return ResponseEntity.status(200).body("blog saved");
    // }
    // @Override
    // public ResponseEntity<List<BlogDTO>> getBlogs() {
    // Optional<List<Blog>> blogs =
    // Optional.ofNullable(this.blogRepository.findAll());
    // if (blogs.isPresent()) {
    // List<BlogDTO> dtos = blogs.get().stream()
    // .map(blog -> new BlogDTO(blog))
    // .collect(Collectors.toList());
    // return ResponseEntity.status(200).body(dtos);
    // }
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    // }
    // @Override
    // public ResponseEntity<String> addReview(BlogReviewDTO blogReviewDTO) {
    // this.blogReviewRepository.save(new BlogReview(blogReviewDTO));
    // if (blogReviewDTO.getRole().toString() == "Tourist") {
    // System.out.println("1");
    // Optional<Reward> reward =
    // Optional.ofNullable(this.rewardRepository.findByTouristUsername(blogReviewDTO.getUsername()));
    // if (reward.isPresent()) {
    // System.out.println("2");
    // reward.get().setPoints(reward.get().getPoints() + 1);
    // reward.get().setActivityType(Reward.ActivityType.valueOf("Review"));
    // this.rewardRepository.save(reward.get());
    // } else {
    // System.out.println("3");
    // Reward new_reward = new Reward(blogReviewDTO.getUsername(),
    // Reward.ActivityType.valueOf("Review"), 1);
    // this.rewardRepository.save(new_reward);
    // }
    // }
    // return ResponseEntity.status(200).body("review saved");
    // }
    // @Override
    // public ResponseEntity<List<BlogReviewDTO>> getReviews(Integer blog_id) {
    // Optional<List<BlogReview>> reviews =
    // Optional.ofNullable(this.blogReviewRepository.findByBlogId(blog_id));
    // if (reviews.isPresent()) {
    // List<BlogReviewDTO> dtos = reviews.get().stream()
    // .map(review -> new BlogReviewDTO(review))
    // .collect(Collectors.toList());
    // return ResponseEntity.status(200).body(dtos);
    // }
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    // }
    // @Override
    // public ResponseEntity<List<ChatDTO>> getChats(String username) {
    // Optional<List<Chat>> chats =
    // Optional.ofNullable(this.chatRepository.findMessagesByUsername(username));
    // List<ChatDTO> dtos = new ArrayList<ChatDTO>();
    // if (chats.isPresent()) {
    // HashMap<String, List<Chat>> map = new HashMap<String, List<Chat>>();
    // for (Chat c : chats.get()) {
    // String name = c.getSender_username().equals(username) ?
    // c.getReceiver_username() : c.getSender_username();
    // if (map.containsKey(name)) {
    // map.get(name).add(c);
    // }else {
    // map.put(name, new ArrayList<>(List.of(c)));
    // }
    // }
    // for (Map.Entry<String, List<Chat>> entry : map.entrySet()) {
    // dtos.add(new ChatDTO(entry.getKey(), entry.getValue()));
    // }
    // }
    // return ResponseEntity.status(200).body(dtos);
    // }
    // @Override
    // public ResponseEntity<List<BlogDTO>> getUserBlogs(String username) {
    // Optional<List<Blog>> blogs =
    // Optional.ofNullable(this.blogRepository.findByUsername(username));
    // List<BlogDTO> blogDTOs = blogs.get().stream()
    // .map(blog -> new BlogDTO(blog))
    // .collect(Collectors.toList());
    // return ResponseEntity.status(200).body(blogDTOs);
    // }
    // @Override
    // public ResponseEntity<String> deleteBlog(Integer blog_id) {
    // this.blogRepository.deleteById(blog_id);
    // return ResponseEntity.status(200).body("blog deleted");
    // }
    // @Override
    // public ResponseEntity<BlogDTO> getBlog(Integer blog_ig) {
    // Optional<Blog> blog = this.blogRepository.findById(blog_ig);
    // if (blog.isPresent()) {
    // BlogDTO blogDTO = new BlogDTO(blog.get());
    // return ResponseEntity.status(200).body(blogDTO);
    // }
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    // }
}
// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
// }
// }
