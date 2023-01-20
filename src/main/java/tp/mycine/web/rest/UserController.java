package tp.mycine.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tp.mycine.security.jwt.JwtUtils;
import tp.mycine.service.UserService;
import tp.mycine.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    private final UserService userService;

    /**
     * Retrieving a user by his id
     * @param userId User id
     * @return Return the user
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getuserById(@PathVariable Long userId) {
        log.debug("request to retrieve a user whose id is {}", userId);
        return ResponseEntity.ok(userService.findById(userId));
    }

    /**
     * Delete a user by his id
     * @param userId User id
     * @return Empty response
     */
    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteuser(
            @PathVariable(value = "userId", required = true) final Long userId
    ) {
        log.debug("request to delete a user whose id is {}", userId);
        userService.deleteByUserId(userId);
        return ResponseEntity.ok().build();
    }

}
