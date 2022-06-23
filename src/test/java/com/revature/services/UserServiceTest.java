package com.revature.services;

import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



public class UserServiceTest {

    UserService mockedService = Mockito.mock(UserService.class);
    User user = new User("Test1", "Test1", "test1", "test1", UserRoles.CUSTOMER);
    User user2 = new User("Test2", "Test2", "test2", "test2", UserRoles.EMPLOYEE);


    @Test
    public void whenGivenUserObjectCreateDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() -> mockedService.createNewUser(user));
    }

    @Test
    public void whenGivenUserObjectCreateReturnsTrue(){
        Mockito.when(mockedService.createNewUser(user)).thenReturn(user);
        User result = mockedService.createNewUser(user);
        Assertions.assertEquals(user, result);
    }

    @Test
    public void whenGetUsersIsCalledDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow(() ->mockedService.getUsers());
    }

    @Test
    public void whenGetAllUsersByRoleIsCalledDoesNotThrowAnException() {
        Assertions.assertDoesNotThrow( () -> mockedService.getAllUsersByRole(UserRoles.CUSTOMER));
    }

    @Test
    public void whenGivenValidIdGetByIdReturnsCorrectUser() {
        Mockito.when(mockedService.userCount()).thenReturn(1);
        Mockito.when(mockedService.getUserById(0)).thenReturn(user);

        User result = mockedService.getUserById(0);

        Assertions.assertEquals(user.getId(), result.getId());
        Assertions.assertEquals(user.getFirstName(), result.getFirstName());
        Assertions.assertEquals(user.getLastName(), result.getLastName());
    }

    @Test
    public void whenUserCountIsCalledItReturnsTheCorrectNumberOfUsers(){
        Mockito.when(mockedService.userCount()).thenReturn(2);
        int result = mockedService.userCount();
        Assertions.assertEquals(2, result);
    }

    @Test
    public void whenGivenUserIdDeleteUserByIdReturnsTrue(){
        Mockito.when(mockedService.userCount()).thenReturn(1);
        Mockito.when(mockedService.getUserById(1)).thenReturn(user);
        Mockito.when(mockedService.deleteUserById(1)).thenReturn(true);
        Assertions.assertTrue(mockedService.deleteUserById(1));
    }

    @Test
    public void whenGivenUserIdUpdateUserByIdReturnsTrue(){
        Mockito.when(mockedService.getUserById(0)).thenReturn(user);
        Mockito.when(mockedService.updateUserById(user)).thenReturn(user2);

        User result = mockedService.updateUserById(user);

        Assertions.assertEquals(user2, result);
    }
}
