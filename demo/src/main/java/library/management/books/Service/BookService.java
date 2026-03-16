package library.management.books.Service;

import library.management.books.Dto.BookDto;
import library.management.books.Entity.AuthorEntity;
import library.management.books.Entity.BookEntity;
import library.management.books.Repo.AuthorRepo;
import library.management.books.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    public List<BookDto> createAll(List<BookDto> bookDto){
        List<BookEntity> books=bookDto.stream().map(dto ->{

            BookEntity book=new BookEntity();
            book.setAvailableCopies(dto.getAvailableCopies());
            book.setName(dto.getName());
            book.setCategory(dto.getCategory());

            AuthorEntity author=authorRepo.findById(dto.getAuthorId())
                    .orElseThrow(()->new RuntimeException("Author not found"));

            book.setAuthor(author);

            return book;


        }) .toList();
        List<BookEntity> savedBooks = bookRepo.saveAll(books);

        return savedBooks.stream().map(bookEntity ->
              new BookDto(
                      bookEntity.getId(),
                      bookEntity.getAuthor().getId(),
                      bookEntity.getName(),
                      bookEntity.getCategory(),
                      bookEntity.getAvailableCopies()
              )
        ).toList();
    }
}
