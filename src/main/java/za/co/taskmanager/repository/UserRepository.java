package za.co.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.taskmanager.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findById(long id);

    @Override
    Iterable<User> findAll();
}
