package com.example.pathfinder.web;

import com.example.pathfinder.model.binding.RouteAddBindingModel;
import com.example.pathfinder.model.service.RouteServiceModel;
import com.example.pathfinder.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final ModelMapper mapper;

    @Autowired
    public RouteController(RouteService routeService, ModelMapper mapper) {
        this.routeService = routeService;
        this.mapper = mapper;
    }

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel() {
        return new RouteAddBindingModel();
    }

    @GetMapping("/all")
    public String allRoutes(Model model) {

        model.addAttribute("routes", this.routeService.getAllRoutes());

        return "routes";
    }

    @GetMapping("/add")
    public String addRoute() {
        return "add-route";
    }

    @PostMapping("/add")
    public String addRouteConfirm(@Valid RouteAddBindingModel routeAddBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("routeAddBindingModel", routeAddBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.routeAddBindingModel",
                            routeAddBindingModel
                    );
        }

        RouteServiceModel routeServiceModel = this.mapper.map(routeAddBindingModel, RouteServiceModel.class);
        routeServiceModel.setGpxCoordinates(new String(routeAddBindingModel.getGpxCoordinates().getBytes()));

        this.routeService.addRoute(routeServiceModel);

        return "redirect:all";
    }

    @GetMapping("/details/{id}")
    public String routeDetails(@PathVariable Long id, Model model) {

        model.addAttribute("route", this.routeService.findRouteById(id));

        return "route-details";
    }
}
