package com.example.fruit.managementbe.Controller;
import com.example.fruit.managementbe.DTOs.BillDTO;
import com.example.fruit.managementbe.Model.Bill;
import com.example.fruit.managementbe.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class BillController {
    @Autowired
    BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/createBill")
    public Bill createBill(@RequestBody BillDTO dto) {
        return billService.createBill(dto);
    }

    @GetMapping("/getAllBill")
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("/getBill/{id}")
    public Bill getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    @PutMapping("/updateBill/{id}")
    public Bill updateBill(@PathVariable Long id, @RequestBody BillDTO dto) {
        return billService.updateBill(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
    }
    @GetMapping("/page")
    public Page<Bill> getBillsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return billService.getBillsWithPagination(page, size);
    }
}

