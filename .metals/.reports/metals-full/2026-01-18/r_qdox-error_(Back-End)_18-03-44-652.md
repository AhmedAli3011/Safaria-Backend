error id: file://<WORKSPACE>/src/main/java/com/safaria/backend/DTO/UserInfoDTO.java
file://<WORKSPACE>/src/main/java/com/safaria/backend/DTO/UserInfoDTO.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[64,1]

error in qdox parser
file content:
```java
offset: 2006
uri: file://<WORKSPACE>/src/main/java/com/safaria/backend/DTO/UserInfoDTO.java
text:
```scala
// package com.safaria.backend.DTO;

// import java.util.ArrayList;
// import java.util.List;

// import javax.swing.plaf.basic.BasicTabbedPaneUI;

// import com.safaria.backend.entity.TourProvider;
// import com.safaria.backend.entity.Tourist;

// import lombok.AllArgsConstructor;
// import lombok.Data;

// @Data
// @AllArgsConstructor
// public class UserInfoDTO {

//     private String name;
//     private String email;
//     private String password;
//     private String phoneNumber;
//     private String country;
//     private List<String> tourismTypes;
//     private String aboutMe;
//     private List<String> myTrips;
//     private List<String> myClients;
//     private List<String> myReviews;
//     private float rating;
//     private String type;
//     private String token;

//     public UserInfoDTO(Tourist tourist) {
//         this.name = tourist.getUser().getName();
//         this.email = tourist.getUser().getEmail();
//         this.password = tourist.getUser().getPassword();
//         this.country = tourist.getNationality();

//         this.aboutMe = "I am A tourist.getUser()";
//         this.myTrips = new ArrayList<>();
//         this.myClients = new ArrayList<>();
//         this.myReviews = new ArrayList<>();
//         this.rating = 5;
//     }

//     public UserInfoDTO(TourProvider tourProvider) {
//        this.name = tourProvider.getUser().getName();
//         this.email = tourProvider.getUser().getEmail();
//         this.password = tourProvider.getUser().getPassword();
//         this.phoneNumber = tourProvider.getPhone();
//         this.country = tourProvider.getCountry();
        

//         this.aboutMe = "I am A TourProvider";
//         this.myTrips = new ArrayList<>();
//         this.myClients = new ArrayList<>();
//         this.myReviews = new ArrayList<>();
//         this.rating = 5;
//     }

//     public void setToken(String token) {
//         throw new UnsupportedOperationException("Not supported yet.");
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

QDox parse error in file://<WORKSPACE>/src/main/java/com/safaria/backend/DTO/UserInfoDTO.java