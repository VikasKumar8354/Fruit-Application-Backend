package com.example.fruit.managementbe.Service;

import com.example.fruit.managementbe.Model.FruitManagement;
import com.example.fruit.managementbe.Repository.FruitRepo;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class FruitService {
    @Autowired
    FruitRepo fruitRepo;

    public FruitManagement addFruit(FruitManagement fruitManagement){
        return fruitRepo.save(fruitManagement);
    }
    public List<FruitManagement>getAllFruits(){
        return fruitRepo.findAll();
    }
    public FruitManagement getFruitById(long id){
        return fruitRepo.findById(id).orElse(null);
    }
    public FruitManagement updateFruit(long id,FruitManagement fruitManagement){
        fruitManagement.setId(id);
        return fruitRepo.save(fruitManagement);
    }

    public void deleteFruit(long id){

        fruitRepo.deleteById(id);
    }
}
