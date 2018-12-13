package za.co.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.taskmanager.exception.ResourceNotFoundException;
import za.co.taskmanager.model.User;
import za.co.taskmanager.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){ return userRepository.save(user);}

    public User UpdateUser(Long userId, User userRequest) {

        User user = findUserById(userId);
        user.setUsername(userRequest.getUsername()!=null? userRequest.getUsername() : user.getUsername());
        user.setFirst_name(userRequest.getFirst_name()!=null? userRequest.getFirst_name() : user.getFirst_name());
        user.setLast_name(userRequest.getLast_name()!=null? userRequest.getLast_name() : user.getLast_name());

        return saveUser(user);
    }

    public User findUserById(Long id){

        User user = userRepository.findById(id).get();

        if(user == null){
            throw new ResourceNotFoundException("User ID '"+id+"' not found");
        }


        return user;
    }

    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }


    public void deleteUserById(Long id){
        User user = userRepository.findById(id).get();

        if(user == null){
            throw new ResourceNotFoundException("User ID '"+id+"' not found");
        }

        userRepository.delete(user);
    }

}
