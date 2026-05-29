package library.management.books.Controller;

import library.management.books.Dto.RegisterRequestDto;
import library.management.books.Entity.UserEntity;
import library.management.books.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import library.management.books.Entity.Role;

@Controller
@RequestMapping("/auth")
public class AuthViewController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
     * Register Form — Thymeleaf UI ke liye
     * register.html se POST request aati hai
     *
     * Flow:
     * 1. User register.html form bharta hai
     * 2. Name, Email, Password, Role submit karta hai
     * 3. Password encode hota hai BCrypt se
     * 4. User database mein save hota hai
     * 5. Login page pe redirect ho jaata hai
     */
    @PostMapping("/register-page")
    public String register(@ModelAttribute RegisterRequestDto dto) {

        var user = new UserEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.valueOf(dto.getRole().toString()));

        userRepo.save(user);

        return "redirect:/login";
    }
}
