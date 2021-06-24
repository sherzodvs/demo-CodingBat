package uz.pdp.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2.entity.Answer;
import uz.pdp.task2.payload.AnswerDto;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.service.AnswerService;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Answer> answers = answerService.getAll();
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        Answer answer = answerService.getById(id);
        return ResponseEntity.status(answer!=null?200:409).body(answer);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.add(answerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.edit(id, answerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = answerService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }
}
