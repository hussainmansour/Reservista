# Reservista

## Introduction

Welcome to Reservista! This mobile application is developed using React Native and Spring Boot. It is a reservation system providing multiple hotels in multiple cities worldwide so you can reserve any room in these hotels. To run this application, we use Docker Compose for easy setup and deployment.

## Prerequisites

Before you begin, ensure that you have the following installed on your machine:

- **Docker**: [Download and install Docker](https://docs.docker.com/get-docker/)
- **Docker Compose**: [Download and install Docker Compose](https://docs.docker.com/compose/install/)

## Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/hussainmansour/Reservista.git
   
2. **Navigate to the project directory:**

      ```bash
   cd Reservista
      
3. **Build and run the Docker containers using Docker Compose:**

   ```bash
   docker-compose up -d --build

4. **Wait for the containers to start.** You can check the logs using:
   
   ```bash
   docker-compose logs -f
  Press 'CTRL+C' to exit the log view.

5. **After the containers run, select the device you want to run this app from the log view, open your emulator or device, and the app should launch automatically.**

## Stopping the Application

To stop the application and shut down the Docker containers, run:

   ```bash
   docker-compose down
