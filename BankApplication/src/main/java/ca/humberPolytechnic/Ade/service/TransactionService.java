package ca.humberPolytechnic.Ade.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import ca.humberPolytechnic.Ade.model.BankAccount;
import ca.humberPolytechnic.Ade.model.TransactionRecord;
import ca.humberPolytechnic.Ade.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void saveTransaction(BankAccount account, String type, BigDecimal amount,
                                String description, String targetAccountNumber) {
        TransactionRecord record = new TransactionRecord();
        record.setAccount(account);
        record.setTransactionType(type);
        record.setAmount(amount);
        record.setDescription(description);
        record.setCreatedAt(LocalDateTime.now());
        record.setTargetAccountNumber(targetAccountNumber);
        transactionRepository.save(record);
    }

    public List<TransactionRecord> getTransactions(BankAccount account) {
        return transactionRepository.findByAccountOrderByCreatedAtDesc(account);
    }
}