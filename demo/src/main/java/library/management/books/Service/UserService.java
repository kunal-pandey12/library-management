package library.management.books.Service;

import library.management.books.Dto.UserDto;
import library.management.books.Entity.Role;
import library.management.books.Entity.UserEntity;
import library.management.books.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<UserDto> createUser(List<UserDto> dtoList){
        return dtoList.stream().map(dto->{

            UserEntity user = new UserEntity();
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());

            user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
            user.setPassword(dto.getPassword());

            UserEntity saved = userRepo.save(user);

            UserDto response=new UserDto();
            response.setId(saved.getId());
            response.setName(saved.getName());
            response.setEmail(saved.getEmail());
            response.setRole(saved.getRole().name());
            response.setPassword(saved.getPassword());

            return response;
        }).toList();
    }
}
