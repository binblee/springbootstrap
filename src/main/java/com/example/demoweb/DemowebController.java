package com.example.demoweb;

import com.example.demoweb.domain.Instance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DemowebController {

    @GetMapping("")
    public String index(Model model){
        Instance instance = new Instance();
        model.addAttribute("ip",instance.getIp());
        return "index";
    }
}
