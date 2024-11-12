package com.springjpahibernate.cruddemo;

import com.springjpahibernate.cruddemo.DAO.StudentDAO;
import com.springjpahibernate.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
//			readStudent(studentDAO);

//			createStudent(studentDAO);

//			createMultipleStudents(studentDAO);

//			readAllStudent(studentDAO);

//			readStudentByLastName(studentDAO);

//			updateStudent(studentDAO);

//			deleteAllStudent(studentDAO);
		};
	}

	private void deleteAllStudent(StudentDAO studentDAO) {
		int numberDeleted = studentDAO.deleteAll();
		System.out.println("Deleted " + numberDeleted + " records");
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Getting id of student with id: " + studentId);
		studentDAO.delete(studentId);
		System.out.println("Deleted the student with id "+ studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		int studentId = 1;
		System.out.println("Getting id of student with id: " + studentId);

		Student tempStudent = studentDAO.findById(studentId);
		tempStudent.setFirstName("Scooby");
		tempStudent.setLastName("Do");
		tempStudent.setEmail("scoobydo@gmail.com");
		studentDAO.update(tempStudent);

		System.out.println("Update the student successfully" + tempStudent);
	}

	private void readStudentByLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByLastName("Jane");
		for (Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readAllStudent(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findAll();

		for (Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Read the student in DB");
		Student student = studentDAO.findById(1);
		System.out.println("Found the student" + student);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Create multiple student objects...");
		Student tempStudent1 = new Student("John", "Doe", "johndoe@gmail.com");
		Student tempStudent2 = new Student("Marry", "Jane", "marryjane@gmail.com");
		Student tempStudent3 = new Student("Bonita", "Nixon", "johnnixon@gmail.com");

		System.out.println("Save the objects");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO){
		System.out.println("Create a new student");
		Student tempStudent = new Student("Khai", "Trinh", "drkhaik");

		studentDAO.save(tempStudent);

		System.out.println("New Student save successfully with Id"+ tempStudent.getId());
	}
}
