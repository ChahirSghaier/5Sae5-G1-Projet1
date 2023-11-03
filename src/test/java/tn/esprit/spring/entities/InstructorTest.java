package tn.esprit.spring.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class InstructorTest {

    @Mock
    private IInstructorRepository instructorRepository;

    @InjectMocks
    private InstructorServicesImpl instructorService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testRetrieveAllInstructors() {
        List<Instructor> instructors = new ArrayList<>();
        instructors.add(new Instructor(1L, "John", "Doe", LocalDate.of(2020, 1, 15), new HashSet<>()));
        instructors.add(new Instructor(2L, "Jane", "Smith", LocalDate.of(2018, 3, 25), new HashSet<>()));

        when(instructorRepository.findAll()).thenReturn(instructors);

        List<Instructor> result = instructorService.retrieveAllInstructors();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());
    }

  /*  @Test
    public void testAddInstructor() {
        Instructor instructor = new Instructor("Alice", "Johnson", LocalDate.of(2021, 5, 10), new HashSet<>());

        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor addedInstructor = instructorService.addInstructor(instructor);

        assertEquals("Alice", addedInstructor.getFirstName());
    } **/

    @Test
    public void testUpdateInstructor() {
        Instructor instructor = new Instructor(3L, "Robert", "Brown", LocalDate.of(2019, 7, 8), new HashSet<>());

        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor updatedInstructor = instructorService.updateInstructor(instructor);

        assertEquals("Robert", updatedInstructor.getFirstName());
    }

    @Test
    public void testRetrieveInstructor() {
        Instructor instructor = new Instructor(4L, "Emily", "Anderson", LocalDate.of(2022, 2, 18), new HashSet<>());
        when(instructorRepository.findById(4L)).thenReturn(Optional.of(instructor));

        Instructor retrievedInstructor = instructorService.retrieveInstructor(4L);

        assertEquals("Emily", retrievedInstructor.getFirstName());
    }

  /*  @Test
    public void testDeleteInstructor() {
        Instructor instructor = new Instructor(5L, "Michael", "Wilson", LocalDate.of(2017, 9, 3), new HashSet<>());
        when(instructorRepository.findById(5L)).thenReturn(Optional.of(instructor));

        instructorService.deleteInstructor(5L);

        verify(instructorRepository, times(1)).delete(instructor);
    } **/
}
