package tp.mycine.service.mapper;

import tp.mycine.model.User;
import tp.mycine.service.dto.UserAuthDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UseAuthMapper extends EntityMapper<UserAuthDTO, User> {
}
