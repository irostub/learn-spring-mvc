package com.irostub.itemservice.web;

import com.irostub.itemservice.domain.item.Item;
import com.irostub.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("items", items);
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

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        repository.update(itemId, item);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/"+item.getId();
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addV1(@RequestParam String itemName,
                        @RequestParam int price,
                        @RequestParam Integer quantity,
                        Model model) {
        Item item = new Item(itemName, price, quantity);
        Item saveItem = repository.save(item);

        model.addAttribute("item", saveItem);
        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addV2(@ModelAttribute("item") Item item, Model model) {
        Item saveItem = repository.save(item);
        model.addAttribute("item", saveItem);
        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addV3(@ModelAttribute("item") Item item) {
        repository.save(item);
        //@ModelAttribute 는 객체에 매핑시켜주는 일과 동시에 파라메터로 받은 문자열을 맵의 키로 받아와서 매핑한 객체를 value 하여 Model 에 넣어준다.
        //그러므로 아래 코드가 필요없어진다.
        //model.addAttribute("item", saveItem);
        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addV4(@ModelAttribute Item item) {
        repository.save(item);
        //사실 V3에서 @ModelAttribute 의 인자에 아무것도 안넣어줘도 받아올 클래스의 이름을 첫자를 소문자로 바꿔서 Model에 자동으로 넣어준다.
        //Item -> item
        return "/basic/item";
    }

    /**
     * PRG 패턴을 지키지 않았다. POST 는 멱등하지 않으므로, 해당 컨트롤러를 탄 뒤에 새로고침을 하면
     * 계속 같은 데이터를 저장하는 로직이 실행되어버린다.
     * addV6() 메서드는 멱등하지 않은 POST 요청을 PRG 패턴으로 해결한 것이다.
     */
    //@PostMapping("/add")
    public String addV5(Item item) {
        repository.save(item);
        //마찬가지로 사용자 정의 Class 을 땐 @ModelAttribute 어노테이션도 필요없으므로 이것조차 생략이 가능하다.
        //동일하게 item 이란 키로 Model 에 담아주는 일까지 해준다.
        return "/basic/item";
    }

    //PRG 패턴을 사용하여 멱등하지 않은 POST 요청을 안전하게 처리
    //@PostMapping("/add")
    public String addV6(Item item) {
        repository.save(item);
        //주의점은 리다이렉트는 URL 인코딩이 되지 않는다. 지금은 Id (정수) 를 넣었으니 망정이지, 한글이 들어가는 순간 URL 은 깨져버리는 것. 이 문제와
        //클라이언트로부터 저장이 되었을 때, 잘 저장되었습니다. 라는 메세지가 뷰에 보이도록 해달라는 요청이 들어왔다. 이것까지 전부 처리할 수 있는
        //컨트롤러 addV7 으로 변신시키자.
        return "redirect:/basic/items/"+item.getId();
    }

    //PRG 패턴을 사용하여 멱등하지 않은 POST 요청을 안전하게 처리
    @PostMapping("/add")
    public String addV7(Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = repository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    void init() {
        Item item1 = new Item("iro", 6000, 10);
        Item item2 = new Item("iro", 10000, 20);

        repository.save(item1);
        repository.save(item2);
    }

}
