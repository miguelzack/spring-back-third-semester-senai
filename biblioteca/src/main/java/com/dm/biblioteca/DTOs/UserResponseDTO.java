package com.dm.biblioteca.DTOs;

import com.dm.biblioteca.entity.Loan;
import com.dm.biblioteca.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class UserResponseDTO {
    private long id;
    private String name;
    private String email;
    private List<Loan> loans = new ArrayList<>();

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.loans = user.getLoans();
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", loans=" + loans +
                '}';
    }
}
