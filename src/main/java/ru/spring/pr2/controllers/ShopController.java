package ru.spring.pr2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.pr2.model.Address;
import ru.spring.pr2.model.Shop;
import ru.spring.pr2.repo.AddressRepository;
import ru.spring.pr2.repo.ShopRepository;

@Controller
public class ShopController {
    @Autowired
    public AddressRepository addressRepository;
    @Autowired
    public ShopRepository shopRepository;

    @GetMapping("/shop")
    public String Main(Model model){
        Iterable<Address> address = addressRepository.findAll();
        model.addAttribute("address",address);
        return "shop";
    }

    @PostMapping("/shop/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String street, Model model)
    {
        Address address = addressRepository.findByStreet(street);
        Shop person = new Shop(title, address);
        shopRepository.save(person);
        return "redirect:/shop";
    }
}
