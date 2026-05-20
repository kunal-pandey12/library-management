package library.management.books.Repo;

import library.management.books.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<BookEntity,Long> {

                    // Search books by book name
    List<BookEntity> findByNameContainingIgnoreCase(String name);

                // Search books by author name
    List<BookEntity> findByAuthor_NameContainingIgnoreCase(String authorName);
}
