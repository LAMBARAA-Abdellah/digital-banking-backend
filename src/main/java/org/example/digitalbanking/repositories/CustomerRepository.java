package org.example.digitalbanking.repositories;

import org.example.digitalbanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Exemple de méthode personnalisée
    Customer findByEmail(String email);
    List<Customer> findByNameContains(String keyword);
}
