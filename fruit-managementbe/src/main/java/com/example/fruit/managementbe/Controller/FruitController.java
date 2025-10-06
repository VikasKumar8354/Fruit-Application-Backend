package com.example.fruit.managementbe.Controller;

import com.example.fruit.managementbe.Model.FruitManagement;
import com.example.fruit.managementbe.Service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Fruit")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    @PostMapping("/addFruit")
    public FruitManagement addFruit(@RequestBody FruitManagement fruitManagement) {
        return fruitService.addFruit(fruitManagement);
    }

    @GetMapping("/getAllFruit")
    public List<FruitManagement> getAllFruit() {
        return fruitService.getAllFruits();
    }

    @GetMapping("/getFruit/{id}")
    public FruitManagement getFruit(@PathVariable long id) {
        return fruitService.getFruitById(id);
    }

    @PutMapping("/updateFruit/{id}")
    public FruitManagement updateFruit(@PathVariable long id, @RequestBody FruitManagement fruitManagement) {
        return fruitService.updateFruit(id, fruitManagement);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFruit(@PathVariable long id) {
        fruitService.deleteFruit(id);
    }
}
