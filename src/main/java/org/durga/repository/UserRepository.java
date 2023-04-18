package org.durga.repository;

import org.durga.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {
    User findByUsername(String username);

@Query(value = "SELECT u.* FROM user u LEFT JOIN user_friends f1 ON u.id = f1.user_id AND f1.friend_id = ?1  and f1.status = 'NONE' \n" +
        "LEFT JOIN user_friends f2 ON u.id = f2.friend_id AND f2.user_id = ?1 AND f2.status = 'NONE'  \n" +
        "WHERE f1.user_id IS NULL AND f2.friend_id IS NULL and u.id <> ?1\n" , nativeQuery = true)
    List<User> getAllUnknownUsers(int id);



    @Query(value="SELECT u FROM User u JOIN UserFriends f1 ON u.id = f1.friend.id AND f1.user.id = ?1 and f1.status='NONE'")
    List<User> getAllSendRequestsByUser(int id);

    @Query("SELECT u FROM User u JOIN UserFriends f1 ON u.id = f1.user.id AND f1.friend.id = ?1 and f1.status='NONE'")
    List<User> getAllRequestsByUser(int id);

    @Query("SELECT u \n" +
            "FROM User u \n" +
            "LEFT JOIN UserFriends f1 ON u.id = f1.friend.id AND f1.user.id = ?1 AND f1.status='ACTIVE' \n" +
            "LEFT JOIN UserFriends f2 ON u.id = f2.user.id AND f2.friend.id = ?1 AND f2.status='ACTIVE'\n" +
            "WHERE f1.user.id = ?1 OR f2.friend.id = ?1")
    List<User> getAllFriends(int id);

}
