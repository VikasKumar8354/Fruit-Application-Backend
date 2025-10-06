package com.example.fruit.managementbe.Controller;
import com.example.fruit.managementbe.Model.Item;
import com.example.fruit.managementbe.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    @PostMapping("/addItem")
    public Item additem(@RequestBody Item item){
        return itemService.addItem(item);
    }
    @GetMapping("/viewAllItem")
    public List<Item> viewAllItem(){
        return itemService.viewAllItem();
    }
    @GetMapping("/viewItem/{id}")
    public Item viewItem(@PathVariable long id){

        return itemService.viewById(id);
    }
    @PutMapping("/update/{id}")
    public Item updateItem(@PathVariable long id,@RequestBody Item item)
    {
       return itemService.updateItem(id,item);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable long id){

        itemService.deleteItem(id);
    }
}
