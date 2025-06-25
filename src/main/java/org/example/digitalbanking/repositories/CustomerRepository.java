package org.example.digitalbanking.repositories;

import org.example.digitalbanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Exemple de méthode personnalisée
    Customer findByEmail(String email);
}
