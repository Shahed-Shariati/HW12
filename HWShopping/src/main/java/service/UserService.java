package service;

import model.User;
import repository.UserRepository;
import utility.UserNotFoundException;

import java.sql.Connection;
import java.util.List;

public class UserService implements Service<User>{

    private UserRepository userRepository;
    private Connection connection;

    public UserService(Connection connection) {
        this.connection = connection;
        userRepository = new UserRepository(connection);
    }

    public int save(String rolId, String firstName, String lastName, String phoneNumber, String address, String userName, String password)
    {
                int role = Integer.parseInt(rolId);
                User user = new User(Integer.MIN_VALUE, role, firstName, lastName, phoneNumber, address, userName, password);
                return userRepository.save(user);

    }

    public User login(String userName,String password){
        User user = userRepository.findByUserName(userName);
        if(user == null){
           throw new UserNotFoundException("there is no user with this username");
        }
        if(user.getPassWord().equals(password) ){
            return user;
        }else {
            System.out.println(" user name or password is wrong");

        }
        return null;
    }



    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void upDate(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
