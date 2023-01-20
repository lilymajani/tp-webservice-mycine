package tp.mycine.service.impl;

import tp.mycine.model.Role;
import tp.mycine.model.User;
import tp.mycine.repository.RoleRepository;
import tp.mycine.repository.UserRepository;
import tp.mycine.service.UserService;
import tp.mycine.service.dto.UserCreateDTO;
import tp.mycine.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tp.mycine.service.mapper.UserCreateMapper;
import tp.mycine.service.mapper.UserMapper;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserCreateMapper userCreateMapper;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    /**
     * Retrieves a user by his id
     * @param id User id
     * @return the found user
     */
    @Override
    public UserDTO findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map((user) -> userMapper.toDto(user)).orElseGet(null);
    }

    /**
     * Retrieves a user by his email
     * @param email User email
     * @return the found user
     */
    @Override
    public UserDTO findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map((user) -> userMapper.toDto(user)).orElseGet(null);
    }

    /**
     * Create a new user
     * @param userCreateDTO Account creation model
     * @return User created
     */
    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        if (!checkIfUserExist(userCreateDTO.getEmail())) {
            User user = userCreateMapper.toEntity(userCreateDTO);
            Optional<Role> roleOptional = roleRepository.findByName(userCreateDTO.getRole());
            if (roleOptional.isPresent()) user.setRoles(Collections.singleton(roleOptional.get()));
            encodePassword(user);
            User userSaved = userRepository.save(user);
            return userMapper.toDto(userSaved);
        }
        return null;
    }

    /**
     * Delete a user by his id
     * @param id User id
     */
    @Override
    public void deleteByUserId(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        }
    }

    /**
     * Check if the user exist by his email
     * @param email User email
     * @return True if the user exists and false otherwise
     */
    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Retrieves the connected user
     * @return The connected user
     */
    @Override
    public Optional<User> getConnectedUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Principal::getName)
                .map(userRepository::findByEmail)
                .orElseGet(Optional::empty);
    }

    private void encodePassword( User user){
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
    }
}
