package org.example.sunsetresortwebapp.Services;
import org.example.sunsetresortwebapp.Models.CheckUserResponse;
import org.example.sunsetresortwebapp.Models.User;
import org.example.sunsetresortwebapp.Repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User findUserById(Long id) {
        return   userRepository.findUserById(id);
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public void deleleteUserById(long id) {
            userRepository.deleteById(id);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
    public CheckUserResponse checkUser(String email , String password){

        User user = userRepository.findUserByEmail(email);

        CheckUserResponse response = new CheckUserResponse();
        if(user == null){
            response.setMessage("User not found");
            response.setSuccess(false);
        }else{
            String hashedPassword = BCrypt.hashpw(password, user.getSalt());
            System.out.println(hashedPassword);
            System.out.println(user.getPassword());
            if(!hashedPassword.equals(user.getPassword())){
                response.setMessage("Wrong password");
                response.setSuccess(false);
            }else{
                response.setMessage("Successfully logged in");
                response.setSuccess(true);
            }
        }
        return response;
    }
    public CheckUserResponse registerUser(User user){
        CheckUserResponse response = new CheckUserResponse();
        User currentUser = userRepository.findUserByEmail(user.getEmail());
        if(currentUser != null){
            response.setMessage("User  with the email already exists");
            response.setSuccess(false);
        }else{
            String salt = BCrypt.gensalt();
            user.setSalt(salt);
            user.setPassword(BCrypt.hashpw(user.getPassword(),salt));
            userRepository.save(user);
            response.setMessage("User successfully registered");
            response.setSuccess(true);
        }
        return response;
    }
}