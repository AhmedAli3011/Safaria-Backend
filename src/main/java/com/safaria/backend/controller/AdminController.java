// package com.safaria.backend.controller;

// import com.safaria.backend.service.AdminService;
// import com.safaria.backend.DTO.TourProviderRequestDTO;
// import com.safaria.backend.DTO.UserEditDto;

// import jakarta.validation.Valid;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;
// import java.util.Optional;

// @CrossOrigin(origins = "http://localhost:8080")
// @RestController
// @PreAuthorize("hasRole('ADMIN')")
// @RequestMapping("/admin")
// public class AdminController {

//     @Autowired
//     AdminService adminService;

//     @GetMapping("/tour-providers/requests")
//     public Optional<List<TourProviderRequestDTO>> getPendingProviders() {
//         return this.adminService.getPendingProviders();
//     }

//     @DeleteMapping("/tour-providers/reject{id}")
//     public ResponseEntity<String> deleteTourProvider(@PathVariable Integer id) {
//         return this.adminService.deleteTourProvider(id);
//     }

//     @DeleteMapping("/tourist/delete{id}")
//     public ResponseEntity<String> deleteTourist(@PathVariable Integer id) {
//         return this.adminService.deleteTourist(id);
//     }

//     @PostMapping("/tour-providers/approve{id}")
//     public ResponseEntity<String> approveTourProvider(@PathVariable Integer id) {
//         return this.adminService.approveTourProvider(id);
//     }

//     @GetMapping("/getUsers")
//     public List<UserEditDto> getUsers() {
//         return this.adminService.getUsers();
//     }

//     @PutMapping("/UpdateUser/{id}/{role}")
//     public ResponseEntity<String> updateTourist(@Valid @RequestBody UserEditDto user, @PathVariable Integer id, @PathVariable Integer role) {
//         return this.adminService.updateUser(user, id, role);
//     }

//     @PostMapping("/addUser/")
//     public ResponseEntity<String> addUser(@Valid @RequestBody UserEditDto user) {
//         return this.adminService.addUser(user);
//     }

//     // @PostMapping("/addReport/")
//     // public ResponseEntity<String> addReport(@Valid @RequestBody ReportDTO reportDTO) {
//     //     return this..addReport(reportDTO);
//     // }
//     // @GetMapping("/getReports")
//     // public ResponseEntity<List<ReportDTO>> getReports() {
//     //     return this..getReports();
//     // }
//     // @DeleteMapping("/deleteReport/{report_id}")
//     // public ResponseEntity<String> deleteReport(@PathVariable Integer report_id) {
//     //     return this..deleteReport(report_id);
//     // }
// }
