package com.kwatch.user_details.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kwatch.user_details.userDetails.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    

    @Override
    public User getUserByUserName(String userName) {
        TypedQuery<User> query = this.entityManager.createQuery("SELECT un FROM User un WHERE un.userName = :userName",User.class);
        query.setParameter("userName", userName);
        
        return query.getResultStream().findFirst().orElse(null);
        
    }

    @Override
    public User getUserById(Long id) {
        User theUser = entityManager.find(User.class, id);
        
        return theUser;
    }

    @Override
    public void createUser(User userDetails) {
        entityManager.persist(userDetails);
    }

    @Override
    public void updateUser(User userDetails) {
        entityManager.merge(userDetails);
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
            "SELECT u FROM User u WHERE u.email = :email",
            User.class
        );
        query.setParameter("email", email);
        return query.getResultStream().findFirst().orElse(null);
        
    }

    @Override
    public String deleteUserById(Long userId) {
        User theUser = entityManager.find(User.class,userId);
        if(theUser == null){
            return "User does not exist";
        }
        entityManager.remove(theUser);
        return "User Deleted";
    }


}
