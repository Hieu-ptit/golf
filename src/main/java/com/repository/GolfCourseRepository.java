package com.repository;

import com.domain.GolfCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GolfCourseRepository extends JpaRepository<GolfCourse, Integer>, InsertUpdateRepository<GolfCourse> {
}
