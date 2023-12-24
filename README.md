# Reservista

## Introduction

Welcome to Reservista! This mobile application is developed using React Native and Spring Boot. It is a reservation system providing multiple hotels in multiple cities worldwide so you can reserve any room in these hotels. To run this application, we use Docker Compose for easy setup and deployment.

## Prerequisites

Before you begin, ensure that you have the following installed on your machine:

- **Docker**: [Download and install Docker](https://docs.docker.com/get-docker/)
- **Docker Compose**: [Download and install Docker Compose](https://docs.docker.com/compose/install/)
- **Make sure that the Docker engine is opened after installing it**

## Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/hussainmansour/Reservista.git
   
2. **Navigate to the project directory:**

      ```bash
      cd Reservista

3. **If you have changed the code then you should do the following steps to see the change in the application, Otherwise jump to step 4:**
   
   1.  **Navigate to the Backend directory:**
      
       ```bash
        cd Backend

   2. **Make sure that the./mvnw file has the permission to run:**
        ```bash
        chmod +x mvnw
        
   3. **Generate the Jar file:**
      ```bash
      ./mvnw clean
      ./mvnw install -DskipTests

   4. **Return to the project directory then continue from step 4:**
      ```bash
      cd ..
      
      
4. **Build and run the Docker containers using Docker Compose:**

   ```bash
   docker-compose up --build

5. **Wait for the containers to start.** You can check the logs using:
   
   ```bash
   docker-compose logs -f
  Press 'CTRL+C' to exit the log view.

6. **After the containers run, select the device you want to run this app from the log view, open your emulator or device, and the app should launch automatically.**

## Stopping the Application

To stop the application and shut down the Docker containers, run:

   ```bash
   docker-compose down
