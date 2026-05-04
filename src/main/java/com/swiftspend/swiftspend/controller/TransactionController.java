package com.swiftspend.swiftspend.controller;

import com.swiftspend.swiftspend.model.Transaction;
import com.swiftspend.swiftspend.service.TransactionService;
import com.swiftspend.swiftspend.threads.MonthlySummaryThread;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public Transaction add(@RequestBody Transaction t) {
        return service.save(t);
    }

    @GetMapping
    public List<Transaction> getAll() {
        return service.getAll();
    }

    @GetMapping("/summary")
    public Map<String, Double> summary() {
        new MonthlySummaryThread().start();
        return service.getSummary();
    }

    @GetMapping("/export")
    public String export() throws Exception {
        service.exportFile();
        return "Exported Successfully!";
    }
}