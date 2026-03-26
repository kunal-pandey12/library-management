package library.management.books.Controller;

import library.management.books.Dto.UserDto;
import library.management.books.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public List<UserDto> createUser(@RequestBody List<UserDto> userDto){
        return userService.createUser(userDto);
    }

}
