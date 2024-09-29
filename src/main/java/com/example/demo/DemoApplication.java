package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Demo API",
				version = "v1",
				description = "Demo API documentation"
		)
)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		new DemoApplication().testDatabaseConnection();
	}
	public void testDatabaseConnection() {
		String url = "jdbc:postgresql://localhost:5432/sitis_db";
		String user = "sitis"; // Your PostgreSQL username
		String password = "sitis_db"; // Replace with your actual password

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM exam_inf201.patients LIMIT 1"); // Replace 'your_table' with an actual table name

			while (rs.next()) {
				System.out.println("Data from database: " + rs.getString(1)); // Print the first column's data
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

