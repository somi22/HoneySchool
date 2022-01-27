package com.ssafy.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.ClassBoard;
@Repository
public interface ClassBoardRepository extends JpaRepository<ClassBoard, Integer>{
	// 전체 검색
	List<ClassBoard> findBySchoolAndGradeAndClasses(String school, int grade, int classe);
	// 카테고리 검색
	List<ClassBoard> findBySchoolAndGradeAndClassesAndCategory(String school, int grade, int classes, String category);
	
}
