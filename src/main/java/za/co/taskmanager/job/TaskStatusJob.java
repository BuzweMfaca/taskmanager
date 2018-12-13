package za.co.taskmanager.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import za.co.taskmanager.model.UserTask;
import za.co.taskmanager.service.UserTaskService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TaskStatusJob {

    @Autowired
    UserTaskService userTaskService;

    @Scheduled(fixedRate = 5000)
    public void checkExpiredTasks(){

        List<UserTask> tasks = new ArrayList<>();

        for(UserTask task: userTaskService.findAllUserTasks()){
            System.out.println("######## LIST OF EXPIRED TASK ########");
            if(task.getStatus() != null && task.getDate_time() != null) {
                if ((task.getStatus().equals("pending")) && (System.currentTimeMillis() > task.getDate_time().getTime())) {
                    System.out.println(task.toString());
                    task.setStatus("done");
                    tasks.add(task);
                    System.out.println(task.toString());
                }
            }
        }

        if(!tasks.isEmpty()){
            userTaskService.bulkInsertOrUpdateTasks(tasks);
        }

    }
}
