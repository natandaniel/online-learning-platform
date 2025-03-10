package org.nd.ocp.domain.course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
  Course save(
      Course course);

  Optional<Course> findById(Integer id);

  Optional<Course> findByTitle(String title);

  List<Course> findAll();

  List<Course> findByCategory(String category);

  void deleteById(int id);
}
