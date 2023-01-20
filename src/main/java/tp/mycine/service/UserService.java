package tp.mycine.service;

import tp.mycine.model.User;
import tp.mycine.service.dto.UserCreateDTO;
import tp.mycine.service.dto.UserDTO;

import java.util.Optional;

public interface UserService {

    /**
     * Retrieves a user by his id
     * @param id User id
     * @return the found user
     */
    UserDTO findById(Long id);

    /**
     * Retrieves a user by his email
     * @param email User email
     * @return the found user
     */
    UserDTO findByEmail(String email);

    /**
     * Create a new user
     * @param userCreateDTO Account creation model
     * @return User created
     */
    UserDTO createUser(UserCreateDTO userCreateDTO);

    /**
     * Delete a user by his id
     * @param id User id
     */
    void deleteByUserId(Long id);

    /**
     * Check if the user exist by his email
     * @param email User email
     * @return True if the user exists and false otherwise
     */
    boolean checkIfUserExist(String email);

    /**
     * Retrieves the connected user
     * @return The connected user
     */
    Optional<User> getConnectedUser();

}
