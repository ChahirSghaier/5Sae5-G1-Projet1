package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InstructorServiceImplTestWithoutMock {

    @Autowired
    private InstructorServicesImpl instructorServices;

    @BeforeEach
    public void setUp() {
        // Add setup logic for the Instructor entity if needed.
    }

    @Test
    public void testRetrieveAllInstructors() {
        List<Instructor> instructors = instructorServices.retrieveAllInstructors();
        assertNotNull(instructors);
        assertFalse(instructors.isEmpty());
        // Add assertions as needed for the retrieved Instructor entities.
    }

    @Test
    public void testAddInstructor() {
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");

        Instructor addedInstructor = instructorServices.addInstructor(instructor);

        assertNotNull(addedInstructor);
        assertNotNull(addedInstructor.getNumInstructor());
        assertEquals("John", addedInstructor.getFirstName());
        assertEquals("Doe", addedInstructor.getLastName());

        // You can also retrieve the added instructor from the service/repository and perform assertions.
    }

    @Test
    public void testUpdateInstructor() {
        // Add an Instructor entity, update it, and then assert the changes.
        Instructor instructor = new Instructor();
        instructor.setFirstName("Jane");
        instructor.setLastName("Smith");

        Instructor addedInstructor = instructorServices.addInstructor(instructor);

        addedInstructor.setFirstName("Updated First Name");
        Instructor updatedInstructor = instructorServices.updateInstructor(addedInstructor);

        assertNotNull(updatedInstructor);
        assertEquals("Updated First Name", updatedInstructor.getFirstName());

    }

    @Test
    public void testRetrieveInstructor() {
        // Add an Instructor entity, retrieve it by ID, and assert its attributes.
        Instructor instructor = new Instructor();
        instructor.setFirstName("Alice");
        instructor.setLastName("Johnson");

        Instructor addedInstructor = instructorServices.addInstructor(instructor);

        Instructor retrievedInstructor = instructorServices.retrieveInstructor(addedInstructor.getNumInstructor());
        assertNotNull(retrievedInstructor);
        assertEquals("Alice", retrievedInstructor.getFirstName());
        assertEquals("Johnson", retrievedInstructor.getLastName());
    }


}
