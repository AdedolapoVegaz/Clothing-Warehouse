package ca.humberPolytechnic.Ade.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.humberPolytechnic.Ade.model.Brand;
import ca.humberPolytechnic.Ade.model.Item;
import ca.humberPolytechnic.Ade.repository.ItemRepository;
import jakarta.validation.Valid;

@Controller
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @GetMapping("/items")
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       @RequestParam(defaultValue = "id") String sort,
                       @RequestParam(defaultValue = "asc") String dir,
                       Model model) {

        Sort.Direction d = dir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(d, sort));
        Page<Item> itemPage = itemRepository.findAll(pageable);

        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", itemPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);

        return "item-list";
    }
    // List page with pagination + sorting
    @GetMapping("/items/brand2025")
    public String listBrand2025(@RequestParam Brand brand,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("brand")); // sort by brand
        Page<Item> itemPage = itemRepository.findBrandIn2025(brand, pageable);

        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", itemPage.getTotalPages());
        model.addAttribute("size", size);

        // optional (helps you show what filter is active)
        model.addAttribute("brandFilter", brand);

        return "item-list";
    }

    // Add form
    @GetMapping("/add-item")
    public String showForm(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("brands", Brand.values());
        model.addAttribute("items", itemRepository.findAll());
        return "add-item";
    }

    // Save to DB + redirect to list page (rubric)
    @PostMapping("/add-item")
    public String addItem(@Valid @ModelAttribute Item item,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("brands", Brand.values());
            model.addAttribute("items", itemRepository.findAll());
            return "add-item";
        }

        itemRepository.save(item);
        return "redirect:/items";
    }

    // Delete + redirect to list page
    @PostMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return "redirect:/items";
    }
}
