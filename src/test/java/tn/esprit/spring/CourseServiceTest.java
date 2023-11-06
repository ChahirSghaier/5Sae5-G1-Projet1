package tn.esprit.spring;

import lombok.AllArgsConstructor;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
import static org.springframework.test.util.AssertionErrors.*;

@AllArgsConstructor
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
     Course course;
     List<Course> courseList;
    @InjectMocks
     CourseServicesImpl courseServices;
    @Mock
     ICourseRepository courseRepository;
    @Before
    public void setUp() throws IOException
{
      course = new Course(1L, 5, TypeCourse.INDIVIDUAL, Support.SKI, 500.2f, 30);
      courseList = new ArrayList<Course>(){
          {
              add(course);
              add(new Course(2L,10,TypeCourse.COLLECTIVE_ADULT,Support.SNOWBOARD,100.2f,60));
          }
      };


}
    @After
    public void tearDown() throws IOException
{
    course = null;
    courseList = null;
}
    @Test
    void addCourse() throws IOException
    {
        Mockito.when(courseRepository.save(any(Course.class))).thenReturn(course);
        Course courseTest = courseServices.addCourse(course);
        Mockito.verify(courseRepository).save(courseTest);
        assertEquals("le champs numéro du course est valide",1L,courseTest.getNumCourse());
        assertEquals("le champs level est valide",5,courseTest.getLevel());
        assertEquals("le champs price est valide",500.2f,courseTest.getPrice());
        assertEquals("le champs timeSlot est valide",30,courseTest.getTimeSlot());
        assertEquals("le champs support est valide",Support.SKI,courseTest.getSupport());
        assertEquals("le champs typeCourse est valide",TypeCourse.INDIVIDUAL,courseTest.getTypeCourse());
    }
    @Test
    void testRetrieveCourse() throws IOException
    {
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(course));
        Course courseTest = courseServices.retrieveCourse(1L);
        assertNotNull("Not Null",courseTest);
        assertTrue("validation du recheche par identifiant ",courseTest.getNumCourse().equals(1L));
        assertEquals("validation ",course,courseTest);
    }
    @Test
    void retrieveAllCourses() throws IOException
    {
        Mockito.when(courseRepository.findAll()).thenReturn(courseList);
        List<Course> courseList1 =  courseServices.retrieveAllCourses();
        Mockito.verify(courseRepository).findAll();
        assertEquals("Validation of retrieve all courses ",courseList,courseList1);
        assertNotNull("Not Null",courseList1);
    }


}
