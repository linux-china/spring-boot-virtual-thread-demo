Spring Boot Virtual Thread Demo
==================================

Spring Boot Virtual Thread Demo with Tomcat.

* Java 21: https://jdk.java.net/21/
* Spring Boot 3.2.0 with Tomcat 10.1.16

# Tomcat Virtual Threads Configuration
          
Please add following configuration to `application.properties`:

```properties
spring.threads.virtual.enabled=true
```

# Reactive on Virtual Threads

```
    @GetMapping("/reactive")
    public Mono<String> reactive() {
        return Mono.just("Hello Reactor!").doOnNext(s -> {
            System.out.println("Reactive on " + Thread.currentThread());
        });
    }
```

# Async method on Virtual Threads

Add `@EnableAsync` on Spring Boot main class, then add `@Async` on method.

# References

* JDK 21: https://openjdk.org/projects/jdk/21/
* JEP 444: Virtual Threads - https://openjdk.org/jeps/444
* Spring Boot Asynchronous Requests: https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-async.html
* Apache Tomcat 10.1.10 available: https://lists.apache.org/thread/20lp6fcf5c0gb5p32k9vwl8xbscc1h68
* Embracing Virtual Threads: https://spring.io/blog/2022/10/11/embracing-virtual-threads
* Working with Virtual Threads in Spring 6: https://www.baeldung.com/spring-6-virtual-threads
* When virtual threads are enabled, configure Jetty to use
  them: https://github.com/spring-projects/spring-boot/issues/35703
