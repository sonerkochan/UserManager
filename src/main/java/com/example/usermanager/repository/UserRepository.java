package com.example.usermanager.repository;

import com.example.usermanager.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE " +
            "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<User> findBySearchTerm(String searchTerm, Sort sort);

    @Query(value = "SELECT * FROM users u ORDER BY u.last_name LIMIT :size OFFSET :offset", nativeQuery = true)
    List<User> findUsersWithPagination(@Param("offset") int offset, @Param("size") int size);
}