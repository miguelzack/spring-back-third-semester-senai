package com.dm.biblioteca.repositories;

import com.dm.biblioteca.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    Optional<Loan> findById(long id);

    void deleteById(long id);
}
