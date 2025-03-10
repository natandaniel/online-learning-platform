package org.nd.ocp.database.course;

import org.nd.ocp.domain.course.Course;
import org.nd.ocp.domain.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryAdapter implements CourseRepository {
  @Autowired
  private SpringDataCourseRepository
      springDataCourseRepository;

  @Override
  public Course save(Course course) {
    return CourseEntityMapper.toDomain(
        springDataCourseRepository.save(CourseEntityMapper.toEntity(course)));
  }

  @Override
  public Optional<Course> findById(Integer id) {
    return springDataCourseRepository.findById(id).map(CourseEntityMapper::toDomain);
  }

  @Override
  public Optional<Course> findByTitle(String title) {
    return springDataCourseRepository.findByTitle(title).map(CourseEntityMapper::toDomain);
  }

  @Override
  public List<Course> findAll() {
    return springDataCourseRepository.findAll()
                                     .stream()
                                     .map(CourseEntityMapper::toDomain)
                                     .toList();
  }

  @Override
  public List<Course> findByCategory(String category) {
    return springDataCourseRepository.findByCategory(category)
                                     .stream()
                                     .map(CourseEntityMapper::toDomain)
                                     .toList();
  }

  @Override
  public void deleteById(int courseId) {
    springDataCourseRepository.deleteById(courseId);
  }
}
