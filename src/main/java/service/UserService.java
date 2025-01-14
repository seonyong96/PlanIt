package service;

import domain.User;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user){
        duplicateCheck(user);
        userRepository.save(user);

    }

    private void duplicateCheck(User user){
        userRepository.findByName(user.getId()).ifPresent( m -> {
            throw new IllegalStateException("중복된 ID 입니다.");
        });
    }




}
