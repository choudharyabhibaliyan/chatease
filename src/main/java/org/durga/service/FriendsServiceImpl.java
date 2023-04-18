package org.durga.service;

import org.durga.entites.User;
import org.durga.entites.UserFriends;
import org.durga.repository.UserFriendsRepository;
import org.durga.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class FriendsServiceImpl {

    @Autowired private UserRepository userRepository;
    @Autowired private UserFriendsRepository userFriendsRepository;
    public List<User> getAllUnknownUsers(int id){
        return userRepository.getAllUnknownUsers(id);
    }

    public List<User> getAllSendRequestsByUser(int id){
        return userRepository.getAllSendRequestsByUser(id);
    }


    public boolean sendFriendRequest(int userId, int friendId) {
        boolean isFriendRequestSendSuccess = false;
        List<User> users = userRepository.findAllById(Arrays.asList(userId , friendId));
        User usrr = users.stream().filter(usr -> usr.getId() == friendId).findAny().get();
        User user = users.stream().filter(u -> u.getId() == userId).findAny().get();
        user.addFriend(usrr);
        if(user != null){
            userRepository.save(user);
            isFriendRequestSendSuccess = true;
        }
        return  isFriendRequestSendSuccess;
    }

    public boolean acceptRequest(int id, int requestUserId,String status) {
        userFriendsRepository.acceptRequest(id , requestUserId , status);
        return true;
    }

    public List<User> getAllRequestsByUser(int id){
        return userRepository.getAllRequestsByUser(id);
    }

    public List<User> getAllFriends(int id){
        return userRepository.getAllFriends(id);
    }

    public boolean deleteRequest(int id, int requestUserId, String status) {
        List<UserFriends> userFriends = userFriendsRepository.getDeleteRequest(id,requestUserId,status);
        userFriendsRepository.deleteById(userFriends.get(0).getId());
        return true;
    }

    public boolean deleteIncomingRequest(int id, int requestUserId, String status) {
        List<UserFriends> userFriends = userFriendsRepository.getDeleteRequest(requestUserId,id,status);
        userFriendsRepository.deleteById(userFriends.get(0).getId());
        return true;
    }

    public boolean unfriend(int id, int requestUserId, String status) {
        List<UserFriends> userFriends = userFriendsRepository.getDeleteRequest(requestUserId,id,status);
        userFriendsRepository.deleteById(userFriends.get(0).getId());
        return true;
    }
}
