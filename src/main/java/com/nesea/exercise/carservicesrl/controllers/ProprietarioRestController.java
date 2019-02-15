package com.nesea.exercise.carservicesrl.controllers;

import com.nesea.exercise.carservicesrl.model.Proprietario;
import com.nesea.exercise.carservicesrl.services.ProprietarioService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


/****************************************MANCA GESTIONE DTO *****************************************************/
/****************************************MANCA GESTIONE DTO  *****************************************************/
/****************************************MANCA GESTIONE DTO  *****************************************************/

@RestController
@RequestMapping("/api/proprietari")
public class ProprietarioRestController {
    private ProprietarioService proprietarioService;

    public ProprietarioRestController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proprietario> getProprietario(@PathVariable Integer id){
        Optional<Proprietario> proprietarioById = proprietarioService.getProprietarioById(id);
        AtomicReference<ResponseEntity< Proprietario >> response = new AtomicReference<>(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        proprietarioById.ifPresent(proprietario -> response.set(new ResponseEntity<>(proprietario, HttpStatus.OK)));
        return response.get();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProprietario(@PathVariable("id") Integer id) {
        proprietarioService.deleteProprietario(id);
    }
    /**
     * Standard api with get method in a generic query string search parameter.
     * A sample search with pagination.
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/allPaged")
    public ResponseEntity<List<Proprietario>> getAutomobiliPage(@RequestParam("page")int page, @RequestParam("size") int size){
        Page< Proprietario> resultPage = proprietarioService.getAllProprietariPaginated(page,size);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(resultPage.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Proprietario>> getAllProprietari(){
        List<Proprietario> result = proprietarioService.getAllProprietari();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }


    @PostMapping("/add")
    @PutMapping("/update")
    public void addProprietario(@RequestBody Proprietario proprietario){
        this.proprietarioService.saveProprietario(proprietario);
    }
}

