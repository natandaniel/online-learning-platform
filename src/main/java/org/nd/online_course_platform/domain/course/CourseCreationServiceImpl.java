package org.nd.online_course_platform.domain.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseCreationServiceImpl implements CourseCreationService {
  @Autowired
  private CourseRepository courseRepository;

  @Override
  public CourseDTO createCourse(CourseDTO courseDTO) {
    Course newCourse = createEmptyCourse(courseDTO);
    courseDTO.modules().forEach(input -> createCourseModule(input, newCourse));
    return CourseDTO.toDTO(courseRepository.save(newCourse));
  }

  private static void createCourseModule(CourseModuleDTO input, Course newCourse) {
    newCourse.createModule(input.title(), input.description())
             .ifPresent(courseModule -> addLessons(input, courseModule));
  }

  private static void addLessons(CourseModuleDTO input, CourseModule newCourseModule) {
    input.lessons().forEach(lessonDTO -> addLesson(lessonDTO, newCourseModule));
  }

  private static void addLesson(LessonDTO input, CourseModule newCourseModule) {
    newCourseModule.addLesson(input.title(), input.contentUrl(), input.free());
  }

  private static Course createEmptyCourse(CourseDTO courseDTO) {
    return new Course(null, courseDTO.title(), courseDTO.description(), courseDTO.category(),
        false);
  }
}
