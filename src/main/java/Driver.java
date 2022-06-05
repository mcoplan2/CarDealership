import controller.UserController;
import io.javalin.Javalin;

public class Driver {
    public static void main(String[] arg){

        UserController userController = new UserController();
        Javalin app = Javalin.create().start(8080);

        app.get("/", context -> context.result("Welcome to the Car Dealership API"));

        // Flashcard Bindings   -> URL? -> /flashcards
        // Operations?
        // GET
        // POST
        app.get("/users", userController.getAllUsers);
        app.get("/users/{id}", userController.getUserById);
        app.post("/users", userController.createNewUser);
        app.get("/users/customers", userController.getAllCustomers);
        app.get("/users/customers/{id}", userController.getCustomerById);
        app.get("/users/employees", userController.getAllEmployees);
        app.get("/users/employees/{id}", userController.getEmployeeById);
        //layout
        /*
        /users/{id}/offers
        /users/{id}/cars
        /
        */

    }
}
