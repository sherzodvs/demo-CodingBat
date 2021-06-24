package uz.pdp.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.Answer;
import uz.pdp.task2.entity.Task;
import uz.pdp.task2.entity.User;
import uz.pdp.task2.payload.AnswerDto;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.repository.AnswerRepository;
import uz.pdp.task2.repository.TaskRepository;
import uz.pdp.task2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    public Answer getById(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent()) {
            return optionalAnswer.get();
        }
        return null;
    }

    public ApiResponse add(AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setName(answerDto.getName());
        answer.setDescription(answerDto.getDescription());
        answer.setCorrect(answerDto.isCorrect());
        User user = userRepository.getById(answerDto.getUserId());
        answer.setUser(user);

        Task task = taskRepository.getById(answerDto.getTaskId());
        answer.setTask(task);
        answerRepository.save(answer);
        return new ApiResponse("Ok",true);
    }

    public ApiResponse edit(Integer id, AnswerDto answerDto) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent()){
            Answer answer = optionalAnswer.get();
            answer.setName(answerDto.getName());
            answer.setDescription(answerDto.getDescription());
            answer.setCorrect(answerDto.isCorrect());
            User user = userRepository.getById(answerDto.getUserId());
            answer.setUser(user);

            Task task = taskRepository.getById(answerDto.getTaskId());
            answer.setTask(task);
            answerRepository.save(answer);
            return new ApiResponse("Ok",true);
        }
        return new ApiResponse("Id topilmadi!!!",false);
    }

    public ApiResponse delete(Integer id) {
        try {
            answerRepository.deleteById(id);
            return new ApiResponse("Ok",true);
        }catch (Exception e){
            return new ApiResponse("Id topilmadi!!!",false);
        }
    }
}
