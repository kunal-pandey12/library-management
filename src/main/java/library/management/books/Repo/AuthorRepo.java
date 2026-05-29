package library.management.books.Repo;

import library.management.books.Entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepo extends JpaRepository<AuthorEntity,Long> {
    List<AuthorEntity> findByNameContainingIgnoreCase(String name);
}
