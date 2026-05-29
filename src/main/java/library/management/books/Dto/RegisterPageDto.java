package library.management.books.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPageDto {
    private String name;
    private String email;
    private String password;
    private String role;
}
