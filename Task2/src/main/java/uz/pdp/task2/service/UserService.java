package uz.pdp.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.User;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    public ApiResponse add(User user) {
        boolean existsByEmail = userRepository.existsByEmail(user.getEmail());
        if (!existsByEmail) {
            User userNew = new User();
            userNew.setEmail(user.getEmail());
            userNew.setPassword(user.getPassword());
            userRepository.save(userNew);
            return new ApiResponse("OK!", true);
        }
        return new ApiResponse("Bunday email bazada bor", false);
    }

    public ApiResponse edit(Integer id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            boolean existsByEmailAndIdNot = userRepository.existsByEmailAndIdNot(user.getEmail(), id);
           if (!existsByEmailAndIdNot){
               User userNew = optionalUser.get();
               userNew.setEmail(user.getEmail());
               userNew.setPassword(user.getPassword());
               userRepository.save(userNew);
               return new ApiResponse("OK!",true);
           }
            return new ApiResponse("Bunday email tizimda bor",false);
        }
        return new ApiResponse("User topilmadi",false);
    }

    public ApiResponse delete(Integer id) {
        try {

            userRepository.deleteById(id);
            return new ApiResponse("OK!",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);

        }

    }
}
