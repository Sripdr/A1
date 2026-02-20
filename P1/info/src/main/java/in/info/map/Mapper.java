package in.info.map;

import in.info.dto.InfRequest;
import in.info.entity.Info;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public static Info mapToInfObj(InfRequest request){
        Info info = new Info();
        info.setFirstName(request.firstName());
        info.setLastName(request.lastName());
        info.setAddress(request.address());
        return info;
    }

    public static InfRequest mapDto(Info info){
       return new InfRequest(info.getId(), info.getFirstName(), info.getLastName(), info.getAddress());
    }
}
