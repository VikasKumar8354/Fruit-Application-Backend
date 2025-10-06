package com.example.fruit.managementbe.Controller;

import com.example.fruit.managementbe.Model.Vendor;
import com.example.fruit.managementbe.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Vendor")
public class VendorController {
    @Autowired
    VendorService vendorService;

    @PostMapping("/add")
    public Vendor addVendor(@RequestBody Vendor vendor){
        return vendorService.addVendor(vendor);
    }
    @GetMapping("/getAll")
    public List<Vendor>getAllVendor(){
        return vendorService.getAllVendor();
    }
    @GetMapping("/get/{id}")
    public Vendor getById(@PathVariable long id){
        return vendorService.getById(id);
    }
    @PutMapping ("/update/{id}")
    public Vendor updateVendor(@PathVariable long id,@RequestBody Vendor vendor){
        return vendorService.updateVendor(id, vendor);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteVendor(@PathVariable long id){
        vendorService.deleteVendor(id);
    }
}
