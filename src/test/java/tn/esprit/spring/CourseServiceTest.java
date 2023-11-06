package tn.esprit.spring;

import lombok.AllArgsConstructor;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.io.IOException;
import java.util.*;

import static com.sun.java.accessibility.util.GUIInitializedMulticaster.add;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;
@AllArgsConstructor
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    private Course course;
    private List<Course> courseList;
    @InjectMocks
    private CourseServicesImpl courseServices;
    @Mock
    private ICourseRepository courseRepository;
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
        when(courseRepository.save(any(Course.class))).thenReturn(course);
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
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Course courseTest = courseServices.retrieveCourse(course.getNumCourse());
        Mockito.verify(courseRepository).findById(1L);
        assertTrue("validation du recheche par identifiant ",courseTest.getNumCourse().equals(1L));
        assertEquals("validation ",course,courseTest);
    }
    @Test
    void retrieveAllCourses() throws IOException
    {
        courseList.add(course);
        courseList.add(new Course(2L,5,TypeCourse.COLLECTIVE_ADULT,Support.SNOWBOARD,800.2f,100));
        when(courseRepository.findAll()).thenReturn(courseList);
        List<Course> courseList1 =  courseServices.retrieveAllCourses();
        Mockito.verify(courseRepository).findAll();
        assertEquals("Validation of retrieve all courses ",courseList,courseList1);
    }


}
