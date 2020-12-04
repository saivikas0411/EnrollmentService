package com.enrollment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.enrollment.entity.Dependent;
import com.enrollment.entity.Enrollment;

public interface EnrollmentService {
	public ResponseEntity<Enrollment> addEnrollment(Enrollment enrollment);
	public ResponseEntity<List<Enrollment>> getAllEnrollees() ;
	public ResponseEntity<Enrollment> getEnrollmentById(int enrollmentId);
	public ResponseEntity<Void> removeEnrollment(int enrollmentId);
	ResponseEntity<Enrollment> update(int id, Enrollment enrollment);
	public ResponseEntity<Dependent> addDependent(int enrolleeId, Dependent dependent);
	public ResponseEntity<Dependent> updateDependent(int enrolleeId, int dependentId, Dependent dependent);
	public ResponseEntity<Void> deleteDependentByEnrolleeId(int enrolleeId, int dependentId) ;
}
