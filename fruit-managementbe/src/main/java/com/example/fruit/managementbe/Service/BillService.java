package com.example.fruit.managementbe.Service;
import com.example.fruit.managementbe.DTOs.BillDTO;
import com.example.fruit.managementbe.Model.Bill;
import com.example.fruit.managementbe.Model.BillItem;
import com.example.fruit.managementbe.Model.BillStatus;
import com.example.fruit.managementbe.Repository.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {
    @Autowired
    BillRepo billRepo;
    public BillService(BillRepo billRepo) {

        this.billRepo = billRepo;
    }
    public Bill createBill(BillDTO dto) {
        Bill bill = new Bill();
        bill.setBillNo(dto.getBillNo());
        bill.setBillDate(dto.getBillDate());
        bill.setCustomerName(dto.getCustomerName());
        bill.setPaymentType(dto.getPaymentType());
        bill.setPaidAmount(dto.getPaidAmount());
        bill.setTotalAmount(dto.getTotalAmount());
        bill.setAdvancePayment(dto.getAdvancePayment());

        bill.setPendingAmount(dto.getTotalAmount() - dto.getPaidAmount());
        bill.setStatus(bill.getPendingAmount() == 0 ? BillStatus.PAID : BillStatus.PENDING);

        List<BillItem> items = dto.getItems().stream().map(i -> {
            BillItem item = new BillItem();
            item.setItemName(i.getItemName());
            item.setLotNo(i.getLotNo());
            item.setCompany(i.getCompany());
            item.setWeight(i.getWeight());
            item.setRate(i.getRate());
            item.setUnit(i.getUnit());
            item.setBasicAmount(i.getBasicAmount());
            item.setExpenses(i.getExpenses());
            item.setNetAmount(i.getNetAmount());
            item.setBill(bill);
            return item;
        }).collect(Collectors.toList());

        bill.setItems(items);

        return billRepo.save(bill);
    }

    public List<Bill> getAllBills() {
        return billRepo.findAll();
    }

    public Bill getBillById(Long id) {
        return billRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + id));
    }

    public Bill updateBill(Long id, BillDTO dto) {
        Bill bill = getBillById(id);

        bill.setBillNo(dto.getBillNo());
        bill.setBillDate(dto.getBillDate());
        bill.setCustomerName(dto.getCustomerName());
        bill.setPaymentType(dto.getPaymentType());
        bill.setPaidAmount(dto.getPaidAmount());
        bill.setTotalAmount(dto.getTotalAmount());
        bill.setAdvancePayment(dto.getAdvancePayment());
        bill.setPendingAmount(dto.getTotalAmount() - dto.getPaidAmount());
        bill.setStatus(bill.getPendingAmount() == 0 ? BillStatus.PAID : BillStatus.PENDING);

        bill.getItems().clear();
        List<BillItem> newItems = dto.getItems().stream().map(i -> {
            BillItem item = new BillItem();
            item.setItemName(i.getItemName());
            item.setLotNo(i.getLotNo());
            item.setCompany(i.getCompany());
            item.setWeight(i.getWeight());
            item.setRate(i.getRate());
            item.setUnit(i.getUnit());
            item.setBasicAmount(i.getBasicAmount());
            item.setExpenses(i.getExpenses());
            item.setNetAmount(i.getNetAmount());
            item.setBill(bill);
            return item;
        }).collect(Collectors.toList());

        bill.getItems().addAll(newItems);

        return billRepo.save(bill);
    }

    public void deleteBill(Long id) {
        if (!billRepo.existsById(id)) {
            throw new RuntimeException("Bill not found with id: " + id);
        }
        billRepo.deleteById(id);
    }
    public Page<Bill> getBillsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return billRepo.findAll(pageable);
    }
}

