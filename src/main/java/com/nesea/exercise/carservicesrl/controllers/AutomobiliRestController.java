package com.nesea.exercise.carservicesrl.controllers;


import com.nesea.exercise.carservicesrl.model.Automobile;
import com.nesea.exercise.carservicesrl.services.AutomobileService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/automobili")

/****************************************MANCA GESTIONE DTO *****************************************************/
/****************************************MANCA GESTIONE DTO  *****************************************************/
/****************************************MANCA GESTIONE DTO  *****************************************************/

public class AutomobiliRestController {
    private AutomobileService automobileService;

    public AutomobiliRestController(AutomobileService automobileService) {
        this.automobileService = automobileService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Automobile> getAutomobile(@PathVariable Integer id){
        Optional<Automobile> automobileById = automobileService.getAutomobileById(id);
        //atomicReference utilizzato per poter avere il riferimento dentro la lambda senza di questo non è possibile
        //fare il set della variabile e IfPresent torna void
        AtomicReference<ResponseEntity< Automobile >> response = new AtomicReference<>(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        automobileById.ifPresent(automobile -> response.set(new ResponseEntity<>(automobile, HttpStatus.OK)));
        return response.get();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAutomobile(@PathVariable("id") Integer id) {
        automobileService.deteleAutomobile(id);
    }
    /**
     * Standard api with get method in a generic query string search parameter.
     * A sample search with pagination.
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/allPaged")
    public ResponseEntity<List<Automobile>> getAutomobiliPage(@RequestParam("page")int page, @RequestParam("size") int size){
        Page< Automobile> resultPage = automobileService.getAllAutomobiliPaginated(page,size);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(resultPage.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Automobile>> getAllAutomobili(){
        List<Automobile> result = automobileService.getAllAutomobili();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }


    @PostMapping("/add")
    @PutMapping("/update")
    public void addAutomobli(@RequestBody Automobile automobile){
        this.automobileService.saveAutomobileInfo(automobile);
    }
}

