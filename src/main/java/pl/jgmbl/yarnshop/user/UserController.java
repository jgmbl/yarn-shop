package pl.jgmbl.yarnshop.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    HttpSession httpSession;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> allUsers = userService.getAllUsers();

        return ResponseEntity.ok(allUsers);
    }

    @DeleteMapping("users")
    public ResponseEntity<Void> deleteUser() {

        boolean isUserDeleted = userService.deleteUser();

        if (!isUserDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}
