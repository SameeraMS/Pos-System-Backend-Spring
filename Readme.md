<h1>Java-Spring POS System Backend</h1>

<h3>API Documentation :</h3>
https://documenter.getpostman.com/view/36188605/2sAXxTdWoQ

<h3>Java-Spring POS System Frontend</h3>
https://github.com/SameeraMS/Pos-System-Frontend-Spring.git

<h3>Description</h3>

This project is built using Java, Spring MVC for the backend, 
MySQL as the database, Hibernate and AJAX (or Fetch API) 
for handling asynchronous communication between the client
and server. The application leverages Hibernate JPQL for
database operations to ensure efficient query handling.
Proper logging is implemented across the application,
following best practices for logging levels to ensure 
maintainability and traceability.

<h3>Tech Stack</h3>

<ul>
  <li>Java-Spring: A powerful, feature-rich framework used for building Java-based enterprise applications.</li>
  <li>Hibernate: A popular ORM (Object-Relational Mapping) framework for Java, simplifying database interactions by mapping Java objects to database tables.</li>
  <li>MySQL: A widely used relational database management system known for its reliability and ease of use.</li>
  <li>AJAX: For asynchronous communication between the client and server, enabling dynamic updates to web pages without requiring a full page reload.</li>
</ul>


<h3>Features</h3>

Spring MVC for the backend.
MySQL as the database.
Hibernate for database operations.
AJAX/Fetch API for dynamic content loading.
Comprehensive logging strategy with appropriate logging levels.

<h3>Setup & Installation</h3>

<ul>
  <li>Clone the repository:
    <br>git clone https://github.com/SameeraMS/Pos-System-Backend-Spring.git
  </li>

  <li>Configure the Database:
    <br>Set up your MySQL database.
  </li>

  <li>Update Hibernate Configuration:
    <br>Update and configure the Hibernate settings for MySQL. Ensure the correct JDBC URL, username, and password are set for your MySQL database.
  </li>

  <li>Update Logger Configuration:
    <br>Update the logger configuration for the application in logback.xml.
  </li>

  <li>Build & Deploy:
    <br>Build the project using Maven.
    <br>Run the Spring application.
  </li>

  <li>Access the Application:
    <br>Once the backend is running, access the frontend application via your web browser.
    <br>Frontend: <a href="https://github.com/SameeraMS/Pos-System-Frontend-Spring.git">https://github.com/SameeraMS/Pos-System-Frontend-Spring.git</a>
  </li>
</ul>


<h3>Logging Configuration</h3>

The application employs a robust logging mechanism with different logging levels:

<ul>
<li>INFO: General application flow.</li>
<li>DEBUG: Detailed debugging information.</li>
<li>ERROR: Error events of considerable importance that might still allow the application to continue running.</li>
<li>WARN: Potentially harmful situations.</li>
</ul>

