package uz.pdp.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.Category;
import uz.pdp.task2.entity.Task;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.payload.TaskDto;
import uz.pdp.task2.repository.CategoryRepository;
import uz.pdp.task2.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task getById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()){
            return optionalTask.get();

        }
    return null;
    }

    public ApiResponse add(TaskDto taskDto) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            task.setCategory(category);
        }

        taskRepository.save(task);
        return new ApiResponse("OK!",true);
    }

    public ApiResponse edit(Integer id, TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()){
            Task task = optionalTask.get();
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
            if (optionalCategory.isPresent()){
                Category category = optionalCategory.get();
                task.setCategory(category);
            }
            taskRepository.save(task);
            return new ApiResponse("OK!",true);
        }
        return new ApiResponse("Id topilmadi!!!",false);
    }

    public ApiResponse delete(Integer id) {
        try {
            taskRepository.deleteById(id);
            return new ApiResponse("OK",true);
        }catch (Exception e){
            return new ApiResponse("Id topilmadi!!!",false);
        }

    }
}
