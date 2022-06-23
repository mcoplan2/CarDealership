# Car Dealership Web API
___
###### `Overview`
This API allows you to emulate the process of visiting a Car Dealership, viewing cars, and making offers on cars.

---
###### `Tutorial`

> ### login portion
>* **POST /register**  &emsp;&emsp;&emsp;&emsp;&ensp;&nbsp;Create a new user
>* **POST /authenticate**  &emsp;&emsp;&nbsp;&nbsp;Generates a Bearer token to access app

POST /register example
```
{
    "firstName": "Test",
    "lastName": "Test",
    "userName": "Test",
    "password": "Test",
    "role": "EMPLOYEE"
}

Enter your information to create an account
there are two roles available "CUSTOMER" or "EMPLOYEE"
```

POST /authenticate example
```
{
    "userName": "Test",
    "password": "Test",
}

Enter the created userName and password to authenticate
```
---



> ### /users portion
>* **GET /users**  &emsp;&emsp;&emsp;&emsp;&ensp;&nbsp;&nbsp;View all the users
>* **GET /users/{id}**  &emsp;&emsp;&emsp;View a user by their ID
>* **PUT /users/{id}**  &emsp;&emsp;&emsp;Modify a user at their ID
>* **DELETE /users/{id}**  &emsp;&ensp;Delete a user at their ID

---

> ### /cars portion
>* **GET /cars** &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;View all the cars
>* **GET /users/{id}/cars** &emsp;&ensp;&nbsp;&nbsp;&nbsp;View cars owned by a specific user
>* **POST /users/{id}/cars** &emsp;&ensp;Create a new car
>* **GET /cars/{id}** &emsp;&emsp;&emsp;&emsp;&emsp;View a car via Car ID
>* **PUT /cars/{id}**&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;Modify a car via Car ID
>* **DELETE /cars/{id}**&emsp;&emsp;&emsp;&ensp;&nbsp;Modify a car via Car ID

POST /users/{id}/cars example
```
{
    "make" : "honda",
    "model" : "corolla",
    "year" : 2033,
    "status" : "AVAILABLE"
}
```
---

> ### /offers portion
>* **GET /offers** &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;&nbsp;&nbsp;View all the offers
>* **POST /cars/{id}/offers** &emsp;&ensp;&nbsp;&nbsp;Create a new offer on a specific car
>* **GET /offers/{id}** &emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;View an offer via ID
>* **PUT /offers/{id}**&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;Modify an offer via ID
>* **DELETE /offers/{id}**&emsp;&emsp;&emsp;&ensp;&nbsp;&nbsp;Modify an offer via ID
>* **POST /offers/{id}/approve**&emsp;Approve an offer via ID
>* **POST /offers/{id}/deny**&emsp;&emsp;&nbsp;&nbsp;Deny an offer via ID

POST /cars/{id}/offers
```
{
    "amount" : 500,
    "userId" : 1,
    "status" : "OPEN"
}

userId - represents the user who is making the offer on the car
```
---

POST /offers/{id}/approve
```
{
    1
}

the body contains the userId of the user who is approving the offer
(enter as a Text string, not json)
```
---

POST /offers/{id}/deny
```
{
    1
}

the body contains the userId of the user who is denying the offer
(enter as a Text string, not json)
```
---

