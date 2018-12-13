package za.co.taskmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.taskmanager.model.UserTask;
import za.co.taskmanager.service.UserTaskService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user/")
public class UserTaskController {

    @Autowired
    private UserTaskService userTaskService;


    @PostMapping("{user_id}/task")
    public ResponseEntity<?> createNewTask(@PathVariable(value = "user_id") Long user_id, @Valid @RequestBody UserTask userTaskRequest) {
        UserTask userTask = userTaskService.saveUserTask(user_id, userTaskRequest);
        return new ResponseEntity<UserTask>(userTask, HttpStatus.CREATED);
    }

    @PutMapping("{user_id}/task/{task_id}")
    public ResponseEntity<?> updateTask(@PathVariable(value = "user_id") Long user_id,@PathVariable(value = "task_id") Long task_id, @Valid @RequestBody UserTask userTaskRequest) {
        UserTask userTask = userTaskService.updateUserTask( user_id, task_id, userTaskRequest);
        return new ResponseEntity<UserTask>(userTask, HttpStatus.CREATED);
    }

    @DeleteMapping("{user_id}/task/{task_id}")
    public ResponseEntity<?> deleteTask(@PathVariable(value = "user_id") Long user_id,@PathVariable(value = "task_id") Long task_id) {
        userTaskService.deleteUserTaskByUserIdAndUserTaskId(user_id, task_id);
        return new ResponseEntity<String>("Task was deleted successfully", HttpStatus.OK);
    }

    @GetMapping("{user_id}/task/{task_id}")
    public ResponseEntity<?> getTask(@PathVariable(value = "user_id") Long user_id,@PathVariable(value = "task_id") Long task_id){
        UserTask userTask = userTaskService.findByUserIdAndUserTaskId(user_id, task_id);
        return new ResponseEntity<UserTask>( userTask, HttpStatus.OK);
    }

    @GetMapping("{user_id}/task")
    public Iterable<UserTask> getAllUserTaskByUserId(@PathVariable(value = "user_id") Long user_id){
        return userTaskService.findAllUserTaskByUserId(user_id);
    }

}