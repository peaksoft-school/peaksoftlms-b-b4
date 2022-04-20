package kg.peaksoft.peaksoftlmsbb4.mapper.task;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.task.TaskRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.task.TaskResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements Converter<Task, TaskRequest, TaskResponse> {
    @Override
    public Task convert(TaskRequest taskRequest) {
        Task task=new Task();
        task.setName(taskRequest.getName());
        task.setCode(taskRequest.getCode());
        task.setImage(taskRequest.getImage());
        task.setFile(taskRequest.getFile());
        task.setLink(taskRequest.getLink());
        task.setText(taskRequest.getText());
        return task;
    }

    @Override
    public TaskResponse deConvert(Task task) {
        TaskResponse taskResponse=new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setImage(task.getImage());
        taskResponse.setText(task.getText());
        taskResponse.setCode(task.getCode());
        taskResponse.setFile(task.getFile());
        taskResponse.setName(task.getName());
        taskResponse.setLink(task.getLink());
        return taskResponse;
    }
}
