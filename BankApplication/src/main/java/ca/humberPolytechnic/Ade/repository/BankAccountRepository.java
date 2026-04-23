package ca.humberPolytechnic.Ade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.humberPolytechnic.Ade.model.AppUser;
import ca.humberPolytechnic.Ade.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{
	BankAccount findByUser(AppUser user);
	BankAccount findByAccountNumber(String accountNumber);
}
