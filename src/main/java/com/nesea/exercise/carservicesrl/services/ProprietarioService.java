package com.nesea.exercise.carservicesrl.services;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.nesea.exercise.carservicesrl.model.Automobile;
import com.nesea.exercise.carservicesrl.model.Proprietario;
import com.nesea.exercise.carservicesrl.repository.ProprietarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sun.plugin.perf.PluginRollup;

import java.util.List;
import java.util.Optional;

@Service
public class ProprietarioService {

    private ProprietarioRepository proprietarioRepository;

    public ProprietarioService(ProprietarioRepository proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    public Optional<Proprietario> getProprietarioById (Integer Id) {
        return proprietarioRepository.findById(Id);
    }
    public List<Proprietario> getAllProprietari () {
        return  proprietarioRepository.findAll();
    }

    public List<Proprietario> getAllProprietariWithoutAutomobili () {
        return  proprietarioRepository.findAllByAutomobile_Owner_IdNull();
    }

    public Optional<Proprietario> getProprietarioByAutomobile (Integer Id) {
        return proprietarioRepository.findByAutomobile_Id(Id);
    }

    public void saveProprietario (Proprietario proprietario) {
        proprietarioRepository.save(proprietario);
    }

    public void deleteProprietario (Integer Id) {
        proprietarioRepository.deleteById(Id);
    }

    public Page< Proprietario > getAllProprietariPaginated(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page< Proprietario > resultPage;
        return proprietarioRepository.findAll(pageable);
    }

}
