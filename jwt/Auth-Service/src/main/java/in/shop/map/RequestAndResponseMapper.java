package in.shop.map;

import in.shop.dto.user.AuthRequest;
import in.shop.dto.user.InfoRequest;
import in.shop.dto.user.InfoResponse;
import in.shop.entity.AuthUser;
import in.shop.entity.UserInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestAndResponseMapper {

    public AuthUser toAuthUser(AuthRequest authRequest);

    public UserInfo toUserInfo(InfoRequest infoRequest);

    public InfoResponse toInfoResponse(UserInfo userInfo);
}
