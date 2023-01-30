package kg.peaksoft.peaksoftlmsbb4.db.mapper.task;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.ResourceRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.ResourceResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.TaskRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TaskResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Resource;
import kg.peaksoft.peaksoftlmsbb4.db.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TaskMapper implements Converter<Task, TaskRequest, TaskResponse> {

    @Override
    public Task convert(TaskRequest taskRequest) {
        Task task = new Task();
        task.setName(taskRequest.getName());
        List<Resource> resources = new ArrayList<>();
        for (ResourceRequest r : taskRequest.getResources()) {
            resources.add(new Resource(r.getResourceType(), r.getValue(),r.getName()));
        }
        task.setResources(resources);
        return task;
    }

    @Override
    public TaskResponse deConvert(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setName(task.getName());
        List<ResourceResponse> resourceResponses = new ArrayList<>();
        for (Resource r : task.getResources()) {
            resourceResponses.add(new ResourceResponse(r.getId(), r.getResourceType(), r.getValue(), r.getName()));
        }
        taskResponse.setResources(resourceResponses);
        return taskResponse;
    }

    public List<TaskResponse> deConvert(List<Task> tasks) {
        List<TaskResponse> responses = new ArrayList<>();
        for (Task task : tasks) {
            responses.add(deConvert(task));
        }
        return responses;
    }
}
