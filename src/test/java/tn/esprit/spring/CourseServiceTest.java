package tn.esprit.spring;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.io.IOException;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class CourseServiceTest {
     @InjectMocks
     CourseServicesImpl courseServices;
     @Mock
     ICourseRepository courseRepository;

    @Before
    public void setUp() throws IOException
{
     MockitoAnnotations.openMocks(this);
}
    @After
    public void tearDown() throws IOException
{
    initMocks(this);
}
    @Test
    void addCourse() throws IOException
    {   Course course = new Course();
        course.setNumCourse(1L);
        course.setTypeCourse(TypeCourse.INDIVIDUAL);
        course.setLevel(5);
        course.setSupport(Support.SKI);
        course.setPrice(500.7f);
        course.setTimeSlot(30);
        Mockito.when(courseRepository.save(any(Course.class))).thenReturn(course);
        Course courseTest = courseServices.addCourse(course);
        Mockito.verify(courseRepository).save(courseTest);
        assertEquals("le champs numéro du course est valide",1L,courseTest.getNumCourse());
        assertEquals("le champs level est valide",5,courseTest.getLevel());
        assertEquals("le champs price est valide",500.7f,courseTest.getPrice());
        assertEquals("le champs timeSlot est valide",30,courseTest.getTimeSlot());
        assertEquals("le champs support est valide",Support.SKI,courseTest.getSupport());
        assertEquals("le champs typeCourse est valide",TypeCourse.INDIVIDUAL,courseTest.getTypeCourse());
        assertTrue(" validation d egalitee ",course == courseTest);
    }
    @Test
    void testRetrieveCourse() throws IOException
    {   Course course = new Course();
        course.setNumCourse(1L);
        course.setTypeCourse(TypeCourse.INDIVIDUAL);
        course.setLevel(5);
        course.setSupport(Support.SKI);
        course.setPrice(500.7f);
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(course));
        Course courseTest = courseServices.retrieveCourse(1L);
        assertNotNull("Not Null",courseTest);
        assertTrue("validation du recheche par identifiant ",course.getNumCourse().equals(1L));
        assertEquals("validation ",course,courseTest);
    }
    @Test
    void retrieveAllCourses() throws IOException
    {
        List<Course> courseList=new ArrayList<>();
        Course course = new Course();
        course.setNumCourse(2L);
        course.setTypeCourse(TypeCourse.INDIVIDUAL);
        course.setLevel(8);
        course.setSupport(Support.SNOWBOARD);
        course.setPrice(400.2f);
        courseList.add(course);
        Mockito.when(courseRepository.findAll()).thenReturn(courseList);
        List<Course> courseList1 =  courseServices.retrieveAllCourses();
        Mockito.verify(courseRepository).findAll();
        assertEquals("Validation of retrieve all courses ",courseList,courseList1);
        assertNotNull("Not Null",courseList1);
    }


}
