import controller.UserController;
import io.javalin.Javalin;

public class Driver {
    public static void main(String[] arg){

        UserController userController = new UserController();
        Javalin app = Javalin.create().start(8080);

        app.get("/", context -> context.result("Welcome to the Car Dealership API"));
        app.get("/users", userController.getAllUsers);
        app.get("/users/{id}", userController.getUserById);
        app.post("/users", userController.createNewUser);
        app.get("/customers", userController.getAllCustomers);
        app.get("/customers/{id}", userController.getCustomerById);
        app.get("/employees", userController.getAllEmployees);
        app.get("/employees/{id}", userController.getEmployeeById);
        //layout
        /*
        /users/{id}/offers
        /users/{id}/cars
        /
        */

    }
}
