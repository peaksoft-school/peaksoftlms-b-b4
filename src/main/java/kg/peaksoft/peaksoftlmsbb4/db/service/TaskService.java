package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.TaskRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TaskResponse;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    TaskResponse saveTasks(TaskRequest taskRequest);

    TaskResponse update(Long id, TaskRequest taskRequest);

    TaskResponse delete(Long id);

    TaskResponse findTaskByLessonId(Long id);

    TaskResponse getById(Long id);
}
