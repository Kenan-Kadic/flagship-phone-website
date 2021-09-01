package org.launchcode.flagshipphonewebsite.models.data;

import org.launchcode.flagshipphonewebsite.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}

