package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.view.BrandWithModelsViewModel;
import bg.softuni.mobilelele.service.BrandService;
import bg.softuni.mobilelele.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;
    private final ModelMapper mapper;

    @Autowired
    public OfferController(OfferService offerService, BrandService brandService, ModelMapper mapper) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public String getOffers() {
        return "offers";
    }

    @GetMapping("/add")
    public String uploadOffer(Model model) {
        List<BrandWithModelsViewModel> allBrandsWithModels = this.brandService.getAllBrandsWithModels();
        model.addAttribute("brands", allBrandsWithModels);

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOfferConfirm(){

        return "redirect:all";
    }
}
