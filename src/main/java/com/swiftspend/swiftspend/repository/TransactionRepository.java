package com.swiftspend.swiftspend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.swiftspend.swiftspend.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}