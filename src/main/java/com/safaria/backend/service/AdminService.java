package com.safaria.backend.service;
import com.safaria.backend.DTO.TourProviderRequestDTO;
import com.safaria.backend.DTO.UserEditDto;
import com.safaria.backend.entity.Admin;
import com.safaria.backend.entity.TourProvider;
import com.safaria.backend.entity.Tourist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
// import com.safaria.backend.repository.AdminRepository;
import com.safaria.backend.repository.TourProviderRepository;
import com.safaria.backend.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class AdminService {
//     @Autowired
//     private AdminRepository adminRepository;
//     @Autowired
//     private TourProviderRepository tourProviderRepository;
//     @Autowired
//     private TouristRepository touristRepository;
//     public Optional<List<TourProviderRequestDTO>> getPendingProviders() {
//         Optional<List<TourProvider>>pendingProviders=this.tourProviderRepository.findByIsApproved(false);
//         List<TourProviderRequestDTO> pending=new ArrayList<TourProviderRequestDTO>();
//         pendingProviders.ifPresent(list -> {
//             for (TourProvider T : list) {
//                 TourProviderRequestDTO t=new TourProviderRequestDTO();
//                 t.setId(T.getUserId());
//                 t.setName(T.getUsername());
//                 t.setEmail(T.getEmail());
//                 t.setType(T.getType()? "tourguide":"company");
//                 t.setDocumentUrl(T.getApprovalDocumentPath());
//                 t.setStatus("pending");
//                 t.setSubmittedAt(T.getDate().toInstant());
//                 pending.add(t);
//             }
//         });
//         return Optional.ofNullable(pending);
//     }

//     //  in admin service
//     public ResponseEntity<String> deleteTourProvider(Integer id) {
//         this.tourProviderRepository.deleteById(id);
//         return ResponseEntity.ok().body("Provider Disapproved");
//     }
//     //  in admin service
//     public ResponseEntity<String> deleteTourist(Integer id) {
//         this.touristRepository.deleteById(id);
//         return ResponseEntity.ok().body("Provider Disapproved");
//     }
//     //  in admin service
//     public ResponseEntity<String> approveTourProvider(Integer id){
//         Optional<TourProvider> tourProvider= this.tourProviderRepository.findById(id);
//         if(tourProvider.isPresent()) {
//             tourProvider.get().setIsApproved(true);
//             this.tourProviderRepository.save(tourProvider.get());
//             return ResponseEntity.ok().body("Tour Provider Approved");
//         }
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tour Provider Not Found");
//     }
//     //  in admin service
//     public List<UserEditDto> getUsers(){
//         Optional<List<TourProvider>>providers= Optional.of(this.tourProviderRepository.findAll());
//         Optional<List<Tourist>>tourists= Optional.of(this.touristRepository.findAll());
//         Optional<List<Admin>>admins= Optional.of(this.adminRepository.findAll());
//         List<UserEditDto> users = new ArrayList<UserEditDto>();

//         providers.ifPresent(list -> {
//             for (TourProvider P : list) {
//                 users.add(new UserEditDto(P));
//             }
//         });

//         tourists.ifPresent(list -> {
//             for (Tourist T : list) {
//                 users.add(new UserEditDto(T));
//             }
//         });
//         admins.ifPresent(list -> {
//             for (Admin A : list) {
//                 users.add(new UserEditDto(A));
//             }
//         });
//         Collections.shuffle(users);
//         return users;
//     }
//     //  in admin service
//     public ResponseEntity<String> updateUser(UserEditDto user,Integer id,Integer role){
//         if(role==1){
//             Optional<Tourist> tourist=this.touristRepository.findById(id);
//             if(tourist.isPresent()){
//                 tourist.get().setUsername(user.getName());
//                 tourist.get().setEmail(user.getEmail());
//                 if(user.getRole() == 1) {
//                     this.touristRepository.save(tourist.get());
//                 }
//                 else if(user.getRole() == 2){
//                     TourProvider provider=new TourProvider(tourist.get(),false);
//                     this.tourProviderRepository.save(provider);
//                     this.touristRepository.deleteById(id);
//                 }
//                 else if(user.getRole() == 3){
//                     TourProvider provider=new TourProvider(tourist.get(),true);
//                     this.tourProviderRepository.save(provider);
//                     this.touristRepository.deleteById(id);
//                 }
//                 else{
//                     Admin admin =new Admin(tourist.get());
//                     this.adminRepository.save(admin);
//                     this.touristRepository.deleteById(id);
//                 }
//                 return ResponseEntity.status(200).body("User Updated");
//             }
//         }
//         else if(role == 2 || role ==3){
//             Optional<TourProvider> provider=this.tourProviderRepository.findById(id);
//             if(provider.isPresent()){
//                 provider.get().setUsername(user.getName());
//                 provider.get().setEmail(user.getEmail());
//                 if(user.getRole()==1){
//                     Tourist tourist=new Tourist(provider.get());
//                     this.touristRepository.save(tourist);
//                     this.tourProviderRepository.deleteById(id);
//                 }
//                 else if(user.getRole() ==4){
//                     Admin admin =new Admin(provider.get());
//                     this.adminRepository.save(admin);
//                     this.tourProviderRepository.deleteById(id);
//                 }
//                 else if(user.getRole() == role) this.tourProviderRepository.save(provider.get());
//                 else {
//                     provider.get().setType(!provider.get().getType());
//                     this.tourProviderRepository.save(provider.get());
//                 }
//                 return ResponseEntity.status(200).body("User Updated");
//             }
//         }
//         else{
//             Optional<Admin> admin =this.adminRepository.findById(id);
//             if (admin.isPresent()) {
//                 admin.get().setUsername(user.getName());
//                 admin.get().setEmail(user.getEmail());

//                 if (user.getRole() == 1) {
//                     this.touristRepository.save(new Tourist(admin.get()));
//                     this.adminRepository.deleteById(id);
//                 } else if (user.getRole() == 2) {
//                     TourProvider provider = new TourProvider(admin.get(), false);
//                     this.tourProviderRepository.save(provider);
//                     this.adminRepository.deleteById(id);
//                 } else if (user.getRole() == 3) {
//                     TourProvider provider = new TourProvider(admin.get(), true);
//                     this.tourProviderRepository.save(provider);
//                     this.adminRepository.deleteById(id);
//                 } else {
//                     this.adminRepository.save(admin.get());
//                 }
//                 return ResponseEntity.status(200).body("User Updated");
//             }
//         }
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Found");
//     }

//     //  in admin service
//     public ResponseEntity<String> addUser(UserEditDto user){
//         if(user.getRole() == 1) this.touristRepository.save(new Tourist(user));
//         else if(user.getRole() == 2 || user.getRole() == 3) this.tourProviderRepository.save(new TourProvider(user));
//         else this.adminRepository.save(new Admin(user));
//         return ResponseEntity.status(200).body("User Added");

//     }
}