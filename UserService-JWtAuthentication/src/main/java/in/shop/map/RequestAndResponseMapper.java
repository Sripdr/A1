package in.shop.map;

import in.shop.dto.user.UserRequest;
import in.shop.dto.user.InfoRequest;
import in.shop.dto.user.InfoResponse;
import in.shop.entity.Users;
import in.shop.entity.UserInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestAndResponseMapper {

    public Users toAuthUser(UserRequest userRequest);

    public UserInfo toUserInfo(InfoRequest infoRequest);

    public InfoResponse toInfoResponse(UserInfo userInfo);
}
