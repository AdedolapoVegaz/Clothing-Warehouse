package ca.humberPolytechnic.Ade.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bank_account")

public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@Column(unique = true)
	private String accountNumber;
	
	private BigDecimal balance;
	
	@OneToOne
	@JoinColumn(name ="user_id")  //create FK in the database
	private AppUser user;
	
}
