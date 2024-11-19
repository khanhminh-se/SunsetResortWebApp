package org.example.sunsetresortwebapp.Repository;

import jakarta.transaction.Transactional;
import org.example.sunsetresortwebapp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User,Long> {
        User findUserByEmail(String email);
        User findUserById(long id);
        User save(User user);
        List<User> findAll();
        void deleteUserById(long id);
        void deleteUserByEmail(String email);
        void deleteAll();
}
