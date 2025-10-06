package com.example.fruit.managementbe.Service;

import com.example.fruit.managementbe.Model.Item;
import com.example.fruit.managementbe.Model.Vendor;
import com.example.fruit.managementbe.Repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepo itemRepo;


    public Item addItem(Item item){
        return itemRepo.save(item);
    }
    public List<Item> viewAllItem(){
        return itemRepo.findAll();
    }
    public Item viewById(long id){
        return itemRepo.findById(id).orElse(null);
    }
    public Item updateItem(long id, Item item){
        item.setId(id);
        return itemRepo.save(item);
    }
    public void deleteItem(long id){
        itemRepo.deleteById(id);
    }
}
