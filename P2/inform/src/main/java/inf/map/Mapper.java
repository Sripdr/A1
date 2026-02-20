package inf.map;


import inf.dto.InfRequest;
import inf.entity.Info;
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
