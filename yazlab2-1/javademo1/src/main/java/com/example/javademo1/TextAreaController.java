package com.example.javademo1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TextAreaController {

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("textArea", "");
        return "addTextArea";
    }

    @PostMapping("/addTextArea")
    public String addTextArea(Model model) {
        model.addAttribute("textArea", "This is a new text area.");
        return "addTextArea";
    }
}
