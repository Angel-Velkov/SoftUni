package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.OfferUploadBindingModel;
import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import bg.softuni.mobilelele.model.service.OfferServiceModel;
import bg.softuni.mobilelele.model.view.BrandWithModelNamesViewModel;
import bg.softuni.mobilelele.service.BrandService;
import bg.softuni.mobilelele.service.OfferService;
import bg.softuni.mobilelele.user.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;
    private final CurrentUser currentUser;
    private final ModelMapper mapper;

    @Autowired
    public OfferController(OfferService offerService, BrandService brandService, CurrentUser currentUser, ModelMapper mapper) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.currentUser = currentUser;
        this.mapper = mapper;
    }

    @ModelAttribute("offerUpload")
    private OfferUploadBindingModel offerUploadBindingModel() {
        return new OfferUploadBindingModel();
    }

    @GetMapping("/all")
    public String getOffers() {
        return "offers";
    }

    @GetMapping("/add")
    public String uploadOffer(Model model) {
        if (this.currentUser.getId() == null) {
            return "redirect:/users/login";
        }

        List<BrandWithModelNamesViewModel> allBrandsWithModels = this.brandService.getAllBrandsWithModels();
        model.addAttribute("brands", allBrandsWithModels);
        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOfferConfirm(@Valid OfferUploadBindingModel offerUploadBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("offerUpload", offerUploadBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.offerUpload",
                            bindingResult
                    );
        }

        this.offerService.saveOffer(this.mapper.map(offerUploadBindingModel, OfferServiceModel.class));

        return "redirect:all";
    }
}
