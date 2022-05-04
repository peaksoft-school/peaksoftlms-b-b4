package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.task.TaskRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.task.TaskResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {
    TaskResponse saveTasks(TaskRequest taskRequest);

    Task findById(Long id);

    List<TaskResponse> findAll();

    TaskResponse update(Long id, TaskRequest taskRequest);

    void delete(Long id);

    TaskResponse findTaskByLessonId(Long id);

}
