package org.example.digitalbanking.repositories;

import org.example.digitalbanking.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    // Tu peux ajouter des méthodes de recherche personnalisées ici si besoin
}