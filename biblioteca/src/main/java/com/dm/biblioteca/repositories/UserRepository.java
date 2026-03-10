package com.dm.biblioteca.repositories;

import com.dm.biblioteca.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(long id);

    List<User> id(long id);

    void deleteById(long id);
}
