package com.example.coffeeshop.web;

import com.example.coffeeshop.model.view.EmployeeViewModel;
import com.example.coffeeshop.model.view.OrderViewModel;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public HomeController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping
    public String index(Model model) {
        if (this.userService.getCurrentLoggedInUser() == null) {
            return "index";
        }

        List<OrderViewModel> orders = this.orderService.findAllOrders();
        List<EmployeeViewModel> employees = this.userService.findAllEmployees();

        model
                .addAttribute("orders", orders)
                .addAttribute(
                        "timeToPrepare",
                        orders
                                .stream()
                                .mapToInt(o -> o.getCategory().getNeededTime())
                                .sum()
                )
                .addAttribute("employees", employees);

        return "home";
    }
}
