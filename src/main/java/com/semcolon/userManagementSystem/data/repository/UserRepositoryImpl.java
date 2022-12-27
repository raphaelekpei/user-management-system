package com.semcolon.userManagementSystem.data.repository;

import com.semcolon.userManagementSystem.data.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository{
    // First, we have to create a map which serves as a database to store users.
    // Note that in real applications, users will be stored in an external database.
    // Map stores users in Key(String i.e.id): Value(User)  pair.
    // String is the data type of the id and User is the data type of the user.
    private final Map<String, User> userDb = new HashMap<>();

    /* To create a user,pass in the user object(which contains all the
     information needed to create a  new user) of type User. Information such as email,
     phoneNumber, is embedded in the user object.
    */

    @Override
    public User save(User user) {
        return userDb.put(user.getId(), user); // add the user id together with the user object
    }

    /*
       To find an existing user from the map using the user's phoneNumber, pass in the
       phoneNumber, then loop through the map.
       userEntry represent each user.
       for each user in map userDb, get the value i.e. the user and store it in the variable User.
       if any of the user phoneNumber is equal to the phoneNumber we passed in, return the user object.
        else return null.
     */
    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        for (Map.Entry<String, User> userEntry : userDb.entrySet()){
            User user = userEntry.getValue();
            if(phoneNumber.equals(user.getPhoneNumber())){
                return user;
            }
        }
        return null;
    }
    /*
       To find an existing user from the map using the user's Id, pass in the Id,
       then loop through the map.
       userEntry represent each user.
       for each user in map userDb, get the value i.e. the user and store it in the variable User.
       if any of the user Id is equal to the Id we passed in, return the user object.
        else return null.
     */

    @Override
    public User findUserById(String id) {
        for (Map.Entry<String, User> userEntry : userDb.entrySet()){
            User user = userEntry.getValue();
            if(id.equals(user.getId())){
                return user;
            }
        }
        return null;
    }

    /*
       To find an existing user from the map using the user's email, pass in the email,
       then loop through the map.
       userEntry represent each user.
       for each user in userDb, get the value i.e. the user and store it in the variable User.
       if any of the user email is equal to the phoneNumber we passed in, return the user object.
        else return null.
     */

    @Override
    public User findUserByEmail(String email) {
        for (Map.Entry<String, User> userEntry : userDb.entrySet()){
            User user = userEntry.getValue();
            if(email.equals(user.getEmail())){
                return user;
            }
        }
        return null;
    }

    /*
        To get all the list of users in the map, create an empty list, then loop through map
          userEntry represent each user.
       for each user in userDb, get the value i.e. the user and store it in the variable User.
       add it to the empty list , and then return the list after adding the last user.
    */
    @Override
    public List<User> findAllUsers() {
        // create an empty list
        List<User> foundUsers = new ArrayList<>();
        for (Map.Entry<String, User> userEntry : userDb.entrySet()){
            User user = userEntry.getValue();
            foundUsers.add(user);
        }
        return foundUsers;
    }

    /*
        To update the information of an already existing user, pass in the user's id, then the updated user of type User.
        then check whether the user's id is present in the database. If it's present, replace the user that has that id with the updated user object.
        if it's not present in the db, return null.
     */
    @Override
    public User updateUser(String id, User user) {
        if(userDb.containsKey(id)){
            userDb.replace(id, user);
            return user;
        }
       return null;
    }

     /*
      To delete a user from the map, pass in the user id, then delete
      the user using the method for removing an item from a map
      */
    @Override
    public void deleteUser(String id) {
     userDb.remove(id);
    }

    /*
    To know the number of users in the userDb
     */
    @Override
    public int size() {
        return userDb.size();
    }
}
