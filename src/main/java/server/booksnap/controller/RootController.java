package server.booksnap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/api/healthcheck")
    public String root() {
        return "Hello, World!";
    }
}