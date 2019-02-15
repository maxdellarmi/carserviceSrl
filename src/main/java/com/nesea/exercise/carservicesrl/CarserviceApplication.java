package com.nesea.exercise.carservicesrl;

import com.nesea.exercise.carservicesrl.model.Automobile;
import com.nesea.exercise.carservicesrl.model.Proprietario;
import com.nesea.exercise.carservicesrl.services.AutomobileService;
import com.nesea.exercise.carservicesrl.services.ProprietarioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.thymeleaf.standard.serializer.StandardJavaScriptSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class CarserviceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CarserviceApplication.class, args);
        AutomobileService autoService= (AutomobileService) run.getBean("automobileService");
        ProprietarioService proprietarioService= (ProprietarioService) run.getBean("proprietarioService");

        Proprietario p = new Proprietario();
        p.setCodiceFiscale("DLLMSM80R25H501Q");
        p.setCognome("DellArmi1");
        p.setEmail("max.dellarmi1@gmail.com");
        p.setIndirizzo("via canicattì 31, Roma");
        p.setNome("Massimiliano");
        p.setTelefono("067919091");

        Proprietario p1 = new Proprietario();
        p1.setCodiceFiscale("DLLMSM80R25H501Q");
        p1.setCognome("DellArmi2");
        p1.setEmail("max.dellarmi2@gmail.com");
        p1.setIndirizzo("via canicattì 31, Roma");
        p1.setNome("Massimiliano2");
        p1.setTelefono("067919092");

        Proprietario p2 = new Proprietario();
        p2.setCodiceFiscale("DLLMSM80R25H501Q");
        p2.setCognome("DellArmi3");
        p2.setEmail("max.dellarmi@gmail.com");
        p2.setIndirizzo("via canicattì 31, Roma");
        p2.setNome("Massimiliano3");
        p2.setTelefono("067919093");

        Proprietario p3 = new Proprietario();
        p3.setCodiceFiscale("DLLMSM80R25H501Q");
        p3.setCognome("DellArmi4");
        p3.setEmail("max.dellarmi4@gmail.com");
        p3.setIndirizzo("via canicattì 31, Roma");
        p3.setNome("Massimiliano4");
        p3.setTelefono("067919094");


        proprietarioService.saveProprietario(p);
        proprietarioService.saveProprietario(p1);
        proprietarioService.saveProprietario(p2);
        proprietarioService.saveProprietario(p3);

        Automobile automobile= new Automobile();
        //automobile.setDataVendita(LocalDateTime.now());
        automobile.setOwner(null);
        automobile.setColore("Bianco");
        automobile.setModello("Fiat 1.9");
        automobile.setNumeroTelaio("WMWSY9C59ET123456");
        //automobile.setPrezzoVendita(10000D);
        automobile.setTarga("CB370KJ");

        Automobile automobile1= new Automobile();
        //automobile1.setDataVendita(LocalDateTime.now());
        automobile1.setOwner(null);
        automobile1.setColore("Verde");
        automobile1.setModello("Fiat 1.9");
        automobile1.setNumeroTelaio("WMWSY9C59ET223456");
        //automobile1.setPrezzoVendita(10000D);
        automobile1.setTarga("CB371KJ");

        Automobile automobile2= new Automobile();
        //automobile2.setDataVendita(LocalDateTime.now());
        automobile2.setOwner(null);
        automobile2.setColore("Rosso");
        automobile2.setModello("Fiat 1.9");
        automobile2.setNumeroTelaio("WMWSY9C59ET323456");
        //automobile2.setPrezzoVendita(10000D);
        automobile2.setTarga("CB372KJ");

        Automobile automobile3= new Automobile();
        //automobile3.setDataVendita(LocalDateTime.now());
        automobile3.setOwner(null);
        automobile3.setColore("Giallo");
        automobile3.setModello("Fiat 1.9");
        automobile3.setNumeroTelaio("WMWSY9C59ET423456");
        //automobile3.setPrezzoVendita(10000D);
        automobile3.setTarga("CB373KJ");

        autoService.saveAutomobileInfo(automobile);
        autoService.saveAutomobileInfo(automobile1);
        autoService.saveAutomobileInfo(automobile2);
        autoService.saveAutomobileInfo(automobile3);


    }

}

