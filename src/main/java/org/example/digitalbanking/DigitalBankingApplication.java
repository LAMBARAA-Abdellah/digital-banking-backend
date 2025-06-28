package org.example.digitalbanking;

import java.util.List;
import java.util.stream.Stream;

import org.example.digitalbanking.dtos.BankAccountDTO;
import org.example.digitalbanking.dtos.CurrentBankAccountDTO;
import org.example.digitalbanking.dtos.SavingBankAccountDTO;
import org.example.digitalbanking.entities.*;
import org.example.digitalbanking.exceptions.BalanceNotSuffisendException;
import org.example.digitalbanking.exceptions.BankAccountNotFoundException;
import org.example.digitalbanking.exceptions.CustomerNotFoundException;
import org.example.digitalbanking.dtos.CustomerDTO;
import org.example.digitalbanking.services.BankAccountService;
import org.example.digitalbanking.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankService bankService, BankAccountService bankAccountService) {
        return args -> {

            // 1. Créer des clients
            Stream.of("lambaraa", "alami", "idrissi").forEach(name -> {
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                bankAccountService.saveCustomer(customer);
            });

            // 2. Pour chaque client, créer des comptes
            bankAccountService.listCustomers().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random() * 90000, 9000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random() * 11402, 3.5, customer.getId());
                } catch (CustomerNotFoundException e) {
                    System.err.println("❌ Erreur client : " + e.getMessage());
                }
            });

            // 3. Pour chaque compte, effectuer crédit + débit
            List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount : bankAccounts) {
                String accountId = null;

                if (bankAccount instanceof SavingBankAccountDTO) {
                    accountId = ((SavingBankAccountDTO) bankAccount).getId();
                } else if (bankAccount instanceof CurrentBankAccountDTO) {
                    accountId = ((CurrentBankAccountDTO) bankAccount).getId();
                }

                if (accountId == null) {
                    System.err.println("⚠️ Compte inconnu, skipping...");
                    continue;
                }

                for (int i = 0; i < 10; i++) {
                    try {
                        double creditAmount = 1000 + Math.random() * 12500;
                        bankAccountService.credit(accountId, creditAmount, "Crédit automatique");

                        double debitAmount = 1000 + Math.random() * 9000;
                        bankAccountService.debit(accountId, debitAmount, "Débit automatique");

                    } catch (BankAccountNotFoundException | BalanceNotSuffisendException e) {
                        System.err.println("⚠️ Erreur sur opération : " + e.getMessage());
                    }
                }
            }
        };
    }

}
