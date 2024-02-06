package com.example.springboot.rdsdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserDataRepository userDataRepository;

    public UserController(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @GetMapping("/")
    public String userForm(Model model) {
        model.addAttribute("user", new UserData());
        return "user";
    }

    @PostMapping("/user")
    public String userSubmit(@ModelAttribute UserData userData, Model model) {
        UserData savedUser = this.userDataRepository.save(userData);
        model.addAttribute("id", savedUser.getId());
        return "result";
    }


    @GetMapping("/users")
    public String showUserDetails(@RequestParam String id, Model model) {
        // Retrieve user details by ID from the database
        long x =  Long.parseLong(id);
        UserData userData = this.userDataRepository.findById(x).orElse(null);
        if (userData != null) {
            model.addAttribute("username", userData.getUsername());
            return "userdetails";
        } else {
            return "error"; // Handle error if user with given ID not found
        }
    }


}