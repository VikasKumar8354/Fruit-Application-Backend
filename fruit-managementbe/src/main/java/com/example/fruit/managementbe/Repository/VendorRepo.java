package com.example.fruit.managementbe.Repository;

import com.example.fruit.managementbe.Model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepo extends JpaRepository<Vendor,Long> {
}
