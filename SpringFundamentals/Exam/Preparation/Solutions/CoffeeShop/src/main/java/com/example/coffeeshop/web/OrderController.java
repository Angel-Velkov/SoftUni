package com.example.coffeeshop.web;

import com.example.coffeeshop.model.binding.OrderAddBindingModel;
import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper mapper;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @ModelAttribute
    private OrderAddBindingModel orderAddBindingModel() {
        return new OrderAddBindingModel();
    }

    @GetMapping("/add")
    public String addOrder() {
        return "order-add";
    }

    @PostMapping("/add")
    public String addOrderConfirm(@Valid OrderAddBindingModel orderAddBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(orderAddBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.orderAddBindingModel",
                            bindingResult
                    );

            return "redirect:add";
        }

        this.orderService.addOrder(this.mapper.map(orderAddBindingModel, OrderServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/ready/{id}")
    public String ready(@PathVariable Long id) {

        this.orderService.readyOrder(id);

        return "redirect:/";
    }
}
