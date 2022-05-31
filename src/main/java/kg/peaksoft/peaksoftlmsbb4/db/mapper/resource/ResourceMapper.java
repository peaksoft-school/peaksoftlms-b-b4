package kg.peaksoft.peaksoftlmsbb4.db.mapper.resource;

import kg.peaksoft.peaksoftlmsbb4.db.dto.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.resource.ResourceRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.resource.ResourceResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Resource;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper implements Converter<Resource, ResourceRequest, ResourceResponse> {
    @Override
    public Resource convert(ResourceRequest resourceRequest) {
        Resource resource = new Resource();
        resource.setValue(resourceRequest.getValue());
        resource.setResourceType(resourceRequest.getResourceType());
        return resource;
    }

    @Override
    public ResourceResponse deConvert(Resource resource) {
        return null;
    }
}
