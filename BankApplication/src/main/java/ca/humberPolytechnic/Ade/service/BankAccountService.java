package ca.humberPolytechnic.Ade.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import ca.humberPolytechnic.Ade.model.AppUser;
import ca.humberPolytechnic.Ade.model.BankAccount;
import ca.humberPolytechnic.Ade.repository.BankAccountRepository;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount getAccountByUser(AppUser user) {
        return bankAccountRepository.findByUser(user);
    }

    public void deposit(BankAccount account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
        bankAccountRepository.save(account);
    }

    public boolean withdraw(BankAccount account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) >= 0) {
            account.setBalance(account.getBalance().subtract(amount));
            bankAccountRepository.save(account);
            return true;
        }
        return false;
    }

    public BankAccount findByAccountNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }

    public void save(BankAccount account) {
        bankAccountRepository.save(account);
    }
}