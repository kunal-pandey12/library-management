package library.management.books.Controller;
import library.management.books.Dto.BookDto;
import library.management.books.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping("/create")
    public List<BookDto> createBooks(@RequestBody List<BookDto> bookDto){
        return bookService.createAll(bookDto);
    }
    @GetMapping("getAll")
    public List<BookDto>getAllBook(){
        return bookService.getAllBook();
    }
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }
    @PutMapping("/{id}")
    public BookDto updateBookById(@PathVariable Long id,@RequestBody BookDto bookDto){
        return bookService.updateBooks(id, bookDto);
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "delete successfully book id";
    }

    @GetMapping("/search/author")
    public List<BookDto> searchBookByAuthor(@RequestParam String authorName){
        return bookService.searchBookByAuthor(authorName);
    }

        @GetMapping("/search/book")
    public List<BookDto> searchBookByName(@RequestParam String name){
        return bookService.searchBookByName(name);
    }
}