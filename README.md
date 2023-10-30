![](logo.png)
# <div align="center">Book Buying Service</div>

___
### Welcome to Book Buying Service!

#### This application is powered by Spring Boot, offering a convenient and efficient way to purchase literature to suit any taste. Whether you're looking for a classic piece or a modern literary novelty, this service is designed to make your buying experience as comfortable as possible.

#### In this README, you'll find detailed information about this project, its features, and how to start using book buying service. We invite you to discover a convenient and reliable way to purchase literature, from classics to contemporary genres.

#### Let's dive in!

---

## Content
- [👨‍💻Project Overview](#overview)
- [🌟Features](#features)
- [‍📝Controllers](#controllers)
- [🛠️Installation](#installation)
- [🕹️️Usage](#usage)
- [🎯Summary](#summary)

<hr>
<div id="overview" align="center">
  <h2 >👨‍💻 Project Overview</h2>
</div>
<hr>


### Technologies used
___
#### Backend Technologies
- **Spring Boot:** The project is built using Spring Boot templates and conventions.
- **Spring Web:** Spring Web is used for handling HTTP requests, managing sessions, and processing web-related tasks.
- **Spring Security:** Authentication and authorization are managed to ensure secure access to endpoints using Spring Security.
- **Spring Data JPA:** Spring Data JPA simplifies database operations and enables easy interaction with the database.
___
#### Additional Backend Libraries
- **JWT:** JWT is used to implement the main principles of REST, ensuring statelessness.
- **Lombok:** Lombok reduces boilerplate code by automatically generating common code constructs.
- **Swagger:** Swagger provides interactive API documentation, making it easier to test and use APIs.
- **Mapstruct:** Mapstruct is used for object mapping between DTOs and entities.
___
#### Database and Data Management
- **MySQL:** MySQL is used as the database management system.
- **Liquibase:** Liquibase is employed for database version control and management.
- **Hibernate:** Hibernate simplifies the interaction between Java code and the database.
___
#### Other Tools and Services
- **Docker:** Docker is used for containerizing the database and for testing purposes.
- **Postman:** Postman is used to automate testing workflows and present the application.
___
### Domain Models (Entities)
___

> - **Book**: Represents a book available in the store.
> - **Cart item**: Represents an item in a user's shopping cart. 
> - **Category**: Represents a category that a book can belong to.
> - **Order**:  Represents an order placed by a user.
> - **Order item**: Represents an item in a user's order.
> - **Role**: Represents the role of a user in the system, for example, admin or user.
> - **Shopping cart**: Represents a user's shopping cart.
> - **User**: Contains information about the registered user including their authentication details and personal information.

### What are the user types in the application?
___

#### Non-authenticated users:

* Register for an account.
* Log in to their account.

#### Users with the role USER:

* View a list of available books.
* Access detailed information about a specific book.
* Explore book categories and view books within specific categories.

#### Users with the role ADMIN:

* Create new books.
* Update existing book information.
* Delete books from the system.
* Create new book categories.
* Update existing category details.
* Delete categories from the system.

<hr>
<div id="features" align="center">
  <h2 >🌟Features</h2>
</div>
<hr>


[Back to content](#content)

### 1. User Authentication and Registration:


- User Authentication: Users can register and log in to the system.
- Role-Based Access Control: User roles include USER (default) and ADMIN, with different access levels.
- JWT Support: JSON Web Tokens (JWT) are used for authentication and securing API endpoints.
- Data Validation: Data validation is implemented in the new DTO classes to ensure the integrity of user inputs.
- Field Matching: Password and repeatPassword fields in registration are checked to ensure they match.

### 2. Book Management:

- Book Catalog: Users can browse the book catalog and view book details.
- Searching Books (Optional): Users can search for books based on specified search parameters.
- Admin Actions: Admins can add, update, and remove books from the catalog.

### 3. Category Management:

- Category Browsing: Users can browse categories and view books by category.
- Admin Actions: Admins can create new categories, update category details, and remove categories.

### 4. Shopping Cart Management:

- Adding Books: Users can add books to their shopping cart.
- Viewing Cart: Users can view their shopping cart contents before placing an order.
- Removing Books: Users can remove books from their shopping cart.

### 5. Order Processing:

- Placing Orders: Users can place orders to purchase books in their shopping cart.
- Viewing Order History: Users can view their order history to track past purchases.
- Order Item Retrieval: Users can view the items within their orders, including specific order items.

### 6. Order Management (Admin):

- Admins can update the status of orders to manage the order processing workflow.

<hr>
<div id="controllers" align="center">
  <h2 >‍📝Controllers</h2>
</div>
<hr>


[Back to content](#content)


> _To start using application, the user must register and log in to the system._

#### Authentication controller `/auth`

|        Feature        |      Endpoint      |                         Required fields                                      |
|-----------------------|:------------------:|:----------------------------------------------------------------------------:|
| Register a new user   | POST:/api/auth/register | email, password, repeat password, first name, last name   |
| Log in                |  POST:/api/auth/login   | email, password                                                              |

> After user login, the user acquires a role based on their credentials and gains corresponding permissions in the application.

#### BookController `/books`

| Feature           |        Endpoint         | USER | ADMIN |
|-------------------|:-----------------------:|:----:|:-----:|
| Get all books     |     GET: /api/books     |  ✔   |   X   | 
| Get book by id    |  GET: /api/books/{id}   |  ✔   |   X   | 
| Add book          |   POST: /api/books/     |  X   |   ✔   |
| Update book by id |  PUT: /api/books/{id}   |  X   |   ✔   | 
| Delete book by id | DELETE: /api/books/{id} |  X   |   ✔   | 


#### CategoryController `/categories`

| Feature               |            Endpoint             |  USER   | ADMIN |
|-----------------------|:-------------------------------:|:-------:|:-------:|
| Get all categories    |      GET: /api/categories       |    ✔    |    X    | 
| Get category by id    |    GET: /api/categories/{id}    |    ✔    |    X    | 
| Get books by category | GET: /api/categories/{id}/books |    ✔    |    X    | 
| Add category          |      POST: /api/categories      |    X    |    ✔    | 
| Update category by id |    PUT: /api/categories/{id}    |    X    |    ✔    | 
| Delete category by id |  DELETE: /api/categories/{id}   |    X    |    ✔    | 

#### OrderController `/orders`

| Feature                      |                 Endpoint                  |  USER   | ADMIN |
|------------------------------|:-----------------------------------------:|:-------:|:-------:|
| Get all user orders          |             GET: /api/orders              |    ✔    |    X    | 
| Add new order                |             POST: /api/orders             |    ✔    |    X    | 
| Get all items in order       |     GET: /api/orders/{orderId}/items      |    ✔    |    X    | 
| Get item in order by item id | GET: /api/orders/{orderId}/items/{itemId} |    ✔    |    X    | 
| Update order status          |          PATCH: /api/orders/{id}          |    X    |    ✔    | 

#### ShoppingCartController `/cart`

| Feature                        |                 Endpoint                  |  USER  | ADMIN |
|--------------------------------|:-----------------------------------------:|:------:|:-----:|
| Get available shopping cart    |              GET: /api/cart               |   ✔    |   X   | 
| Create new shopping cart       |              POST: /api/cart              |   ✔    |   X   | 
| Add item to shopping cart      |  PUT: /api/cart/cart-items/{cartItemId}   |   ✔    |   X   | 
| Remove item from shopping cart | DELETE: /api/cart/cart-items/{cartItemId} |   ✔    |   X   | 


<hr>
<div id="installation" align="center">
  <h2 >🛠️ ️Installation</h2>
</div>
<hr>

[Back to content](#content)

>For the deepest understanding of the inner workings of this project, please provide a comprehensive installation guide.

### Prerequisites

Make sure you have the following apps installed, this is necessary to run app:
- Java 17 (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- JDK, JDE
- Docker (https://www.docker.com/products/docker-desktop/)
- Maven (https://maven.apache.org/download.cgi)
- MySql (https://dev.mysql.com/downloads/installer/)
- You may also need Docker and Postman.

## How to start this app:
Download git repository by git command:
 ```bash
 git clone https://github.com/ihorvasylenko23/spring_boot_project.git 
 ```
Build a project using **Maven**:
 ```bash
 mvn clean install
 ```
Then, rise a **Docker** container of your app:
 ```bash
 docker build -t {imageʼs name or hash code}
 docker-compose build
 docker-compose up
 ```
Also, you can run this project without docker, but before that, you need to configure the connection to your local database in the application properties. Run this command after that:
```bash
  mvn spring-boot:run
  ```


<hr>
<div id="usage" align="center">
  <h2 >🕹️ Usage</h2>
</div>
<hr>

[Back to content](#content)

***_In this video, you'll see a demonstration of how to use this application with the Postman collection:_***

[Presentation in Postman](https://www.loom.com/share/4d493e0149534bb69c15d1f5f3a90d12)
<hr>
<div id="summary" align="center">
  <h2 >🎯 Summary</h2>
</div>
<hr>

[Back to content](#content)

***_I am delighted to introduce you to Book Buying Service!_***

***_Thank you for joining me on this journey. I have designed this project with you and your needs in mind, aiming to provide a convenient platform for purchasing books while ensuring secure online transactions._***
