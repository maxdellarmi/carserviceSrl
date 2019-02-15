package com.nesea.exercise.carservicesrl.services;

import com.nesea.exercise.carservicesrl.model.Automobile;
import com.nesea.exercise.carservicesrl.model.Proprietario;
import com.nesea.exercise.carservicesrl.repository.AutomobileRepository;
import com.nesea.exercise.carservicesrl.repository.ProprietarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AutomobileService {

    private AutomobileRepository automobileRepository;
    private ProprietarioRepository proprietarioRepository;

    public AutomobileService(AutomobileRepository automobileRepository, ProprietarioRepository proprietarioRepository) {
        this.automobileRepository = automobileRepository;
        this.proprietarioRepository = proprietarioRepository;
    }

    public void saveAutomobileInfo(Automobile automobile) {
        automobileRepository.save(automobile);
    }

    public void sellAutomobileToNewOwner (Automobile auto, Integer ownerId){
        /***relazione 1 a uno quindi va eliminato dall'altra macchina ***/
        Optional<Automobile> automobileByProprietario = findAutomobileByProprietario(auto.getOwner().getId());
        automobileByProprietario.ifPresent( macchina -> {
            macchina.setOwner(null);
            automobileRepository.save(macchina);
        });

        /****NON SALVA BENE LA DATA *****/
        Optional<Proprietario> proprietario = proprietarioRepository.findById(ownerId);
        proprietario.ifPresent(owner -> {
            auto.setOwner(owner);
            //auto.setDataVendita(LocalDate.now());
            automobileRepository.save(auto);
        });
    }

    public Optional<Automobile> getAutomobileById (Integer Id) {
        return automobileRepository.findById(Id);
    }

    public List<Automobile> getAllAutomobili () {
        return  automobileRepository.findAll();
    }

    public Page< Automobile > getAllAutomobiliPaginated(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page< Automobile > resultPage = automobileRepository.findAll(pageable);
        return  resultPage;
    }

    public Optional<Automobile> findAutomobileByProprietario (Integer Id){
        return automobileRepository.findByOwner_Id(Id);
    }

    public List<Automobile> findAutomobiliSelledAfter (LocalDate date){
        return automobileRepository.findAllByDataVenditaAfter(date);
    }

    public List<Automobile> findAutomobiliSelledRange (LocalDate datestart, LocalDate dateend){
        return automobileRepository.findAllByDataVenditaBetween(datestart,dateend);
    }

    public void deteleAutomobile( Integer id) {
        automobileRepository.deleteById(id);
    }
}
