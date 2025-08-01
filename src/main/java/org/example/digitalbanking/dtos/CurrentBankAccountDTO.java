package org.example.digitalbanking.dtos;

import lombok.Data;
import org.example.digitalbanking.enums.AccountStatus;

import java.util.Date;


@Data

public class CurrentBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;
}
