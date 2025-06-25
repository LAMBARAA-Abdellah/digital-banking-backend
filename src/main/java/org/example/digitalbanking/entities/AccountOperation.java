package org.example.digitalbanking.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.digitalbanking.enums.OperationType;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    private String description;

    @ManyToOne
    private BankAccount bankAccount;
}
