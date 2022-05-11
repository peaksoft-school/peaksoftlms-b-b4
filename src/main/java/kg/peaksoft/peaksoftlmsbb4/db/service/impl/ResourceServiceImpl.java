package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.repository.ResourceRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;

    @Override
    public String delete(Long id) {
        if (resourceRepository.existsById(id)) {
            resourceRepository.deleteById(id);
        }
        return "resource deleted";
    }
}
