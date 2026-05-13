package com.dm.foto.dtos;

import jakarta.validation.constraints.Email;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.dm.foto.entities.User}
 */
@Value
public class UserDto implements Serializable {
    Long id;
    String name;
    @Email
    String email;
    String photo;
}