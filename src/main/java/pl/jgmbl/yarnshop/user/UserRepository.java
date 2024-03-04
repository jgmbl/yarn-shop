package pl.jgmbl.yarnshop.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
