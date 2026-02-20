package in.info.cont;

import in.info.Service.InfoService;
import in.info.dto.InfRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/in")
@RequiredArgsConstructor
public class InController {
    private  final InfoService infoService;

    @GetMapping("/wel")
    public String wel(String msg){

        msg="Hi Welcome to Info";
        return msg.toUpperCase();
    }

    @PostMapping("/save")
    public ResponseEntity<InfRequest> save(@RequestBody InfRequest request){
        InfRequest saveInfo = infoService.saveInfo(request);
        return new ResponseEntity<>(saveInfo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfRequest> getById(@PathVariable Long id){
        InfRequest byId = infoService.getById(id);
        return ResponseEntity.ok(byId);
    }
}
