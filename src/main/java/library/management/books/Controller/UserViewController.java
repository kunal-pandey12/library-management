package library.management.books.Controller;

import library.management.books.Entity.UserEntity;
import library.management.books.Entity.Role;
import library.management.books.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserViewController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
     * GET /users
     * Sabhi users fetch karke users.html pe bhejta hai
     * Sirf ADMIN dekh sakta hai
     */
    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "users";
    }

    /*
     * GET /users/search?name=xyz
     * Name se user search karta hai (case insensitive)
     * Result users.html pe bhejta hai
     */
    @GetMapping("/search")
    public String searchByName(@RequestParam String name, Model model) {
        model.addAttribute("users", userRepo.findByNameContainingIgnoreCase(name));
        return "users";
    }

    /*
     * POST /users/update/{id}
     * Edit modal se updated data aata hai
     * Name, Email, Role update karke save karta hai
     * Sirf ADMIN use kar sakta hai
     */
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String role) {
        UserEntity user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setRole(Role.valueOf(role));
            userRepo.save(user);
        }
        return "redirect:/users";
    }

    /*
     * POST /users/delete/{id}
     * User ko ID se dhundh ke delete karta hai
     * Sirf ADMIN use kar sakta hai
     */
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return "redirect:/users";
    }
}