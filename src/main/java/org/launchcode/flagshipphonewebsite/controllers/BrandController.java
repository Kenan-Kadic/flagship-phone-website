package org.launchcode.flagshipphonewebsite.controllers;

import org.launchcode.flagshipphonewebsite.models.Brand;
import org.launchcode.flagshipphonewebsite.models.Phone;
import org.launchcode.flagshipphonewebsite.models.data.BrandRepository;
import org.launchcode.flagshipphonewebsite.models.data.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("brands")
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("brand", brandRepository.findAll());

        return "brands/index";
    }

    @GetMapping("add")
    public String displayAddBrandForm(Model model) {
        model.addAttribute(new Brand());
        return "brands/add";
    }

    @PostMapping("add")
    public String processAddBrandForm(@ModelAttribute @Valid Brand newBrand,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "brands/add";
        } else {
            brandRepository.save(newBrand);
            return "redirect:../";
        }
    }

    @GetMapping("view/{brandId}")
    public String displayViewBrand(Model model, @PathVariable int brandId, Phone newPhone) {
        var brandName = brandRepository.findById(brandId).map(Brand::getName).orElse("Unknown");
        model.addAttribute("brand", brandName);
        model.addAttribute("phones", phoneRepository.findByBrandId(brandId));

        return "brands/view";
    }
}
