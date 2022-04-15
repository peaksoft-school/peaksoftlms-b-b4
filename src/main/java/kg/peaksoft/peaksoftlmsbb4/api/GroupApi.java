package kg.peaksoft.peaksoftlmsbb4.api;

import kg.peaksoft.peaksoftlmsbb4.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsbb4.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/group")
public class GroupApi {
    private final GroupService groupService;

    @PostMapping("/save")
    @CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
    private GroupResponse saveGroup(@RequestBody GroupRequest groupRequest) {
        return groupService.saveGroup(groupRequest);
    }

    @PermitAll
    @GetMapping
    @CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
    private List<GroupResponse> findAllGroup() {
        return groupService.findAllGroup();
    }


    @GetMapping("/find/by/{id}")
    @CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
    private GroupResponse findById(@PathVariable Long id) {
        return groupService.findById(id);
    }


    @DeleteMapping("/delete/by/{id}")
    @CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
    private void deleteById(@PathVariable Long id) {
        groupService.deleteById(id);
    }


    @PatchMapping("update/{id}")
    @CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
    private GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return groupService.update(id, groupRequest);
    }
}
