package org.launchcode.flagshipphonewebsite.controllers;

import org.launchcode.flagshipphonewebsite.models.Phone;
import org.launchcode.flagshipphonewebsite.models.PhoneData;
import org.launchcode.flagshipphonewebsite.models.data.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.launchcode.flagshipphonewebsite.controllers.ListController.columnChoices;

@Controller
@RequestMapping("phones")
public class SearchController {

    @Autowired
    private PhoneRepository phoneRepository;

    @RequestMapping("search")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "phones/search";
    }

    @PostMapping("search/results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Phone> phones;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            phones = phoneRepository.findAll();
        } else {
            phones = PhoneData.findByColumnAndValue(searchType, searchTerm, phoneRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Phones with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("phones", phones);

        return "phones/search";
    }
}