Spring Boot Virtual Thread Demo
==================================

Spring Boot Virtual Thread Demo with Tomcat.

* Java 21: https://jdk.java.net/21/
* Spring Boot 3.1.1-SNAPSHOT with Tomcat 10.1.10
               
# Tomcat Virtual Threads Configuration

```java
import org.apache.catalina.core.StandardVirtualThreadExecutor;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;

@Configuration
public class TomcatVirtualThreadConfiguration {
    /**
     * Tomcat StandardVirtualThreadExecutor bean
     *
     * @return standard virtual thread executor
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public StandardVirtualThreadExecutor standardVirtualThreadExecutor() {
        return new StandardVirtualThreadExecutor();
    }

    /**
     * AsyncTaskExecutor to enable async servlet support and async tasks
     *
     * @param standardVirtualThreadExecutor standard virtual thread executor
     * @return async task executor
     */
    @Bean
    public AsyncTaskExecutor applicationTaskExecutor(StandardVirtualThreadExecutor standardVirtualThreadExecutor) {
        return new TaskExecutorAdapter(standardVirtualThreadExecutor);
    }

    /**
     * tomcat protocol handler customizer to enable virtual thread executor
     *
     * @param standardVirtualThreadExecutor standard virtual thread executor
     * @return TomcatProtocolHandlerCustomizer
     */
    @Bean
    public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer(StandardVirtualThreadExecutor standardVirtualThreadExecutor) {
        return protocolHandler -> protocolHandler.setExecutor(standardVirtualThreadExecutor);
    }
}

```


# References

* JDK 21: https://openjdk.org/projects/jdk/21/
* JEP 444: Virtual Threads - https://openjdk.org/jeps/444
* Spring Boot Asynchronous Requests: https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-async.html
* Apache Tomcat 10.1.10 available: https://lists.apache.org/thread/20lp6fcf5c0gb5p32k9vwl8xbscc1h68
