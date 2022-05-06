package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.task.TaskRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.task.TaskResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.ResourceType;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.task.TaskMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Lesson;
import kg.peaksoft.peaksoftlmsbb4.db.model.Resource;
import kg.peaksoft.peaksoftlmsbb4.db.model.Task;
import kg.peaksoft.peaksoftlmsbb4.db.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TaskRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.TaskService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final LessonRepository lessonRepository;
    private final AWSS3Service awss3Service;

    @Override
    public TaskResponse saveTasks(TaskRequest taskRequest) {
        Lesson lessons = lessonRepository.findById(taskRequest.getLessonId()).orElseThrow(() -> new NotFoundException(
                String.format("Lesson with id %s not found", taskRequest.getLessonId())

        ));
        Task task = taskMapper.convert(taskRequest);
        Task save = taskRepository.save(task);
        lessons.setTask(save);
        log.info("successfully save task:{}", task);
        return taskMapper.deConvert(save);
    }

    private Task findById(Long id) {
        log.info("successfully find task by id:{}", id);
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found id=%s", id)));
    }

    @Override
    public List<TaskResponse> findAll() {
        log.info("successfully find all tasks");
        return taskRepository.findAll().stream().map(taskMapper::deConvert).collect(Collectors.toList());
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
    public String delete(Long id) {
        boolean exits = taskRepository.existsById(id);
        if (!exits) {
            log.error("not found task with  id:{}", id);
            throw new NotFoundException(String.format("Task is not found id=%s", id));

        }
//        for (Resource r : taskRepository.getById(id).getResources()) {
//            if (r.getResourceType() == ResourceType.FILE || r.getResourceType() == ResourceType.IMAGE) {
//                awss3Service.deleteFile(r.getValue());
//                awss3Service.deleteFile(r.getValue());
//            }
//        }
        taskRepository.deleteById(id);
        log.info("successfully delete task by id :{}", id);
        return "Task deleted";
    }

    @Override
    public TaskResponse findTaskByLessonId(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format("Lesson with id = %s not found", id)));
        return taskMapper.deConvert(lesson.getTask());
    }
}
