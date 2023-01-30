package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.resource.ResourceResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.resource.ResourceMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Resource;
import kg.peaksoft.peaksoftlmsbb4.db.repository.ResourceRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResourceService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    @Override
    public ResourceResponse delete(Long id) {
        if (resourceRepository.existsById(id)) {
            Resource resource = resourceRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Lesson with id %s not found", id)));
            resourceRepository.deleteById(id);

            return resourceMapper.deConvert(resource);
        }
        return null;
    }
}
