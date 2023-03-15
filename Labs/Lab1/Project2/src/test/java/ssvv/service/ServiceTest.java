package ssvv.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ssvv.domain.Student;
import ssvv.repository.NotaXMLRepo;
import ssvv.repository.StudentXMLRepo;
import ssvv.repository.TemaXMLRepo;
import ssvv.validation.NotaValidator;
import ssvv.validation.StudentValidator;
import ssvv.validation.TemaValidator;
import ssvv.validation.ValidationException;

// Lab2
public class ServiceTest {

    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();

    StudentXMLRepo studentRepo = new StudentXMLRepo("fisiere/Studenti.xml");
    TemaXMLRepo temaRepo = new TemaXMLRepo("fisiere/Teme.xml");

    NotaValidator notaValidator = new NotaValidator(studentRepo, temaRepo);
    NotaXMLRepo noteRepo = new NotaXMLRepo("fisiere/Note.xml");

    Service service = new Service(studentRepo, studentValidator, temaRepo, temaValidator, noteRepo, notaValidator);

    @Test
    public void testAddStudent() {
        // Add successfully a student -> null will be returned
        Assertions.assertNull(service.addStudent(new Student("523", "Test", 205, "test@email.com")));

//        // Incorrect fields for student -> exception thrown
//        Assertions.assertThrows(ValidationException.class, () -> service.addStudent(new Student("123", "", 205, "test@email.com")));

        // Add already existing student -> student will be returned
        Assertions.assertEquals("523" ,service.addStudent(new Student("523", "Test", 205, "test@email.com")).getID());
    }
}