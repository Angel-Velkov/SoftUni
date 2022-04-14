package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.view.BrandWithModelsViewModel;
import bg.softuni.mobilelele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;
    private final ModelMapper mapper;

    @Autowired
    public BrandController(BrandService brandService, ModelMapper mapper) {
        this.brandService = brandService;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public String gerBrands(Model model) {
        model.addAttribute("brandModels", this.brandService.findAllBrandsWithTheirModels()
                .stream()
                .map(brand -> this.mapper.map(brand, BrandWithModelsViewModel.class))
                .collect(Collectors.toList()));

        return "brands";
    }
}
