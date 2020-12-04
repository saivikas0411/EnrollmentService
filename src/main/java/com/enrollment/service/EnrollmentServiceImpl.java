package com.enrollment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.enrollment.entity.Dependent;
import com.enrollment.entity.Enrollment;
import com.enrollment.repository.DependentRepository;
import com.enrollment.repository.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@Autowired
	DependentRepository dependentRepository;
	
	@Override
    public ResponseEntity<Enrollment> addEnrollment(Enrollment enrollment) {
        try {
        	enrollment = enrollmentRepository.save(enrollment);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
    }
	@Override
    public ResponseEntity<List<Enrollment>> getAllEnrollees() {
        List<Enrollment> enrolleeList = enrollmentRepository.findAll();
        if (enrolleeList.isEmpty()) {
            System.out.println("No enrollees found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(enrolleeList, HttpStatus.OK);
    }
	
	public ResponseEntity<Enrollment> getEnrollmentById(int enrollmentId) {
		Optional<Enrollment> enrollee = enrollmentRepository.findById(enrollmentId);
        return enrollee.isPresent() ? new ResponseEntity<>(enrollee.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@Override
    public ResponseEntity<Enrollment> update(int enrollmentId, Enrollment enrollment) {
        
        Optional<Enrollment> isEnrollmentPresent = enrollmentRepository.findById(enrollmentId);
        if (!isEnrollmentPresent.isPresent()) {
             return ResponseEntity.notFound().build(); 
        }
        enrollment.setEnrollee_Id(enrollmentId);
        enrollment = enrollmentRepository.save(enrollment);
        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }
	
	public ResponseEntity<Void> removeEnrollment(int enrollmentId) {
		try {
			enrollmentRepository.deleteById(enrollmentId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<Dependent> addDependent(int enrolleeId, Dependent dependent) {
		Enrollment enrollment;
		try {
			enrollment = enrollmentRepository.getOne(Integer.valueOf(enrolleeId));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		dependent.setEnrollee(enrollment);
		dependentRepository.save(dependent);
		return new ResponseEntity<>(dependent, HttpStatus.CREATED);
	}

	public ResponseEntity<Void> deleteDependentByEnrolleeId(int enrolleeId, int dependentId) 
	{
		Enrollment enrollment;
		Dependent dependent;
		try{
			enrollment = enrollmentRepository.getOne(Integer.valueOf(enrolleeId));
			dependent = dependentRepository.getOne(Integer.valueOf(dependentId));

			if(enrollment.equals(dependent.getEnrollee())){
				dependentRepository.deleteById(dependentId);   
			}
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);       
	}

	public ResponseEntity<Dependent> updateDependent(int enrolleeId, int dependentId, Dependent dependent) {
		Enrollment isEnrollmentPresent = enrollmentRepository.getOne(enrolleeId);
		Optional<Dependent> isDependentPresent = dependentRepository.findById(dependentId);
		try{    
			if (isDependentPresent.isPresent()) {
				dependent.setEnrollee(isEnrollmentPresent);
				dependentRepository.save(dependent);

			}
		}  catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}   
		return new ResponseEntity<>(dependent, HttpStatus.OK);
	}
}