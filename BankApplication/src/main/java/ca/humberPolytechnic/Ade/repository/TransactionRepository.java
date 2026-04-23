package ca.humberPolytechnic.Ade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.humberPolytechnic.Ade.model.BankAccount;
import ca.humberPolytechnic.Ade.model.TransactionRecord;

public interface TransactionRepository extends JpaRepository<TransactionRecord, Long> {
    List<TransactionRecord> findByAccountOrderByCreatedAtDesc(BankAccount account);
}