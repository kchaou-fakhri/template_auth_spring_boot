package com.dev0kch.mybook.repository;

import com.dev0kch.mybook.model.Mobinaute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Mobinaute, Long> {

    @Query(
            value = "SELECT * FROM mobinaute WHERE nom_utilisateur = ?1",
            nativeQuery = true)
    Mobinaute findByUsername(String username);
}
