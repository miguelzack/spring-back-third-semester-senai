package com.dm.biblioteca.services;

import com.dm.biblioteca.DTOs.LoanRequestDTO;
import com.dm.biblioteca.DTOs.LoanResponseDTO;
import com.dm.biblioteca.entity.Loan;
import com.dm.biblioteca.entity.User;
import com.dm.biblioteca.repositories.LoanRepository;
import com.dm.biblioteca.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LoanService {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    public LoanService(LoanRepository loanRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    public String saveLoan(@Valid LoanRequestDTO loanRequestDTO) {
        Optional<User> userLoan = userRepository.findById(loanRequestDTO.getUser_id());
        if (userLoan.isPresent() && userLoan.get().getWalletLibrary().isValid()) {
            User userId = userLoan.get();

            Loan loan = new Loan(userId);
            loanRepository.save(loan);
            return "Loan created!";
        } else {
            return "This ID is not valid.";
        }
    }

    public List<LoanResponseDTO> showLoans() {
        List<Loan> loans = loanRepository.findAll();
        List<LoanResponseDTO> listLoans = loans.stream().map(LoanResponseDTO::new).toList();
        return listLoans;
    }


    public String searchById(long id) {
        Optional<Loan> loan = loanRepository.findById(id);

        if (loan.isPresent()) {
            LoanResponseDTO dto = new LoanResponseDTO(loan.get());
            return dto.toString();
        } else {
            return "This ID is not valid. Please try again.";
        }
    }
    
}
