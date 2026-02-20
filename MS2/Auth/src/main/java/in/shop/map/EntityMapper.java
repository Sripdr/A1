package in.shop.map;

import in.shop.dto.AuthRequest;
import in.shop.dto.AuthResponse;
import in.shop.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityMapper {
        EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

        AuthUser authRequestToAuthUser(AuthRequest authRequest);
        AuthResponse authUserToAuthResponse(AuthUser authUser);
}
