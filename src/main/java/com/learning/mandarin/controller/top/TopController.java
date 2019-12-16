package com.learning.mandarin.controller.top;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TopController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "Hello World!";
    }
}
