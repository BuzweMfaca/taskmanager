package za.co.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.taskmanager.model.UserTask;


@Repository
public interface UserTaskRepository extends CrudRepository<UserTask, Long> {

    UserTask findByIdAndUserId(Long id, Long userId);

    Iterable<UserTask> findAllByUserId(Long userId);
}
