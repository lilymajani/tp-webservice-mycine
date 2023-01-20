package tp.mycine.web.rest;

import tp.mycine.repository.UserRepository;
import tp.mycine.security.jwt.JwtUtils;
import tp.mycine.service.UserService;
import tp.mycine.service.dto.AuthDTO;
import tp.mycine.service.dto.UserCreateDTO;
import tp.mycine.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private final UserService userService;

    /**
     * Connect to the application
     * @param auth Model containing the connection information
     * @return Token and current user
     */
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody AuthDTO auth) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("token", jwt);

        UserDTO userDTO = userService.findByEmail(authentication.getName());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(userDTO);
    }

    /**
     * Create a user account
     * @param userCreateDTO Account creation model
     * @return User created
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserDTO userDTO = userService.createUser(userCreateDTO);
        if (userDTO != null) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userCreateDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("token", jwt);

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(userDTO);
        }
        return ResponseEntity.ok(userService.createUser(userCreateDTO));
    }
}
