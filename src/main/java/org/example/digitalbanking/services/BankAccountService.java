package org.example.digitalbanking.services;

import org.example.digitalbanking.entities.BankAccount;
import org.example.digitalbanking.entities.CurrentAccount;
import org.example.digitalbanking.entities.Customer;
import org.example.digitalbanking.entities.SavingAccount;
import org.example.digitalbanking.exceptions.BalanceNotSuffisendException;
import org.example.digitalbanking.exceptions.BankAccountNotFoundException;
import org.example.digitalbanking.exceptions.CustomerNotFoundException;
import org.example.digitalbanking.mappers.CustomerDTO;

import java.util.List;

public interface BankAccountService {

    CustomerDTO saveCustomer(CustomerDTO customerDTO);


    CurrentAccount saveCurrentBankAccount(double initialBalane, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalane, double interstRate, Long customerId) throws CustomerNotFoundException;

    List<CustomerDTO> listCustomers();
    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;
    BankAccount getBankAccount(String accounId) throws BankAccountNotFoundException;
    void debit(String accounId, double amount, String Description) throws BankAccountNotFoundException, BalanceNotSuffisendException;
    void credit(String accounId, double amount, String Description) throws BankAccountNotFoundException;
    void transfer(String fromAccounId, String toAccounId, double amount) throws BankAccountNotFoundException, BalanceNotSuffisendException;


    List<BankAccount> bankAccountList();

    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long customerId);
}
