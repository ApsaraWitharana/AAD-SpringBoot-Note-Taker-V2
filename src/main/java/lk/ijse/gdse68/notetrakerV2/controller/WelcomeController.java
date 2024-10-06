package lk.ijse.gdse68.notetrakerV2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("api/v1/welcome") //this note rest full /this ui
public class WelcomeController {
    @GetMapping
    public String welcome(Model model){
        model.addAttribute("message","Welcome To Note Traker V2 2024");
        return "welcome";
    }
}
