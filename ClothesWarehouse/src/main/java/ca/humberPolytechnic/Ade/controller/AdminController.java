package ca.humberPolytechnic.Ade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.humberPolytechnic.Ade.repository.ItemRepository;

@Controller
public class AdminController {

    private final ItemRepository itemRepository;

    public AdminController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "admin";
    }
}