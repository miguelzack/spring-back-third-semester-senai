package com.dm.biblioteca.DTOs;

import com.dm.biblioteca.entity.WalletLibrary;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class WalletLibraryResponseDTO {
    private long numberWallet;
    private long user_id;
    private LocalDate dataEmission;
    private boolean isValid;

    public WalletLibraryResponseDTO(WalletLibrary walletLibrary) {
        this.numberWallet = walletLibrary.getNumberWallet();
        this.user_id = walletLibrary.getUser().getId();
        this.dataEmission = walletLibrary.getDataEmission();
        this.isValid = walletLibrary.isValid();
    }

    @Override
    public String toString() {
        return "WalletLibraryResponseDTO{" +
                "numberWallet=" + numberWallet +
                ", user_id=" + user_id +
                ", dataEmission=" + dataEmission +
                ", isValid=" + isValid +
                '}';
    }
}
