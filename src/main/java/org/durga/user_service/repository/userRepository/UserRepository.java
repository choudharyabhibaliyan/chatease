package org.durga.user_service.repository.userRepository;

import org.durga.user_service.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {
    User findByUsername(String username);
}
