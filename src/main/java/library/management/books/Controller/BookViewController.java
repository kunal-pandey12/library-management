package library.management.books.Controller;

import library.management.books.Dto.BookDto;
import library.management.books.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookViewController {

    @Autowired
    private BookService bookService;

    /*
     * GET /books/search/page
     * User aur Admin dono kar sakte hain
     * Book name se search karta hai
     * Result books.html pe bhejta hai
     */
    @GetMapping("/search/page")
    public String searchBookPage(@RequestParam String name, Model model) {
        List<BookDto> books = bookService.searchBookByName(name);
        model.addAttribute("books", books);
        return "books";
    }

    /*
     * GET /books/search/author/page
     * User aur Admin dono kar sakte hain
     * Author name se book search karta hai
     * Result books.html pe bhejta hai
     */
    @GetMapping("/search/author/page")
    public String searchByAuthorPage(@RequestParam String authorName, Model model) {
        List<BookDto> books = bookService.searchBookByAuthor(authorName);
        model.addAttribute("books", books);
        return "books";
    }

    /*
     * GET /books/search/admin/page
     * Sirf ADMIN kar sakta hai
     * Admin Panel — Book name se search karta hai
     * Result books.html pe bhejta hai
     */
    @GetMapping("/search/admin/page")
    public String searchBookAdminPage(@RequestParam String name, Model model) {
        List<BookDto> books = bookService.searchBookByName(name);
        model.addAttribute("books", books);
        return "books";
    }

    /*
     * GET /books/search/admin/author/page
     * Sirf ADMIN kar sakta hai
     * Admin Panel — Author name se book search karta hai
     * Result books.html pe bhejta hai
     */
    @GetMapping("/search/admin/author/page")
    public String searchByAuthorAdminPage(@RequestParam String authorName, Model model) {
        List<BookDto> books = bookService.searchBookByAuthor(authorName);
        model.addAttribute("books", books);
        return "books";
    }

    /*
     * POST /books/add
     * Sirf ADMIN use kar sakta hai
     * Add Book form se data aata hai
     * Book database mein save hoti hai
     */
    @PostMapping("/add")
    public String addBook(@ModelAttribute BookDto bookDto, Model model) {
        bookService.createAll(List.of(bookDto));
        List<BookDto> books = bookService.getAllBook();
        model.addAttribute("books", books);
        return "redirect:/books-page";
    }

    /*
     * POST /books/delete/{id}
     * Sirf ADMIN use kar sakta hai
     * Book ID se delete hoti hai
     */
    @PostMapping("/delete/{id}")
    public String deleteBookPage(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books-page";
    }

    /*
     * POST /books/update/{id}
     * Sirf ADMIN use kar sakta hai
     * Edit modal se updated data aata hai
     * Book update hoti hai database mein
     */
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute BookDto bookDto) {
        bookService.updateBooks(id, bookDto);
        return "redirect:/books-page";
    }
}