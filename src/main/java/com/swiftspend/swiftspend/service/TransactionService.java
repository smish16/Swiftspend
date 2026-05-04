package com.swiftspend.swiftspend.service;

import com.swiftspend.swiftspend.model.Transaction;
import com.swiftspend.swiftspend.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.*;

@Service
public class TransactionService {

    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public Transaction save(Transaction t) {
        return repo.save(t);
    }

    public List<Transaction> getAll() {
        return repo.findAll();
    }

    public Map<String, Double> getSummary() {
        List<Transaction> list = repo.findAll();
        Map<String, Double> map = new HashMap<>();

        for (Transaction t : list) {
            map.put(
                    t.getCategory(),
                    map.getOrDefault(t.getCategory(), 0.0) + t.getAmount()
            );
        }

        return map;
    }

    public void exportFile() throws Exception {
        List<Transaction> list = repo.findAll();
        FileWriter writer = new FileWriter("statement.csv");

        writer.write("ID,Type,Category,Amount,Date\n");

        for (Transaction t : list) {
            writer.write(t.getId() + "," +
                    t.getType() + "," +
                    t.getCategory() + "," +
                    t.getAmount() + "," +
                    t.getDate() + "\n");
        }

        writer.close();
    }
}