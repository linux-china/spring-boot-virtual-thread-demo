package org.mvnsearch.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;


@RestController
public class PortalController {

    @GetMapping("/")
    public String index() {
        return "Hello Virtual Thread!";
    }

    @GetMapping("/where-am-i")
    String getThreadName() {
        return Thread.currentThread().toString();
    }

    @GetMapping("/where-am-i-async")
    Callable<String> getAsyncThreadName() {
        return () -> Thread.currentThread().toString();
    }
}
