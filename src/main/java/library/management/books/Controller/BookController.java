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

    /*
     * This API is used for Thymeleaf UI search (frontend page rendering).
     * It searches books by name and returns the "books.html" page
     * with filtered book data added in the Model.
     *
     * NOTE:
     * - Used only for UI (not for JSON/API response)
     * - Works with Thymeleaf template (books.html)
     */
    @GetMapping("/search/page")
    public String searchBookPage(@RequestParam String name, Model model){

        List<BookDto> books = bookService.searchBookByName(name);

        model.addAttribute("books", books);

        return "books";
    }


    /*
     * This API is used for Thymeleaf UI (frontend page rendering) to search books by author name.
     *
     * FEATURE ADDED IN books.html:
     * - Author Search functionality (input field for author name)
     * - Displays filtered book list in the same books table
     * - Updates UI dynamically using Model (books attribute)
     *
     * FLOW:
     * 1. User enters author name in search field
     * 2. Request goes to /books/search/author/page
     * 3. Service layer filters books by author
     * 4. Filtered list is sent to "books.html"
     * 5. Table updates with matching results
     *
     * NOTE:
     * - This is for UI only (Thymeleaf view)
     * - Not a REST API (no JSON response)
     */
    @GetMapping("/search/author/page")
    public String searchByAuthorPage(@RequestParam String authorName, Model model){

        List<BookDto> books = bookService.searchBookByAuthor(authorName);

        model.addAttribute("books", books);

        return "books";
    }
   /*
     * Thymeleaf UI ke liye Add Book endpoint
     * Sirf ADMIN use kar sakta hai
     *
     * Flow:
     * 1. Admin Add Book form bharta hai — Book Name, Category, Copies, Author ID
     * 2. "Add Book" button click karta hai
     * 3. Ye endpoint call hota hai — book database mein save hoti hai
     * 4. Page refresh hota hai — nai book Book List mein dikh ti hai
     */
    @PostMapping("/add")
    public String addBook(@ModelAttribute BookDto bookDto, Model model){
        bookService.createAll(List.of(bookDto));
        List<BookDto> books = bookService.getAllBook();
        model.addAttribute("books", books);
        return "redirect:/books-page";
    }
    /*
     * Thymeleaf UI ke liye Delete Book endpoint
     * Sirf ADMIN use kar sakta hai
     *
     * Flow:
     * 1. Admin Delete button click karta hai
     * 2. Ye endpoint call hota hai — book database se delete hoti hai
     * 3. Page refresh hota hai — deleted book Book List se hat jaati hai
     *
     * Note:
     * - Book delete hone ke baad uski ID wapas nahi aati
     * - Next book add karne pe agle number ki ID milegi
     */

    @PostMapping("/delete/{id}")
    public String deleteBookPage(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/books-page";
    }

    /*
     * Thymeleaf UI ke liye Update/Edit Book endpoint
     * Sirf ADMIN use kar sakta hai
     *
     * Flow:
     * 1. Admin Edit button click karta hai — Modal open hota hai
     * 2. Book ka purana data Modal form mein prefill hota hai
     * 3. Admin changes karke "Update Book" click karta hai
     * 4. Ye endpoint call hota hai — book update hoti hai database mein
     * 5. Page refresh hota hai — updated data dikh ta hai
     */
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute BookDto bookDto){
        bookService.updateBooks(id, bookDto);
        return "redirect:/books-page";
    }

/*    @GetMapping("/search/author")
    public List<BookDto> searchBookByAuthor(@RequestParam String authorName){
        return bookService.searchBookByAuthor(authorName);
    }*/

    /*    @GetMapping("/search/book")
    public List<BookDto> searchBookByName(@RequestParam String name){
        return bookService.searchBookByName(name);
    }*/
}