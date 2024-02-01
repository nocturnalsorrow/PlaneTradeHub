package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.Model;
import com.dm.planetradehub.repository.ManufacturerRepository;
import com.dm.planetradehub.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService{

    private final ManufacturerService manufacturerService;

    private final ModelRepository modelRepository;


    public ModelServiceImpl(ManufacturerService manufacturerService, ModelRepository modelRepository) {
        this.manufacturerService = manufacturerService;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Model> getAllModels() {
        return null;
    }

    @Override
    public Model getModelById(Long id) {
        return null;
    }

    @Override
    public Model saveModel(Model model) {
        return null;
    }

    @Override
    public List<Model> getModelsByManufacturer(String manufacturer) {
        if (manufacturer == ""){
            return getAllModels();
        }
        else
            return modelRepository.getModelsByManufacturerId(manufacturerService.getManufacturerByName(manufacturer).getId());
    }
}
