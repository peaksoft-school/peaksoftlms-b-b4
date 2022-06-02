package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.task.TaskRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.task.TaskResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.task.TaskMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Lesson;
import kg.peaksoft.peaksoftlmsbb4.db.model.Task;
import kg.peaksoft.peaksoftlmsbb4.db.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.ResourceRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TaskRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.TaskService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final LessonRepository lessonRepository;
    private final ResourceRepository resourceRepository;

    @Override
    public TaskResponse saveTasks(TaskRequest taskRequest) {
        Lesson lessons = lessonRepository.findById(taskRequest.getLessonId()).orElseThrow(() -> new NotFoundException(
                String.format("Lesson with id %s not found", taskRequest.getLessonId())
        ));
        if (lessons.getTest() == null) {
            Task task = taskMapper.convert(taskRequest);
            Task save = taskRepository.save(task);
            lessons.setTask(save);
            log.info("successfully save task:{}", task);
            return taskMapper.deConvert(save);
        } else {
            throw new BadRequestException("in this lesson task already exists");
        }
    }

    private Task findById(Long id) {
        log.info("successfully find task by id:{}", id);
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found id=%s", id)));
    }

    @Override
    public TaskResponse update(Long id, TaskRequest taskRequest) {
        boolean exist = taskRepository.existsById(id);
        if (!exist) {
            log.error("not found task with id:{}", id);
            throw new NotFoundException(String.format("Task is not found id=%s", id));
        }
        Task task = findById(id);
        if (!task.getName().equals(taskRequest.getName())) {
            task.setName(taskRequest.getName());
        }
        log.info("successfully update task with id:{}", id);
        return taskMapper.deConvert(task);
    }


    @Override
    @Transactional
    public TaskResponse delete(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Task is not found id=%s", id)));
        Task resource_not_found_with_id = taskRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("resource not found =%s with id", id)));
        resourceRepository.deleteAll(task.getResources());
        taskRepository.delete(task);
        log.info("successfully delete task by id :{}", id);
        return taskMapper.deConvert(resource_not_found_with_id);
    }

    @Override
    public TaskResponse findTaskByLessonId(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format("Lesson with id = %s not found", id)));
        return taskMapper.deConvert(lesson.getTask());
    }
}
