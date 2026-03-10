package com.dm.biblioteca.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

public class WalletLibraryRequestDTO {
    private long user_id;

    public WalletLibraryRequestDTO(long user_id) {
        this.user_id = user_id;
    }
}
