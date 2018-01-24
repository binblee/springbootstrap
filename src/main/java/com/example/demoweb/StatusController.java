package com.example.demoweb;

import com.example.demoweb.domain.Instance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s")
public class StatusController {

    @GetMapping("")
    public Instance show(){
        return new Instance();
    }
}
