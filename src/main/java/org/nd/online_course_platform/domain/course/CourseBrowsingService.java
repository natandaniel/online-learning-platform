package org.nd.online_course_platform.domain.course;

import java.util.List;
import java.util.Optional;

public interface CourseBrowsingService {

  List<CourseDTO> findAll();

  List<CourseDTO> findByCategory(String category);

  Optional<CourseDTO> findById(int id);

}
