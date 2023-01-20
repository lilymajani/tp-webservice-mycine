package tp.mycine.service.dto;

import tp.mycine.model.Role;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDTO implements Serializable {

    private Long id;
    private String name;
    private String firstname;
    private String email;
    private String username;
    private String password;
    private Set<Role> roles;
}
