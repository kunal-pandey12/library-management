package library.management.books.Controller;

import library.management.books.Repo.IssueRepo;
import library.management.books.Service.IssueService;
import library.management.books.Dto.IssueRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * IssueViewController — Thymeleaf Pages ke liye Controller
 *
 * Ye controller sirf HTML pages handle karta hai (Thymeleaf).
 * REST API ke liye alag IssueController.java hai.
 *
 * Endpoints:
 *   GET  /issue-view       → Issue list page dikhata hai
 *   POST /issue-view/issue → Book issue karta hai
 *   POST /issue-view/return/{id} → Book return karta hai
 */
@Controller
@RequestMapping("/issue-view")
public class IssueViewController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private IssueRepo issueRepo;

    /*
     * GET /issue-view
     * Sabhi issues fetch karke issue-books.html pe bhejta hai
     * User aur Admin dono dekh sakte hain
     */
    @GetMapping
    public String getIssuePage(Model model) {
        model.addAttribute("issues", issueService.getAllIssues());
        return "issue-books";
    }

    /*
     * POST /issue-view/issue
     * Form se User ID aur Book ID aata hai
     * Book issue hoti hai — copies kam hoti hain
     * Page refresh hota hai
     * User aur Admin dono issue kar sakte hain
     */
    @PostMapping("/issue")
    public String issueBook(@RequestParam Long userId,
                            @RequestParam Long bookId,
                            Model model) {
        try {
            IssueRequestDto dto = new IssueRequestDto();
            dto.setUserId(userId);
            dto.setBookId(bookId);
            issueService.issueBook(dto);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("issues", issueService.getAllIssues());
            return "issue-books";
        }
        return "redirect:/issue-view";
    }

    /*
     * POST /issue-view/return/{id}
     * Issue ID se book return hoti hai
     * Return date set hoti hai — copies badh jaati hain
     * Late fine bhi calculate hota hai — ₹10/day late fee
     * Fine message RedirectAttributes se page pe dikhaya jaata hai
     * User aur Admin dono return kar sakte hain
     */
    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable Long id,
                             RedirectAttributes redirectAttributes) {
        String message = issueService.returnBook(id);
        redirectAttributes.addFlashAttribute("fine", message);
        return "redirect:/issue-view";
    }
    }
