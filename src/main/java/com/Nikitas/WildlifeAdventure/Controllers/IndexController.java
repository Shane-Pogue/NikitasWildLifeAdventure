package com.Nikitas.WildlifeAdventure.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index", "/home"})
    public String index(Model model) {
        model.addAttribute("page", "fragments/index");
        return "index";
    }
}
