package com.dm.biblioteca.services;

import com.dm.biblioteca.DTOs.UserRequestDTO;
import com.dm.biblioteca.DTOs.UserResponseDTO;
import com.dm.biblioteca.entity.User;
import com.dm.biblioteca.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String saveUser(UserRequestDTO userRequestDTO) {
        User user = new User(userRequestDTO.getName(), userRequestDTO.getEmail());
        userRepository.save(user);
        return "Usuário criado com sucesso!";
    }


    public List<UserResponseDTO> showUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> listUsers = users.stream().map(UserResponseDTO::new).toList();
        return listUsers;
    }


    public String searchById(long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            UserResponseDTO dto = new UserResponseDTO(user.get());
            return dto.toString();
        } else {
            return "Esse ID não é válido. Tente novamente.";
        }
    }

    public String updateEmail(long id, User newUser) {
        Optional<User> oldUser = userRepository.findById(id);

        if (oldUser.isPresent()) {
            User user = oldUser.get();
            user.setEmail(newUser.getEmail());
            userRepository.save(user);
            return "O email foi alterado com sucesso. Novo email: " + user.getEmail();
        } else {
            return "Esse ID não é válido.";
        }
    }

    public String deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.deleteById(id);
            return "Usuário deletado com sucesso!";
        } else {
            return "Esse ID não é válido";
        }
    }
}
