package uz.pdp.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2.entity.Task;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.payload.TaskDto;
import uz.pdp.task2.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Task> tasks = taskService.getAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        Task task = taskService.getById(id);
        return ResponseEntity.status(task!= null?200:409).body(task);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody TaskDto taskDto){
        ApiResponse apiResponse = taskService.add(taskDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody TaskDto taskDto){
        ApiResponse apiResponse = taskService.edit(id, taskDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id){
        ApiResponse apiResponse = taskService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }
}
