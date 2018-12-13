package za.co.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.taskmanager.exception.ResourceNotFoundException;
import za.co.taskmanager.model.User;
import za.co.taskmanager.model.UserTask;
import za.co.taskmanager.repository.UserTaskRepository;
import za.co.taskmanager.repository.UserRepository;


@Service
public class UserTaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTaskRepository userTaskRepository;

    public UserTask saveUserTask(long userId, UserTask userTask){
        try {
            User user = userRepository.findById(userId);
            userTask.setUser(user);
            return userTaskRepository.save(userTask);
        }catch (Exception e){
            throw new ResourceNotFoundException("User ID '"+userId+"' not found");
        }
    }

    public UserTask updateUserTask(long userId, long userTaskId, UserTask userTaskRequest){
        UserTask userTask = findByUserIdAndUserTaskId(userId, userTaskId);
        userTask.setName(userTaskRequest.getName() !=null ? userTaskRequest.getName() : userTask.getName());
        userTask.setDescription(userTaskRequest.getDescription() != null ? userTaskRequest.getDescription() : userTask.getDescription());
        userTask.setStatus(userTaskRequest.getStatus() != null ? userTaskRequest.getStatus() : userTask.getStatus());
        userTask.setDate_time(userTaskRequest.getDate_time() != null ? userTaskRequest.getDate_time() : userTask.getDate_time());
        return userTaskRepository.save(userTask);
    }

    public UserTask findByUserIdAndUserTaskId(long userId, long userTaskId){

        UserTask userTask = userTaskRepository.findByIdAndUserId(userTaskId, userId);

        if(userTask == null){
            throw new ResourceNotFoundException("Task with Id '"+userTaskId+"' and userId '"+userId+"' not found");
        }

        return userTask;
    }

    public void deleteUserTaskByUserIdAndUserTaskId(long userId, long userTaskId){
        UserTask userTask = findByUserIdAndUserTaskId(userId, userTaskId);
        userTaskRepository.delete(userTask);
    }

    public Iterable<UserTask>findAllUserTaskByUserId(Long userId){
        return userTaskRepository.findAllByUserId(userId);
    }

    public Iterable<UserTask>findAllUserTasks(){
        return userTaskRepository.findAll();
    }

    public void bulkInsertOrUpdateTasks(Iterable<UserTask> tasks) {
        userTaskRepository.saveAll(tasks);
    }
}
