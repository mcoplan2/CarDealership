package model;

public class User {
    public String firstName;
    public String lastName;
    public String userName;
    public String password;
    public int id;
    //0 for customer, 1 for employee
    public boolean flag;

    public User(){
    }
    //this constructor is used to check if the user is a Customer or Employee.
   public User(boolean flag){
        this.flag=flag;
    }

    public User(String firstName, String lastName, String userName, String password, int id, boolean flag){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.id = id;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Username: " + userName + "\n";
    }
}
