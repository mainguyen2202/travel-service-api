package com.nlu.mainguyen.travelserviceapi.services;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.VisitorTModel;
import com.nlu.mainguyen.travelserviceapi.repositories.VisitorRepository;

@Service
public class VisitorService {

    private VisitorRepository vistorRepo;
    // constuctor
    public VisitorService(VisitorRepository initialVisitRepo) {
        // khởi tạo
        this.vistorRepo = initialVisitRepo;
    }

    public List<VisitorTModel> showAllUser(){
        return vistorRepo.findAll();
    }
    public VisitorTModel  createAVisitor(VisitorTModel newVisitor) {
        // TODO
        return vistorRepo.save(newVisitor);
    }

    public VisitorTModel getByIdAVisitor(Long id) {
        Optional<VisitorTModel> aVisitor = vistorRepo.findById(id);
        if(aVisitor.isPresent()) {
            return aVisitor.get();
        }
        return null;
    }
    public void updateAVisitor(VisitorTModel newVisitor) {
        vistorRepo.save(newVisitor);
    }
    public void deleteByID(Long id) {
        vistorRepo.deleteById(id);
    }

    
    
}