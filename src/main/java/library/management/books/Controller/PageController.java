package library.management.books.Controller;
import library.management.books.Dto.BookDto;
import library.management.books.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books-page")
    public String booksPage(Model model){
        List<BookDto> books = bookService.getAllBook();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }

    @GetMapping("/authors-page")
    public String authorsPage() {
        return "authors";
    }

    @GetMapping("/users-page")
    public String usersPage() {
        return "users";
    }

    @GetMapping("/issue-page")
    public String issuePage() {
        return "issue-books";
    }
}
