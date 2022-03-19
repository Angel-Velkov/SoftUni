package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.OfferPersistBindingModel;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.service.ModelServiceModel;
import bg.softuni.mobilelele.model.service.OfferServiceModel;
import bg.softuni.mobilelele.model.view.BrandWithModelNamesViewModel;
import bg.softuni.mobilelele.model.view.ModelViewModel;
import bg.softuni.mobilelele.model.view.OfferDetailedViewModel;
import bg.softuni.mobilelele.model.view.OfferSummaryViewModel;
import bg.softuni.mobilelele.service.BrandService;
import bg.softuni.mobilelele.service.OfferService;
import bg.softuni.mobilelele.user.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    private OfferPersistBindingModel offerUploadBindingModel() {
        return new OfferPersistBindingModel();
    }

    // GET
    @GetMapping("/all")
    public String getOffers(Model model) {

        List<OfferServiceModel> allOffers = this.offerService.findAllOffers();

        List<OfferSummaryViewModel> collect = allOffers
                .stream()
                .map(offer -> this.mapper.map(offer, OfferSummaryViewModel.class))
                .collect(Collectors.toList());

        model.addAttribute("offers", collect);

        return "offers";
    }

    @GetMapping("/{id}/details")
    public String offerDetails(@PathVariable Long id, Model model) {

        OfferDetailedViewModel map = this.mapper.map(this.offerService.findById(id), OfferDetailedViewModel.class);

        model.addAttribute(
                "offer",
                map
        );

        return "details";
    }

    // POST
    @GetMapping("/add")
    public String addOffer(Model model) {
        if (this.currentUser.getId() == null) {
            return "redirect:/users/login";
        }

        model.addAttribute("brands", this.brandService
                .findAllBrandsWithTheirModels()
                .stream()
                .map(brand ->
                        new BrandWithModelNamesViewModel(brand.getName(),
                                brand
                                        .getModels()
                                        .stream()
                                        .map(ModelServiceModel::getName)
                                        .collect(Collectors.toList()))
                )
                .collect(Collectors.toList()));

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOfferConfirm(@Valid OfferPersistBindingModel offerPersistBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("offerUpload", offerPersistBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.offerUpload",
                            bindingResult
                    );

            return "redirect:add";
        }

        this.offerService.saveOffer(this.mapper.map(offerPersistBindingModel, OfferServiceModel.class));

        return "redirect:all";
    }

    // PATCH
    @GetMapping("/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model) {

        model
                .addAttribute("brands", this.brandService
                        .findAllBrandsWithTheirModels()
                        .stream()
                        .map(brand ->
                                new BrandWithModelNamesViewModel(brand.getName(),
                                        brand
                                                .getModels()
                                                .stream()
                                                .map(ModelServiceModel::getName)
                                                .collect(Collectors.toList()))
                        )
                        .collect(Collectors.toList()))
                .addAttribute(
                        "offerEdit",
                        this.mapper.map(this.offerService.findById(id), OfferPersistBindingModel.class)
                );

        return "update";
    }

    @PatchMapping("{id}/edit")
    private String editOfferConfirm(@PathVariable Long id,
                                    @Valid OfferPersistBindingModel offerPersistBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("offerEdit", offerPersistBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.offerPersistBindingModel",
                            bindingResult
                    );

            return "redirect:edit";
        }

        OfferServiceModel offerServiceModel = this.mapper.map(offerPersistBindingModel, OfferServiceModel.class);
        offerServiceModel.setId(id);

        this.offerService.updateOffer(offerServiceModel);

        return "redirect:details";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable Long id) {
        this.offerService.deleteOffer(id);

        return "redirect:all";
    }
}
