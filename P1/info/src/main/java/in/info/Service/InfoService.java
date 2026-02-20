package in.info.Service;

import in.info.dto.InfRequest;
import in.info.entity.Info;
import in.info.map.Mapper;
import in.info.repo.InfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InfoService {
    private final InfoRepo infoRepo;

    public InfRequest saveInfo(InfRequest request){
        Info info = Mapper.mapToInfObj(request);
        Info save = infoRepo.save(info);
        return Mapper.mapDto(save);
    }

    public InfRequest getById(Long id){
        Info info = infoRepo.findById(id).orElseThrow(() -> new RuntimeException("the requested id info is not available" + id));
        return Mapper.mapDto(info);
    }

}
