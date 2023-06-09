package nocoders.courseworkdb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/login")
    public String UserPage(Model model) {
        model.addAttribute("title", "Страница абитуриента");
        return "users/login";
    }
}
