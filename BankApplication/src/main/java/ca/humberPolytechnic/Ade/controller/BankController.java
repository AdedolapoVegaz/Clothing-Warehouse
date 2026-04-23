package ca.humberPolytechnic.Ade.controller;

import java.math.BigDecimal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.humberPolytechnic.Ade.model.AppUser;
import ca.humberPolytechnic.Ade.model.BankAccount;
import ca.humberPolytechnic.Ade.service.BankAccountService;
import ca.humberPolytechnic.Ade.service.TransactionService;
import ca.humberPolytechnic.Ade.service.UserService;

@Controller
public class BankController {

    private final UserService userService;
    private final BankAccountService bankAccountService;
    private final TransactionService transactionService;

    public BankController(UserService userService,
                          BankAccountService bankAccountService,
                          TransactionService transactionService) {
        this.userService = userService;
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        AppUser user = userService.findByUsername(authentication.getName());
        BankAccount account = bankAccountService.getAccountByUser(user);

        model.addAttribute("account", account);
        model.addAttribute("user", user);
        return "dashboard";
    }
    
    @GetMapping("/deposit")
    public String depositPage() {
        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam BigDecimal amount, Authentication authentication) {
        AppUser user = userService.findByUsername(authentication.getName());
        BankAccount account = bankAccountService.getAccountByUser(user);

        bankAccountService.deposit(account, amount);
        transactionService.saveTransaction(account, "DEPOSIT", amount, "Money deposited", null);

        return "redirect:/dashboard";
    }

    @GetMapping("/withdraw")
    public String withdrawPage() {
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam BigDecimal amount, Authentication authentication, Model model) {
        AppUser user = userService.findByUsername(authentication.getName());
        BankAccount account = bankAccountService.getAccountByUser(user);

        boolean success = bankAccountService.withdraw(account, amount);

        if (success) {
            transactionService.saveTransaction(account, "WITHDRAW", amount, "Money withdrawn", null);
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Insufficient balance");
        return "withdraw";
    }
    
    @GetMapping("/transfer")
    public String transferPage() {
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam String targetAccountNumber,
                           @RequestParam BigDecimal amount,
                           Authentication authentication,
                           Model model) {

        AppUser user = userService.findByUsername(authentication.getName());
        BankAccount sender = bankAccountService.getAccountByUser(user);
        BankAccount receiver = bankAccountService.findByAccountNumber(targetAccountNumber);

        if (receiver == null) {
            model.addAttribute("error", "Receiver account not found");
            return "transfer";
        }

        boolean success = bankAccountService.withdraw(sender, amount);

        if (!success) {
            model.addAttribute("error", "Insufficient balance");
            return "transfer";
        }

        bankAccountService.deposit(receiver, amount);

        transactionService.saveTransaction(sender, "TRANSFER OUT", amount,
                "Transfer sent", receiver.getAccountNumber());

        transactionService.saveTransaction(receiver, "TRANSFER IN", amount,
                "Transfer received", sender.getAccountNumber());

        return "redirect:/dashboard";
    }
    
    @GetMapping("/transactions")
    public String transactions(Model model, Authentication authentication) {
        AppUser user = userService.findByUsername(authentication.getName());
        BankAccount account = bankAccountService.getAccountByUser(user);

        model.addAttribute("transactions", transactionService.getTransactions(account));
        return "transactions";
    }
}
    
    
    
    