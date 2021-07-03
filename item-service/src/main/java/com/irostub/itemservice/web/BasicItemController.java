package com.irostub.itemservice.web;

import com.irostub.itemservice.domain.item.Item;
import com.irostub.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basic/items")
public class BasicItemController {
    private final ItemRepository repository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = repository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/{id}")
    public String item(@PathVariable Long id, Model model) {
        Item findItem = repository.findById(id);
        model.addAttribute("item", findItem);
        return "basic/item";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Item findItem = repository.findById(id);
        model.addAttribute("item", findItem);
        return "basic/editForm";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute Item item) {
        repository.update(id, item);
        return "redirect:/basic/items/{itemId}";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Item item) {
        repository.save(item);
        return "/basic/items";
    }

    @PostConstruct
    void init() {
        Item item1 = new Item("iro",6000,10);
        Item item2 = new Item("iro", 10000, 20);

        repository.save(item1);
        repository.save(item2);
    }

}
