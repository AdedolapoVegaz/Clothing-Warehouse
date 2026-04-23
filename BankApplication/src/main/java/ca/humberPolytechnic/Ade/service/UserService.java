
package ca.humberPolytechnic.Ade.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ca.humberPolytechnic.Ade.model.AppUser;
import ca.humberPolytechnic.Ade.model.BankAccount;
import ca.humberPolytechnic.Ade.repository.BankAccountRepository;
import ca.humberPolytechnic.Ade.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       BankAccountRepository bankAccountRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole("ROLE_USER");
        AppUser savedUser = userRepository.save(appUser);

        BankAccount account = new BankAccount();
        account.setUser(savedUser);
        account.setBalance(BigDecimal.ZERO);
        account.setAccountNumber(generateAccountNumber());
        bankAccountRepository.save(account);
    }

    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private String generateAccountNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }
}