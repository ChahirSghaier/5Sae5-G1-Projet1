package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class InstructorServicesImplTest {

    @InjectMocks
    private InstructorServicesImpl instructorService;

    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddInstructor() {
        Instructor sampleInstructor = new Instructor();
        sampleInstructor.setNumInstructor(1L);
        sampleInstructor.setFirstName("John");
        sampleInstructor.setLastName("Doe");

        Mockito.when(instructorRepository.save(Mockito.any(Instructor.class))).thenReturn(sampleInstructor);

        Instructor addedInstructor = instructorService.addInstructor(sampleInstructor);

        Mockito.verify(instructorRepository).save(sampleInstructor);

        assertEquals(1L, addedInstructor.getNumInstructor());
        assertEquals("John", addedInstructor.getFirstName());
        assertEquals("Doe", addedInstructor.getLastName());
    }

    @Test
    public void testRetrieveAllInstructors() {
        Instructor instructor1 = new Instructor();
        instructor1.setNumInstructor(1L);
        instructor1.setFirstName("John");
        instructor1.setLastName("Doe");

        Instructor instructor2 = new Instructor();
        instructor2.setNumInstructor(2L);
        instructor2.setFirstName("Jane");
        instructor2.setLastName("Smith");

        List<Instructor> instructorList = new ArrayList<>();
        instructorList.add(instructor1);
        instructorList.add(instructor2);

        Mockito.when(instructorRepository.findAll()).thenReturn(instructorList);

        List<Instructor> retrievedInstructors = instructorService.retrieveAllInstructors();

        Mockito.verify(instructorRepository).findAll();

        assertEquals(2, retrievedInstructors.size());
        assertEquals(1L, retrievedInstructors.get(0).getNumInstructor());
        assertEquals("John", retrievedInstructors.get(0).getFirstName());
        assertEquals("Doe", retrievedInstructors.get(0).getLastName());
        assertEquals(2L, retrievedInstructors.get(1).getNumInstructor());
        assertEquals("Jane", retrievedInstructors.get(1).getFirstName());
        assertEquals("Smith", retrievedInstructors.get(1).getLastName());
    }

    @Test
    public void testUpdateInstructor() {
        Instructor sampleInstructor = new Instructor();
        sampleInstructor.setNumInstructor(1L);
        sampleInstructor.setFirstName("John");
        sampleInstructor.setLastName("Doe");

        Mockito.when(instructorRepository.save(Mockito.any(Instructor.class))).thenReturn(sampleInstructor);

        Instructor updatedInstructor = instructorService.updateInstructor(sampleInstructor);

        Mockito.verify(instructorRepository).save(sampleInstructor);

        assertEquals(1L, updatedInstructor.getNumInstructor());
        assertEquals("John", updatedInstructor.getFirstName());
        assertEquals("Doe", updatedInstructor.getLastName());
    }

    @Test
    public void testRetrieveInstructor() {
        Instructor sampleInstructor = new Instructor();
        sampleInstructor.setNumInstructor(1L);

        Mockito.when(instructorRepository.findById(1L)).thenReturn(Optional.of(sampleInstructor));

        Instructor retrievedInstructor = instructorService.retrieveInstructor(1L);

        Mockito.verify(instructorRepository).findById(1L);

        assertEquals(1L, retrievedInstructor.getNumInstructor());
    }
}
