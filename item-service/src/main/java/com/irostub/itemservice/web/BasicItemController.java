package com.irostub.itemservice.web;

import com.irostub.itemservice.domain.item.Item;
import com.irostub.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostConstruct
    void init() {
        Item item1 = Item.builder()
                .itemName("iro")
                .quantity(10)
                .price(6000).build();
        Item item2 = Item.builder()
                .itemName("iro")
                .quantity(20)
                .price(10000).build();

        repository.save(item1);
        repository.save(item2);
    }

}
