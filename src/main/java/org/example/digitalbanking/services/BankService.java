package org.example.digitalbanking.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.digitalbanking.entities.AccountOperation;
import org.example.digitalbanking.entities.BankAccount;
import org.example.digitalbanking.repositories.AccountOperationRepository;
import org.example.digitalbanking.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BankService {

    private final BankAccountRepository bankAccountRepository;
    private final AccountOperationRepository accountOperationRepository;

    public void consulter(String accountId) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable : " + accountId));

        System.out.println("=== Compte trouvé ===");
        System.out.println("ID       : " + bankAccount.getId());
        System.out.println("Solde    : " + bankAccount.getBalance());
        System.out.println("Statut   : " + bankAccount.getStatus());
        System.out.println("Client   : " + bankAccount.getCustomer().getName());

        List<AccountOperation> operations = accountOperationRepository.findByBankAccountId(accountId);
        System.out.println("=== Opérations ===");
        operations.forEach(op -> {
            System.out.println(op.getOperationDate() + " | " + op.getType() + " | " + op.getAmount());
        });
    }
}
