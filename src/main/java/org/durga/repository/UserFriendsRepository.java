package org.durga.repository;

import org.durga.entites.UserFriends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserFriendsRepository extends JpaRepository<UserFriends , Integer> {
    @Query("select uf from UserFriends uf where uf.user.id=?1 and uf.friend.id=?2 and uf.status=?3")
    List<UserFriends> getDeleteRequest(int id, int requestUserId, String status);

    @Modifying
    @Transactional
    @Query("update from UserFriends uf set uf.status=?3 where uf.friend.id=?1 and uf.user.id=?2")
    void acceptRequest(int id, int requestUserId ,String status);
}
