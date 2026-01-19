error id: file://<WORKSPACE>/src/main/java/com/safaria/backend/controller/AdminController.java
file://<WORKSPACE>/src/main/java/com/safaria/backend/controller/AdminController.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[72,1]

error in qdox parser
file content:
```java
offset: 2688
uri: file://<WORKSPACE>/src/main/java/com/safaria/backend/controller/AdminController.java
text:
```scala
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
@@
```

```



#### Error stacktrace:

```
com.thoughtworks.qdox.parser.impl.Parser.yyerror(Parser.java:2025)
	com.thoughtworks.qdox.parser.impl.Parser.yyparse(Parser.java:2147)
	com.thoughtworks.qdox.parser.impl.Parser.parse(Parser.java:2006)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:232)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:190)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:94)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:89)
	com.thoughtworks.qdox.library.SortedClassLibraryBuilder.addSource(SortedClassLibraryBuilder.java:162)
	com.thoughtworks.qdox.JavaProjectBuilder.addSource(JavaProjectBuilder.java:174)
	scala.meta.internal.mtags.JavaMtags.indexRoot(JavaMtags.scala:49)
	scala.meta.internal.metals.SemanticdbDefinition$.foreachWithReturnMtags(SemanticdbDefinition.scala:99)
	scala.meta.internal.metals.Indexer.indexSourceFile(Indexer.scala:546)
	scala.meta.internal.metals.Indexer.$anonfun$indexWorkspaceSources$7(Indexer.scala:366)
	scala.meta.internal.metals.Indexer.$anonfun$indexWorkspaceSources$7$adapted(Indexer.scala:361)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:630)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:628)
	scala.collection.AbstractIterator.foreach(Iterator.scala:1313)
	scala.collection.parallel.ParIterableLike$Foreach.leaf(ParIterableLike.scala:938)
	scala.collection.parallel.Task.$anonfun$tryLeaf$1(Tasks.scala:52)
	scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
	scala.util.control.Breaks$$anon$1.catchBreak(Breaks.scala:97)
	scala.collection.parallel.Task.tryLeaf(Tasks.scala:55)
	scala.collection.parallel.Task.tryLeaf$(Tasks.scala:49)
	scala.collection.parallel.ParIterableLike$Foreach.tryLeaf(ParIterableLike.scala:935)
	scala.collection.parallel.AdaptiveWorkStealingTasks$AWSTWrappedTask.internal(Tasks.scala:169)
	scala.collection.parallel.AdaptiveWorkStealingTasks$AWSTWrappedTask.internal$(Tasks.scala:156)
	scala.collection.parallel.AdaptiveWorkStealingForkJoinTasks$AWSFJTWrappedTask.internal(Tasks.scala:304)
	scala.collection.parallel.AdaptiveWorkStealingTasks$AWSTWrappedTask.compute(Tasks.scala:149)
	scala.collection.parallel.AdaptiveWorkStealingTasks$AWSTWrappedTask.compute$(Tasks.scala:148)
	scala.collection.parallel.AdaptiveWorkStealingForkJoinTasks$AWSFJTWrappedTask.compute(Tasks.scala:304)
	java.base/java.util.concurrent.RecursiveAction.exec(RecursiveAction.java:194)
	java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:387)
	java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1310)
	java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1841)
	java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1806)
	java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:188)
```
#### Short summary: 

QDox parse error in file://<WORKSPACE>/src/main/java/com/safaria/backend/controller/AdminController.java