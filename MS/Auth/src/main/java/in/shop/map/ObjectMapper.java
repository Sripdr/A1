package in.shop.map;

import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;
import in.shop.entity.AuthUser;
import org.mapstruct.Mapper;

@Mapper
public interface ObjectMapper {

    AuthUser maptoAuthUser(AuthReq authReq);
    AuthRes maptoAuthRes(AuthUser authUser);
}
