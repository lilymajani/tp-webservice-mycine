package tp.mycine.service.mapper;

import tp.mycine.model.User;
import tp.mycine.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends EntityMapper<UserDTO, User>{
}
