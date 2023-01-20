package tp.mycine.service.mapper;

import tp.mycine.model.User;
import tp.mycine.service.dto.UserCreateDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserCreateMapper extends EntityMapper<UserCreateDTO, User> {
}
