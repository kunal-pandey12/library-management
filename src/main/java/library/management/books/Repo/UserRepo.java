package library.management.books.Repo;

import library.management.books.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity>findByEmail(String email);
    List<UserEntity> findByNameContainingIgnoreCase(String name);
}
