package com.nesea.exercise.carservicesrl.controllers;

import com.nesea.exercise.carservicesrl.model.Automobile;
import com.nesea.exercise.carservicesrl.model.Proprietario;
import com.nesea.exercise.carservicesrl.services.AutomobileService;
import com.nesea.exercise.carservicesrl.services.ProprietarioService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping("/automobili")
public class AutomobileController {

    private AutomobileService automobileService;
    private ProprietarioService proprietarioService;

    public AutomobileController(AutomobileService automobileService, ProprietarioService proprietarioService) {
        this.automobileService = automobileService;
        this.proprietarioService = proprietarioService;
    }

    ///gestisce le stringhe vuote in validazione come null
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/all")
    public String getAllAutomobili(Model model) {
        model.addAttribute("automobili", automobileService.getAllAutomobili());
        return "automobili/index";  //DI DEFAULT CLASSPATH:RESOURCES/TEMPLATES
    }

    @PostMapping("/add")
    public String AddAutomobile(@ModelAttribute Automobile auto, ModelMap modelMap) {
        try {
            automobileService.saveAutomobileInfo(auto);
            modelMap.addAttribute("okMessage", "Salvataggio effettuato correttamente.");
        } catch (RuntimeException e) {
            modelMap.addAttribute("errorMessage", "Errore!");
        }
        return "automobili/add";
    }

    @GetMapping("/add")
    public ModelAndView getAutomobili() {
        /****OGGETTO COMPOSITO PER SETTARE SIA IL MODEL CHE LA VIEW *****/
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(new Automobile());
        modelAndView.setViewName("automobili/add");
        return modelAndView;
    }

    /***PRIMA FASE EDIT ****/
    @GetMapping("/edit/{id}")
    public ModelAndView getDevice(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Optional<Automobile> automobileById = automobileService.getAutomobileById(id);
            if (automobileById.isPresent()) {
                modelAndView.addObject("proprietari", proprietarioService.getAllProprietari());
                if (automobileById.get().getOwner() != null) {
                    modelAndView.addObject("newProprietario", false);
                    modelAndView.addObject("automobile", automobileById.get());
                }
                else {
                    modelAndView.addObject("newProprietario", true);
                    automobileById.get().setOwner(new Proprietario());
                    modelAndView.addObject("automobile", automobileById.get());
                }


                modelAndView.setViewName("automobili/edit");
            } else {
                modelAndView.addObject("errorMessage", "Nessun device trovato con l'id " + id);
                modelAndView.addObject("automobili", automobileService.getAllAutomobili());
                modelAndView.setViewName("automobili/index");
            }
        } catch (RuntimeException e) {
            modelAndView.addObject("errorMessage", "Errore!");
        }
        return modelAndView;
    }

    /***SECONDA FASE EDIT ****/
    @PostMapping("/edit")
    public String editDevice(@ModelAttribute Automobile automobile,  ModelMap modelMap) {
        try {
            //automobileService.saveAutomobileInfo(automobile);
            automobileService.sellAutomobileToNewOwner(automobile, automobile.getOwner().getId());

            modelMap.addAttribute("okMessage", "Salvataggio effettuato correttamente");
            modelMap.addAttribute("automobili", automobileService.getAllAutomobili());
        } catch (RuntimeException e) {
            modelMap.addAttribute("errorMessage", "Errore!");
        }
        return "automobili/index";
    }


    @GetMapping("/delete/{id}")
    public String deleteDevice(@PathVariable("id") Integer id, Model model) {
        try {
            automobileService.deteleAutomobile(id);
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Errore!");
        }
        model.addAttribute("automobili", automobileService.getAllAutomobili());
        return "automobili/index";
    }


}

