import model.Car;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.CarService;
import service.UserService;

public class CarServiceTest {

    @Test
    public void carServiceCreateCarAsEmployeeWorks(){
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createUser(new User("Test1","Test","test","test",1, User.Role.EMPLOYEE));
        userService.createUser(new User("Test2","Test","test","test",2, User.Role.CUSTOMER));
        userService.createUser(new User("Test3","Test","test","test",3, User.Role.EMPLOYEE));
        userService.getUsers();
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),3);
        Assertions.assertEquals(1, carService.carCount());
    }

    @Test
    public void carServiceCreateCarAsCustomerDoesNotWork(){
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createUser(new User("Test","Test","test","test",1, User.Role.CUSTOMER));
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),1);
        Assertions.assertEquals(0, carService.carCount());
    }
}
