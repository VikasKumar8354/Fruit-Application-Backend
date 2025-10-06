package com.example.fruit.managementbe.Repository;

import com.example.fruit.managementbe.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item,Long> {
}
