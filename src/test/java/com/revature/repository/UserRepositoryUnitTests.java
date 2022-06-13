package com.revature.repository;

import com.revature.model.User;
import com.revature.model.UserRoles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class UserRepositoryUnitTests {


    User user = new User();
    List<User> mockedList = Mockito.mock(List.class);
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
        Mockito.when(mockedList.add(user)).thenReturn(true);
        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(user);

        User result = userRepository.getById(0);

        Assertions.assertEquals(user, result);
    }

    @Test
    public void whenGivenValidIdGetUserIdByRoleReturnsCorrectUser() {
        User user = new User("Test1", "Test1", "test1", "test1", UserRoles.CUSTOMER);
        Mockito.when(mockedList.add(user)).thenReturn(true);
        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(user);

        User result = userRepository.getUserIdByRole(1, UserRoles.CUSTOMER);

        Assertions.assertEquals(user, result);
    }

    @Test
    public void whenGivenValidIdGetEmployeeByIdReturnsCorrectEmployee() {
        User user = new User("Test", "Test", "test", "test", UserRoles.EMPLOYEE);
        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(user);

        User result = userRepository.getUserIdByRole(1, UserRoles.EMPLOYEE);

        Assertions.assertEquals(user, result);
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
        user.setId(1);

        Mockito.when(mockedList.size()).thenReturn(1);
        Mockito.when(mockedList.get(0)).thenReturn(user);
        Mockito.when(mockedList.remove(0)).thenReturn(user);

        Assertions.assertTrue(userRepository.deleteById(1));
    }

    @Test
    public void whenGivenUserIdUpdateUserByIdReturnsTrue() {
        User user = new User("Test1", "Test1", "test1", "test1", UserRoles.CUSTOMER);
        Mockito.when(mockedList.size()).thenReturn(1);

        User user2 = new User("Test2", "Test2", "test2", "test2", UserRoles.EMPLOYEE);
        Mockito.when(mockedList.size()).thenReturn(2);

        Mockito.when(mockedList.size()).thenReturn(2);
        Mockito.when(mockedList.get(0)).thenReturn(user);
        Mockito.when(mockedList.set(0, user)).thenReturn(user2);

        User result = userRepository.update(user);

        Assertions.assertEquals(user2, result);
    }
}
