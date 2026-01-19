error id: file://<WORKSPACE>/src/main/java/com/safaria/backend/service/ProfileService.java
file://<WORKSPACE>/src/main/java/com/safaria/backend/service/ProfileService.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[45,1]

error in qdox parser
file content:
```java
offset: 1536
uri: file://<WORKSPACE>/src/main/java/com/safaria/backend/service/ProfileService.java
text:
```scala
// package com.safaria.backend.service;

// import java.util.Optional;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import com.safaria.backend.DTO.UserInfoDTO;
// import com.safaria.backend.entity.TourProvider;
// import com.safaria.backend.entity.Tourist;
// import com.safaria.backend.repository.TourProviderRepository;
// import com.safaria.backend.repository.TouristRepository;

// @Service
// public class ProfileService {

//     @Autowired
//     private TouristRepository touristRepository;
//     @Autowired
//     private TourProviderRepository providerRepository;

//     public UserInfoDTO getUserByIdAndRole(Integer id, String role) {
//         System.out.println(role);
//         switch (role.toLowerCase()) {
//             case "tourist": {
//                 Optional<Tourist> touristOpt = touristRepository.findById(id);
//                 if (touristOpt.isPresent()) {
//                     return new UserInfoDTO(touristOpt.get());
//                 }
//                 break;
//             }
//             case "tourguide":
//             case "company": {
//                 Optional<TourProvider> providerOpt = providerRepository.findById(id);
//                 if (providerOpt.isPresent()) {
//                     return new UserInfoDTO(providerOpt.get());
//                 }
//                 break;
//             }
//             default:
//                 return null;
//         }
//         return null;
//     }

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

QDox parse error in file://<WORKSPACE>/src/main/java/com/safaria/backend/service/ProfileService.java