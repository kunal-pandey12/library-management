package library.management.books.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }

    @GetMapping("/books-page")
    public String booksPage() {
        return "books";
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
