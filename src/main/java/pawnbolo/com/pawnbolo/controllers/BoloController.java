package pawnbolo.com.pawnbolo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bolos")
public class BoloController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!!!!";
    }
}
