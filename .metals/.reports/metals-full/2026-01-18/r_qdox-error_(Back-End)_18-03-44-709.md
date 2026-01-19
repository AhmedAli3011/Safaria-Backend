error id: file://<WORKSPACE>/src/main/java/com/safaria/backend/controller/payment/StripeController.java
file://<WORKSPACE>/src/main/java/com/safaria/backend/controller/payment/StripeController.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[52,1]

error in qdox parser
file content:
```java
offset: 2153
uri: file://<WORKSPACE>/src/main/java/com/safaria/backend/controller/payment/StripeController.java
text:
```scala
// package com.safaria.backend.controller.payment;

// import com.safaria.backend.DTO.payment.PaymentIntentRequest;
// import com.safaria.backend.DTO.payment.SellerOnboardingRequest;
// import com.safaria.backend.DTO.payment.SellerOnboardingResponse;
// import com.safaria.backend.entity.TourProvider;
// import com.safaria.backend.repository.TourProviderRepository;
// import com.safaria.backend.service.payment.StripeService;
// import com.stripe.exception.StripeException;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import java.util.HashMap;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/integration/api/stripe")
// @CrossOrigin(origins = "http://localhost:4200")
// @RequiredArgsConstructor
// public class StripeController {

//     @Autowired
//     private TourProviderRepository tourProviderRepository;
//     @Autowired
//     private StripeService stripeService;

//     @PostMapping("/create-account")
//     public SellerOnboardingResponse createSellerAccount(@RequestBody SellerOnboardingRequest request) throws StripeException {
//         return stripeService.createExpressAccount(request);
//     }

//     @PostMapping("/create-payment-intent")
//     public Map<String, String> createPaymentIntent(@RequestBody PaymentIntentRequest request) throws StripeException {
//         return stripeService.createPaymentIntent(request);
//     }

//     @GetMapping("/provider-stripe-status/{providerId}")
//     public Map<String, Object> getStripeStatus(@PathVariable Integer providerId) {
//         TourProvider provider = tourProviderRepository.findById(providerId).orElse(null);
//         Map<String, Object> result = new HashMap<>();
//         result.put("hasStripeAccount", provider != null && provider.getStripeAccountId() != null);
//         if (provider != null) {
//             result.put("email", provider.getEmail());
//         }
//         if (provider != null) {
//             result.put("country", provider.getCountry());
//         }
//         return result;
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

QDox parse error in file://<WORKSPACE>/src/main/java/com/safaria/backend/controller/payment/StripeController.java