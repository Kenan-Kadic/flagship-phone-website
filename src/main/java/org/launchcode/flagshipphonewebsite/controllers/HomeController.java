package org.launchcode.flagshipphonewebsite.controllers;

import org.launchcode.flagshipphonewebsite.models.Brand;
import org.launchcode.flagshipphonewebsite.models.data.BrandRepository;
import org.launchcode.flagshipphonewebsite.models.data.PhoneRepository;
import org.launchcode.flagshipphonewebsite.models.data.UserRepository;
import org.launchcode.flagshipphonewebsite.models.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;



@Controller
public class HomeController {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("brands", brandRepository.findAll());
        return "index";
    }

    @GetMapping("admin")
    public String displayAddPhoneForm(Model model) {
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute(new Phone());
        model.addAttribute(new Brand());
        return "admin";
    }

    @PostMapping("admin")
    public String processAddPhoneForm(@ModelAttribute @Valid Phone newPhone, Brand newBrand,
                                      Errors errors, Model model, @RequestParam int brandId) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Phone");
            return "admin";
        }

        Optional <?> optBrand = brandRepository.findById(brandId);
        if (optBrand.isPresent()) {
            Brand brand = (Brand) optBrand.get();
            newPhone.setBrand(brand);
            model.addAttribute("brand", brand);
            phoneRepository.save(newPhone);
            return "redirect:";
        } else {
            return "redirect:../";
        }
    }


    @GetMapping("phones/view/{phoneId}")
    public String displayViewPhone(Model model, @PathVariable int phoneId, Brand newBrand) {

        model.addAttribute("brands", brandRepository.findAll());

        Optional<?> optPhone = phoneRepository.findById(phoneId);
        if (optPhone.isPresent()) {
            Phone phone = (Phone) optPhone.get();
            model.addAttribute("phone", phone);
            return "phones/view";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("phones/edit/{phoneId}")
    public String displayEditPhone(Model model, @PathVariable int phoneId, Brand newBrand) {
        model.addAttribute("brands", brandRepository.findAll());

        Optional<?> optPhone = phoneRepository.findById(phoneId);
        if (optPhone.isPresent()) {
            Phone phone = (Phone) optPhone.get();
            model.addAttribute("phone", phone);
            return "phones/edit";
        } else {
            return "redirect:/";
        }
    }

    @PutMapping("phones/edit/{phoneId}")
    public String processEditPhoneForm(@ModelAttribute @Valid Phone newPhone, @PathVariable int phoneId, Errors errors, Model model) {

         var x = phoneRepository.findById(phoneId).get();
            x.setName(newPhone.getName());
            x.setPrice(newPhone.getPrice());
            x.setSize(newPhone.getSize());
            x.setColors(newPhone.getColors());
            x.setBuild(newPhone.getBuild());
            x.setResistance(newPhone.getResistance());
            x.setProcessor(newPhone.getProcessor());
            x.setOs(newPhone.getOs());
            x.setRam(newPhone.getRam());
            x.setFrontCameras(newPhone.getFrontCameras());
            x.setRearCameras(newPhone.getRearCameras());
            x.setBattery(newPhone.getBattery());
            x.setStorage(newPhone.getStorage());
            x.setSpeakers(newPhone.getSpeakers());
         phoneRepository.save(x);
        return "redirect:/";
    }

    @DeleteMapping ("phones/delete/{phoneId}")
    public String deletePhone(@PathVariable int phoneId) {

        phoneRepository.deleteById(phoneId);
        return "redirect:/";
    }
}
