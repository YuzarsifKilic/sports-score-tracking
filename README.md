# Sport Score Tracking Application

This application displays results and statistics of sports competitions. It utilizes API-SPORTS to fetch data and covers three main sports: Football, NBA, and Formula 1. The application is secured with role-based protection using Spring Security and JWT tokens.

### Features
* Real-time sports results and statistics
* Coverage for Football, NBA, and Formula 1
* Secure access with role-based protection
* User authentication and authorization using JWT tokens

### Technologies Used

* Spring Boot
* Angular
* MySQL
* MongoDB
* API-SPORTS (Football, NBA, Formula 1)

## Installation

### Prerequisites

* Java Development Kit (JDK) 21 or higher
* Node.js and npm
* MySQL
* MongoDB
* Angular CLI

### Backend Setup

1. Clone the repository.:

````
git clone https://github.com/YuzarsifKilic/sports-score-tracking
cd api
````

2. Configure the environment variables:

````
export DATABASE_USERNAME=mysql-username
export DATABASE_PASSWORD=mysql-password
export DATABASE_URL=mysql-database-url
export MONGODB_URI=mongodb-uri
export MONGODB_DATABASE=your-mongodb-database
export RAPID_API_KEY=your-sports-api-key
````

3. Ensure the following settings are in application.yml:

````
spring:
  datasource:
    driverClassName: com.mysql.cj.jdbc.Driver
    username: ${DATABASE_USERNAME}
    password: ${DATABASE_PASSWORD}
    url: ${DATABASE_URL}
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
  data:
    mongodb:
      uri: ${MONGODB_URI}
      database: ${MONGODB_DATABASE}
      
sport-api:
  x-rapidapi-key: ${RAPID_API_KEY}
````

4. Build and run the backend application:

````
./mvnw clean install
./mvnw spring-boot:run
````

### Frontend Setup

1. Ensure you have Angular CLI installed:
````
npm install -g @angular/cli
````

2. Navigate to the frontend directory:

````
cd frontend
````

3. Isntall the dependencies:

````
npm install
````

4. Start the frontend application:

````
ng serve
````

5. Open your browser and navigate to http://localhost:4200


## Usage

### User Authentication
* Users can sign up and log in to access the application.
* Role-based access control ensures that only authorized users can access certain features.

## API Integration
* The application integrates with API-SPORTS to fetch real-time data for Football, NBA, and Formula 1.
* Ensure you have a valid API-SPORTS key and configure it in the backend application properties:
````
sport-api:
  x-rapidapi-key: ${RAPID_API_KEY}
````