package org.example.digitalbanking;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;
import org.example.digitalbanking.entities.*;
import org.example.digitalbanking.enums.AccountStatus;
import org.example.digitalbanking.enums.OperationType;
import org.example.digitalbanking.repositories.*;
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
    CommandLineRunner testOperations(BankService bankService) {
        return args -> {
            String testAccountId = "013cb775-3524-472a-8666-135a76322349";
            bankService.consulter(testAccountId);
        };
    }

    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("Hassa", "Yassine", "Aicha").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });

            // 2. Afficher les clients
            System.out.println("=== Liste des clients enregistrés ===");
            customerRepository.findAll().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random() * 100);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random() * 90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(customer);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
                System.out.println("Nom: " + customer.getName());
                System.out.println("Email: " + customer.getEmail());
                System.out.println("------------------------");
            });
            bankAccountRepository.findAll().forEach(bankAccount -> {
                for (int i = 0; i < 5 ; i++){
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random() * 100001);
                    accountOperation.setType(Math.random()>0.5 ? OperationType.CREDIT: OperationType.DEBIT);
                    accountOperation.setBankAccount(bankAccount);
                    accountOperationRepository.save(accountOperation);
                }
            });
        };

    }
}
