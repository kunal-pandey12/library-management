package library.management.books.Controller;

import library.management.books.Entity.AuthorEntity;
import library.management.books.Repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * AuthorViewController — Thymeleaf Pages ke liye Controller
 *
 * Ye controller sirf HTML pages handle karta hai (Thymeleaf).
 * REST API ke liye alag AuthorController.java hai.
 *
 * Pages:
 *   - author.html → Authors ka poora page (list, add, edit, delete)
 *
 * Endpoints:
 *   GET  /authors            → Sabhi authors ki list dikhao
 *   GET  /authors/search     → Name se author search karo
 *   POST /authors/add        → Naya author add karo
 *   POST /authors/update/{id}→ Author update karo
 *   POST /authors/delete/{id}→ Author delete karo
 */
@Controller
@RequestMapping("/authors")
public class AuthorViewController {

    /**
     * AuthorRepository — Database se directly kaam karta hai
     * Author ka data save, update, delete, fetch sab yahi karta hai
     */
    @Autowired

    private AuthorRepo authorRepository;

    /**
     * GET /authors
     * Sabhi authors ki list fetch karke author.html page pe bhejta hai
     * User aur Admin dono dekh sakte hain
     */
    @GetMapping
    public String getAllAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }

    /**
     * GET /authors/search?name=xyz
     * Name se author search karta hai (case insensitive)
     * e.g. "chetan" likhne pe "Chetan Bhagat" bhi milega
     * Result wapas author.html pe bhejta hai
     */
    @GetMapping("/search")
    public String searchByName(@RequestParam String name, Model model) {
        model.addAttribute("authors",
                authorRepository.findByNameContainingIgnoreCase(name));
        return "authors";
    }

    /**
     * POST /authors/add
     * Form se naya author ka data aata hai aur database mein save hota hai
     * Save hone ke baad /authors page pe redirect ho jaata hai
     * Sirf ADMIN use kar sakta hai (Security Config mein set hai)
     */
    @PostMapping("/add")
    public String addAuthor(@ModelAttribute AuthorEntity author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    /**
     * POST /authors/update/{id}
     * Edit modal se updated data aata hai
     * Pehle purana author ID se dhundta hai
     * Phir name, email, number update karke save karta hai
     * Sirf ADMIN use kar sakta hai (Security Config mein set hai)
     */
    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable Long id,
                               @ModelAttribute AuthorEntity updatedAuthor) {
        AuthorEntity author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            author.setName(updatedAuthor.getName());
            author.setEmail(updatedAuthor.getEmail());
            author.setNumber(updatedAuthor.getNumber());
            authorRepository.save(author);
        }
        return "redirect:/authors";
    }

    /**
     * POST /authors/delete/{id}
     * Author ko ID se dhundh ke delete karta hai
     * Delete hone ke baad /authors page pe redirect ho jaata hai
     * Sirf ADMIN use kar sakta hai (Security Config mein set hai)
     */
    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }
}