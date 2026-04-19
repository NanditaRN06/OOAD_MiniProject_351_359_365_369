# Centralized Platform for Sharing Capstone Projects and Subject Feedback

## Project Statement
The objective of this project is to create a unified web platform connecting students and faculty for seamless management of capstone projects and academic feedback. Students can showcase their completed capstone projects, explore peer projects for inspiration using search functionalities, and submit detailed course feedback. Faculty can access this feedback to improve their teaching methodologies and monitor student projects. The platform features role-based dashboards and an interactive commenting and rating system, ensuring an engaging and collaborative academic environment.

## Tools & Technologies
- **Backend Framework**: Java Spring Boot 3.x
- **Database**: MySQL with Hibernate / Spring Data JPA
- **Frontend Template Engine**: Thymeleaf
- **Styling**: Vanilla CSS3 & HTML5
- **Build Tool**: Maven

## Download Instructions
1. Clone the repository to your local machine:
   ```bash
   git clone <repository_url>
   ```
2. Navigate into the application directory:
   ```bash
   cd OOAD_MiniProject_351_359_365_369/capstone_project_sharing_platform
   ```
3. Ensure you have **Java 17+** installed.
4. Ensure **MySQL** is installed and running locally on port 3306.

## Run Commands
1. **Configure Database**:
   - Create a database in MySQL named `capstone_db`:
     ```sql
     CREATE DATABASE capstone_db;
     ```
   - Update `application.properties` with your MySQL credentials (default expects username `root` and password `_Benaka@1203`).

2. **Compile the Project**:
   ```bash
   ./mvnw clean compile
   ```

3. **Run the Application**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the Application**:
   - Open your web browser and navigate to: `http://localhost:8080/auth/login`

## Authors
- PES2UG23CS351 - [Moulya K A](https://github.com/moulyajs)
- PES2UG23CS359 - [Najmus Seher](https://github.com/Sehar-12)
- PES2UG23CS365 - [Nandita R Nadig](https://github.com/NanditaRN06)
- PES2UG23CS369 - [Naveen S](https://github.com/nh-44)