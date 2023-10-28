package app.linguistai.bmvp.repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.linguistai.bmvp.model.User;

// @Qualifier("accountjpa")
@Repository
// @Transactional
public interface IAccountRepository extends JpaRepository<User, UUID> {
    List<User> findAllById(UUID id);
    Optional<User> findUserById(UUID id);
    Optional<User> findUserByEmail(String email);
    boolean existsByEmail(String email);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    int updatePassword(@Param("password") String newPassword, @Param("id") UUID id);
}