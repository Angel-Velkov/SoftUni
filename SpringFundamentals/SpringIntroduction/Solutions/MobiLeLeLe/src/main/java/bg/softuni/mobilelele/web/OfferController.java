package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.service.BrandService;
import bg.softuni.mobilelele.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getOfferForm(Model model) {
//        if (model.getAttribute("offer") == null) {
//            model.addAttribute("offer", new OfferViewModel());
//        }
//
//        model.addAttribute("brands", brandService.getBrands());

        return "offers";
    }
}
