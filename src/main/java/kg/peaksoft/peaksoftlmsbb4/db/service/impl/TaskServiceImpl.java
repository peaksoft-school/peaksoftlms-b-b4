package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.task.TaskRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.task.TaskResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.TaskService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.task.TaskMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Lesson;
import kg.peaksoft.peaksoftlmsbb4.db.model.Task;
import kg.peaksoft.peaksoftlmsbb4.db.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TaskRepository;
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

    @Override
    public Task findById(Long id) {
        log.info("successfully find by id:{}", id);
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
            throw new NotFoundException(String.format("Task is not found id=%s", id));
        }
        Task task = findById(id);
        if (!task.getName().equals(taskRequest.getName())) {
            task.setName(taskRequest.getName());
        }
        if (!task.getImage().equals(taskRequest.getImage())) {
            task.setImage(taskRequest.getImage());
        }
        if (!task.getCode().equals(taskRequest.getCode())) {
            task.setCode(taskRequest.getCode());
        }
        if (!task.getFile().equals(taskRequest.getFile())) {
            task.setFile(taskRequest.getFile());
        }
        if (!task.getLink().equals(taskRequest.getLink())) {
            task.setLink(taskRequest.getLink());
        }
        if (!task.getText().equals(taskRequest.getText())) {
            task.setText(taskRequest.getText());
        }
        log.info("successfully update id:{}", id);
        return taskMapper.deConvert(task);
    }

    @Override
    public void delete(Long id) {
        boolean exits = taskRepository.existsById(id);
        if (!exits) {
            throw new NotFoundException(String.format("Task is not found id=%s", id));

        }
        log.info("successfully delet by id :{}", id);
        taskRepository.deleteById(id);
    }
}
