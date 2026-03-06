package com.dm.biblioteca.DTOs;

import com.dm.biblioteca.entity.WalletLibrary;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class WalletLibraryResponseDTO {
    private long numeroCarteira;
    private long user_id;
    private LocalDate dataEmissao;
    private boolean isValid;

    public WalletLibraryResponseDTO(WalletLibrary walletLibrary) {
        this.numeroCarteira = walletLibrary.getNumeroCarteira();
        this.user_id = walletLibrary.getUser().getId();
        this.dataEmissao = walletLibrary.getDataEmissao();
        this.isValid = walletLibrary.isValid();
    }

    @Override
    public String toString() {
        return "WalletLibraryResponseDTO{" +
                "numeroCarteira=" + numeroCarteira +
                ", user_id=" + user_id +
                ", dataEmissao=" + dataEmissao +
                ", isValid=" + isValid +
                '}';
    }
}
