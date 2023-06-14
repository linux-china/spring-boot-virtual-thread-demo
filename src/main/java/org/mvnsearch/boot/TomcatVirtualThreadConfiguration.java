package org.mvnsearch.boot;

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
     * AsyncTaskExecutor to enable async servlet support
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
        return protocolHandler -> {
            protocolHandler.setExecutor(standardVirtualThreadExecutor);
        };
    }
}
