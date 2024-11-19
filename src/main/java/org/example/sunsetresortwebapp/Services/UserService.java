package org.example.sunsetresortwebapp.Services;
import org.example.sunsetresortwebapp.Models.CheckUserResponse;
import org.example.sunsetresortwebapp.Models.User;
import org.example.sunsetresortwebapp.Repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService implements  IUserSerice{
    @Autowired
    UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
    @Override
    public User getUserById(long id) {
        return   userRepository.findUserById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleleteUserById(long id) {
            userRepository.deleteUserById(id);
    }

    @Override
    public void updateUser(User user, long id) {
        User currentUser = userRepository.findUserById(id);
        if(user.getEmail() != null &&  !user.getEmail().equalsIgnoreCase("")){
            currentUser.setEmail(user.getEmail());
        }
        if(user.getPassword() != null &&  !user.getPassword().equalsIgnoreCase("")){
            currentUser.setPassword(user.getPassword());
        }
        if (user.getFullname() != null &&  !user.getFullname().equalsIgnoreCase("")){
            currentUser.setFullname(user.getFullname());
        }
        if(user.getPhoneNumber() != null &&  !user.getPhoneNumber().equalsIgnoreCase("")){
            currentUser.setPhoneNumber(user.getPhoneNumber());
        }
        if(user.getAddress() != null &&  !user.getAddress().equalsIgnoreCase("")){
            currentUser.setAddress(user.getAddress());
        }
        if(user.getRole() != null &&  !user.getRole().equalsIgnoreCase("")){
            currentUser.setRole(user.getRole());
        }
        userRepository.save(currentUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
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