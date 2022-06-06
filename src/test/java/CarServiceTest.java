import com.revature.model.Car;
import com.revature.model.User;
import com.revature.service.CarService;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CarServiceTest {

    @Test
    public void carServiceCreateCarAsEmployeeWorksCarCountEquals2(){
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createUser(new User("Test1","Test","test","test",1, User.Role.EMPLOYEE));
        userService.createUser(new User("Test2","Test","test","test",2, User.Role.CUSTOMER));
        userService.createUser(new User("Test3","Test","test","test",3, User.Role.EMPLOYEE));
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),3);
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),1);
        Assertions.assertEquals(2, carService.carCount());
    }

    @Test
    public void carServiceCreateCarAsCustomerDoesNotWorkCarCountEquals0(){
        CarService carService = new CarService();
        UserService userService = new UserService();
        userService.createUser(new User("Test","Test","test","test",1, User.Role.CUSTOMER));
        userService.createUser(new User("Test2","Test","test","test",2, User.Role.CUSTOMER));
        userService.createUser(new User("Test3","Test","test","test",3, User.Role.CUSTOMER));
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),1);
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),2);
        carService.createCar(new Car("honda", "ford", 2832, Car.Status.AVAILABLE),3);
        Assertions.assertEquals(0, carService.carCount());
    }
}
