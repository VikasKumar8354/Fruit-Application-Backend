package com.example.fruit.managementbe.Service;

import com.example.fruit.managementbe.Model.Vendor;
import com.example.fruit.managementbe.Repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    @Autowired
    VendorRepo vendorRepo;
    public Vendor addVendor(Vendor vendor){
        return vendorRepo.save(vendor);
    }
    public List<Vendor> getAllVendor(){
        return vendorRepo.findAll();
    }
    public Vendor getById(long id){
        return vendorRepo.findById(id).orElse(null);
    }
    public Vendor updateVendor(long id,Vendor vendor){
       vendor.setId(id);
       return vendorRepo.save(vendor);
    }
    public void deleteVendor(long id){
        vendorRepo.deleteById(id);
    }
}
