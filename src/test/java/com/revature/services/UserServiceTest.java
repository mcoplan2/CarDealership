package com.revature.services;

import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.repository.UserRepository;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;


public class UserServiceTest {

    List<User> mockedList = Mockito.mock(List.class);
    User user = new User();
    UserRepository userRepository = new UserRepository(mockedList);

    @Test
    public void whenGivenUserObjectCreateDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> userRepository.create(user));
    }

    @Test
    public void whenGivenUserObjectCreateReturnsTrue(){
        Mockito.when(mockedList.add(user)).thenReturn(true);
        User result = userRepository.create(user);
        Assertions.assertEquals(user, result);
    }

    @Test
    public void whenGetUsersIsCalledDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() ->userRepository.getAll());
    }

    @Test
    public void whenGetAllUsersByRoleIsCalledDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow( () -> userRepository.getAllByRole(UserRoles.CUSTOMER));
    }

    @Test
    public void whenGivenValidIdGetByIdReturnsCorrectUser() {
        User user = new User("Test", "Test", "test", "test", UserRoles.EMPLOYEE);
        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(user);

        User result = userRepository.getById(0);

        Assertions.assertEquals(user.getId(), result.getId());
        Assertions.assertEquals(user.getFirstName(), result.getFirstName());
        Assertions.assertEquals(user.getLastName(), result.getLastName());
    }

    @Test
    public void whenGivenValidIdGetUserIdByRoleReturnsCorrectUser() {
        User user = new User("Test", "Test", "test", "test", UserRoles.CUSTOMER);
        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(user);

        User result = userRepository.getUserIdByRole(0, UserRoles.CUSTOMER);

        Assertions.assertEquals(user.getId(), result.getId());
        Assertions.assertEquals(user.getFirstName(), result.getFirstName());
        Assertions.assertEquals(user.getLastName(), result.getLastName());
    }

    @Test
    public void whenGivenValidIdGetEmployeeByIdReturnsCorrectEmployee() {
        User user = new User("Test", "Test", "test", "test", UserRoles.EMPLOYEE);
        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(user);

        User result = userRepository.getUserIdByRole(0, UserRoles.EMPLOYEE);

        Assertions.assertEquals(user.getId(), result.getId());
        Assertions.assertEquals(user.getFirstName(), result.getFirstName());
        Assertions.assertEquals(user.getLastName(), result.getLastName());
    }

    @Test
    public void whenUserCountIsCalledItReturnsTheCorrectNumberOfUsers(){
        User user = new User("Test1", "Test1", "test1", "test1", UserRoles.CUSTOMER);
        Mockito.when(mockedList.size()).thenReturn(1);

        User user2 = new User("Test2", "Test2", "test2", "test2", UserRoles.EMPLOYEE);
        Mockito.when(mockedList.size()).thenReturn(2);

        int result = userRepository.count();
        Assertions.assertEquals(2, result);
    }

    @Test
    public void whenGivenUserIdDeleteUserByIdReturnsTrue(){
        User user = new User();
        user.setId(1);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(user);
        Mockito.when(mockedList.remove(0)).thenReturn(user);

        Assertions.assertTrue(userRepository.deleteById(1));
    }

    @Test
    public void whenGivenUserIdUpdateUserByIdReturnsTrue(){
        User user = new User("Test1", "Test1", "test1", "test1", UserRoles.CUSTOMER);
        Mockito.when(mockedList.size()).thenReturn(1);

        User user2 = new User("Test2", "Test2", "test2", "test2", UserRoles.EMPLOYEE);
        Mockito.when(mockedList.size()).thenReturn(2);

        Mockito.when(mockedList.size()).thenReturn(2);
        Mockito.when(mockedList.get(0)).thenReturn(user);
        Mockito.when(mockedList.set(0, user2)).thenReturn(user2);

        User result = userRepository.update(user2);

        Assertions.assertEquals(user2, result);
    }
    //               -------------------------
    //               ---INTEGRATION TESTS-----
    //-              -------------------------
    //Test that creates various different users to test different functionality
    //Testing different String combinations
    //Testing various IDs
    //Testing that Customers cannot create users
    //Flag = TRUE implies it is an Employee(Only Employees can create Users)
    //However, if there are no users, one can be created.
    @Test
    public void userServiceCreateManyUsers(){
        int numUsers = 5;
        UserService userService = new UserService();
        String[] fName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] lName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] uName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] pass = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        UserRoles[] role = {UserRoles.CUSTOMER, UserRoles.EMPLOYEE, UserRoles.CUSTOMER, UserRoles.CUSTOMER, UserRoles.CUSTOMER};

        for(int i=0; i<numUsers; i++){
            userService.createNewUser(new User(fName[i],lName[i],uName[i],pass[i],role[i]));
        }
        Assertions.assertEquals(5, userService.userCount());
    }
    @Test
    public void userServiceCreateManyUsersTestAllUsersString(){
        int numUsers = 5;
        UserService userService = new UserService();
        String[] fName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] lName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] uName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] pass = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        int[] id  = {0, 1, 2, 3, 4};
        UserRoles[] role = {UserRoles.CUSTOMER, UserRoles.EMPLOYEE, UserRoles.CUSTOMER, UserRoles.CUSTOMER, UserRoles.CUSTOMER};

        for(int i=0; i<numUsers; i++){
            userService.createNewUser(new User(fName[i],lName[i],uName[i],pass[i],role[i]));
        }
        Assertions.assertEquals("2",userService.getUsers().toString());
    }
    @Test
    public void userServiceCreateManyUsersTestCustomerString(){
        int numUsers = 5;
        UserService userService = new UserService();
        String[] fName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] lName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] uName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] pass = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        int[] id  = {0, 1, 2, 3, 4};
        UserRoles[] role = {UserRoles.CUSTOMER, UserRoles.EMPLOYEE, UserRoles.CUSTOMER, UserRoles.CUSTOMER, UserRoles.CUSTOMER};

        for(int i=0; i<numUsers; i++){
            userService.createNewUser(new User(fName[i],lName[i],uName[i],pass[i],role[i]));
        }
        Assertions.assertEquals("2",userService.getAllUsersByRole(UserRoles.CUSTOMER));
    }
    @Test
    public void userServiceCreateManyUsersTestEmployeeString(){
        int numUsers = 5;
        UserService userService = new UserService();
        String[] fName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] lName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] uName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] pass = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        int[] id  = {0, 1, 2, 3, 4};
        UserRoles[] role = {UserRoles.CUSTOMER, UserRoles.EMPLOYEE, UserRoles.CUSTOMER, UserRoles.CUSTOMER, UserRoles.CUSTOMER};

        for(int i=0; i<numUsers; i++){
            userService.createNewUser(new User(fName[i],lName[i],uName[i],pass[i],role[i]));
        }
        //Assertions.assertEquals("2",userService.getAllEmployeesAsString());
    }
    @Test
    public void userServiceCheckIfGetUserByIDWorks(){
        int numUsers = 5;
        String s = "ID: 2" + "\n" + "Username: " +"Test2" + "\n"+ "Role: " + UserRoles.EMPLOYEE + "\n\n";
        Object d = s;
        UserService userService = new UserService();
        String[] fName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] lName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] uName = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        String[] pass = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        int[] id  = {1, 2, 3, 4, 5};
        UserRoles[] role = {UserRoles.CUSTOMER, UserRoles.EMPLOYEE, UserRoles.CUSTOMER, UserRoles.CUSTOMER, UserRoles.CUSTOMER};

        for(int i=0; i<numUsers; i++){
            userService.createNewUser(new User(fName[i],lName[i],uName[i],pass[i],role[i]));
        }
        Assertions.assertEquals(d,userService.getUserById(2).toString());
    }
}
