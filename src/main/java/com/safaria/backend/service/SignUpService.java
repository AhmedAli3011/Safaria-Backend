package com.safaria.backend.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.safaria.backend.CustomExceptions.AlreadyExistsException;
import com.safaria.backend.CustomExceptions.InvalidDomainException;
import com.safaria.backend.DTO.TourProviderRequestDTO;
import com.safaria.backend.DTO.TouristSignUpDTO;
import com.safaria.backend.entity.Role;
import com.safaria.backend.entity.TourProvider;
import com.safaria.backend.entity.Tourist;
import com.safaria.backend.entity.User;
import com.safaria.backend.repository.TourProviderRepository;
import com.safaria.backend.repository.TouristRepository;
import com.safaria.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SignUpService {

    private final TouristRepository touristRepository;
    private final TourProviderRepository tourProviderRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CheckEmailService checkEmailService;
    private final FileSystemService fileSystemService;
    
    public void touristSignUp(TouristSignUpDTO touristSignUpDTO) {
        String domain = touristSignUpDTO.getEmail().substring(touristSignUpDTO.getEmail().indexOf("@") + 1);
        if (!checkEmailService.isValidEmailDomain(domain)) 
           throw new InvalidDomainException("Invalid email domain :"+domain);
        
        if (userRepository.existsByEmail(touristSignUpDTO.getEmail())) {
            throw new AlreadyExistsException("Email is already Used!");
        }
        
        User user = new User(null, touristSignUpDTO.getName(), touristSignUpDTO.getEmail(), passwordEncoder.encode(touristSignUpDTO.getPassword()),
                Role.TOURIST, null, null,null);
        userRepository.save(user);
        Tourist  tourist = new Tourist(null, user, touristSignUpDTO.getNationality());   
        touristRepository.save(tourist);

    }

    public void tourProviderSignUp(TourProviderRequestDTO tourProviderRequestDTO) {
        String domain = tourProviderRequestDTO.getEmail().substring(tourProviderRequestDTO.getEmail().indexOf("@") + 1);
        if (!checkEmailService.isValidEmailDomain(domain)) 
           throw new InvalidDomainException("Invalid email domain :"+domain);
        if (userRepository.existsByEmail(tourProviderRequestDTO.getEmail())) 
           throw new AlreadyExistsException("Email is already Used!");
        String directory = "Documents/TourProvider";
        String uniqueFileName="";
        if (fileSystemService.isPdf(tourProviderRequestDTO.getBusinessLicense())) 
            uniqueFileName = this.fileSystemService.generateUniqueFileName(directory, "pdf");
        else if(fileSystemService.isJpeg(tourProviderRequestDTO.getBusinessLicense()))
            uniqueFileName = this.fileSystemService.generateUniqueFileName(directory, "jpg");
        else
            throw new IllegalArgumentException("Business License must be a PDF or an JPEG file.");    
        String relativeFilePath = Paths.get(directory, uniqueFileName).toString();
        try{
        this.fileSystemService.storeFile(tourProviderRequestDTO.getBusinessLicense().getBytes(),relativeFilePath);
        }
        catch(IOException ex)
        {
            throw new RuntimeException("Failed to turn file to bytes ", ex);
        }
        User user = new User(null, tourProviderRequestDTO.getName(), tourProviderRequestDTO.getEmail(),
            passwordEncoder.encode(tourProviderRequestDTO.getPassword()), Role.TOUR_PROVIDER, null, null,null);
        userRepository.save(user);
        TourProvider tourProvider = new TourProvider(null,user, relativeFilePath, tourProviderRequestDTO.getPhone(),
        tourProviderRequestDTO.getDescription(), false, tourProviderRequestDTO.getCountry(), LocalDateTime.now());
        tourProviderRepository.save(tourProvider);  
        
       

        

    }
    
}
