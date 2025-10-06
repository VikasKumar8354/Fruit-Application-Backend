package com.example.fruit.managementbe.Repository;

import com.example.fruit.managementbe.Model.FruitManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepo extends JpaRepository<FruitManagement, Long> {
}
