package com.dm.biblioteca.services;

import com.dm.biblioteca.DTOs.*;
import com.dm.biblioteca.entity.User;
import com.dm.biblioteca.entity.WalletLibrary;
import com.dm.biblioteca.repositories.UserRepository;
import com.dm.biblioteca.repositories.WalletLibraryRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WalletLibraryService {
    private final WalletLibraryRepository walletLibraryRepository;
    private final UserRepository userRepository;

    public WalletLibraryService(WalletLibraryRepository walletLibraryRepository, UserRepository userRepository) {
        this.walletLibraryRepository = walletLibraryRepository;
        this.userRepository = userRepository;
    }


    public String saveWallet(@Valid WalletLibraryRequestDTO walletRequestDTO) {
        Optional<User> userWallet = userRepository.findById(walletRequestDTO.getUser_id());
        if (userWallet.isPresent()) {
            User userId = userWallet.get();

            WalletLibrary wallet = new WalletLibrary(userId);
            walletLibraryRepository.save(wallet);
            return "Carteira criada!";
        } else {
            return "Esse ID não é válido.";
        }
    }


    public String searchById(long numberWallet) {
        Optional<WalletLibrary> wallet = walletLibraryRepository.findByNumberWallet(numberWallet);

        if (wallet.isPresent()) {
            WalletLibraryResponseDTO dto = new WalletLibraryResponseDTO(wallet.get());
            return dto.toString();
        } else {
            return "Esse ID não é válido. Tente novamente.";
        }
    }

}
