# Car Dealership Web API
___
###### `Overview`
This API allows you to emulate the process of visiting a Car Dealership, viewing cars, making offers on cars, and if your 
offer is accepted than formulating a payment plan that works for you.
---
###### `Tutorial`


> ### /users portion
>* **GET /users**  &emsp;&emsp;&emsp;&emsp;&ensp;&nbsp;&nbsp;View all the users
>* **POST /users**  &emsp;&emsp;&emsp;&emsp;&nbsp;Create a new user
>* **GET /users/{id}**  &emsp;&emsp;&emsp;View a user by their ID
>* **PUT /users/{id}**  &emsp;&emsp;&emsp;Modify a user at their ID
>* **DELETE /users/{id}**  &emsp;&ensp;Delete a user at their ID


```
{
    "firstName": "Test",
    "lastName": "Test",
    "userName": "Test",
    "password": "Test",
    "role": "EMPLOYEE"
}
```
---

> ### /cars portion
>* **GET /cars** &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;View all the cars
>* **GET /users/{id}/cars** &emsp;&ensp;&nbsp;&nbsp;&nbsp;View cars owned by a specific user
>* **POST /users/{id}/cars** &emsp;&ensp;Create a new car
>* **GET /cars/{id}** &emsp;&emsp;&emsp;&emsp;&emsp;View a car via Car ID
>* **PUT /cars/{id}**&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;Modify a car via Car ID
>* **DELETE /cars/{id}**&emsp;&emsp;&emsp;&ensp;&nbsp;Modify a car via Car ID

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

```
{
    "amount" : 500,
    "userId" : 1,
    "status" : "OPEN"
}
```
---

