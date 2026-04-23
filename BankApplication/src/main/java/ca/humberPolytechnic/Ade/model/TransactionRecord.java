package ca.humberPolytechnic.Ade.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transaction_record")
public class TransactionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_type")
    private String transactionType;

    private BigDecimal amount;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "target_account_number")
    private String targetAccountNumber;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount account;
}
