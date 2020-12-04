package com.enrollment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enrollment.entity.Dependent;
import com.enrollment.entity.Enrollment;
import com.enrollment.service.EnrollmentService;

@RestController
@RequestMapping("/enrollees")

public class EnrollmentController {
	
	
	@Autowired
    private EnrollmentService enrollmentService;

	
	/***
     * Add a new enrollee
     * 
     */
	
	@RequestMapping(value = "/addEnrollment", method = RequestMethod.POST)
	public ResponseEntity<Enrollment> addEnrollment(@RequestBody @Valid Enrollment enrollment) {
		return enrollmentService.addEnrollment(enrollment);
	}
	
	/***
	 * Retrieve all enrollees from datasource.
	 * 
	 * @return A list of all available enrollees.
	 */
	@RequestMapping(value = "/getEnrollments", method = RequestMethod.GET)
	public ResponseEntity<List<Enrollment>> getEnrollments() {
		return enrollmentService.getAllEnrollees();
	}

	/***
	 * Retrieve a enrollee by enrollee id.
	 */
	@RequestMapping(value = "/getEnrollmentById/{enrollmentId}", method = RequestMethod.GET)
	public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable("enrollmentId") int enrollmentId) {
		return enrollmentService.getEnrollmentById(enrollmentId);
	}
	
	/***
     * update an exisitng enrollee
     * 
     */

    @PutMapping("/updateEnrollment/{enrolleeId}")
    public ResponseEntity<Enrollment> updateEnrollee(@PathVariable("enrolleeId") int enrolleeId, @RequestBody Enrollment enrollment) {
          return enrollmentService.update(enrolleeId,enrollment);
      }

    /***
     * Delete an enrollee by id.
     * 
     * Remove an enrollee, as well as dependents belonging to that enrollee.
     *
     */
    @RequestMapping(value = "/removeEnrollment/{enrollmentId}", method = RequestMethod.GET)
	public ResponseEntity<Void> removeEnrollment(@PathVariable("enrollmentId") int enrollmentId) {
    	return enrollmentService.removeEnrollment(enrollmentId);
	}
    
   	/***
     * Add a new dependent to a enrollee by id.
     * 
     */
	
	@RequestMapping(value = "/addDependent/{enrollmentId}", method = RequestMethod.POST)
	public ResponseEntity<Dependent> addDependentByEnrollmentId(@PathVariable("enrollmentId") int enrollmentId,@RequestBody Dependent dependent)  {
		return enrollmentService.addDependent(enrollmentId,dependent);
	}
	
	
	
	/***
     * Update a Depedent
     * 
     */

    @PutMapping("/updateDepedent/{enrolleeId}/{dependentId}")
    public ResponseEntity<Dependent> updateDependent(@PathVariable("enrolleeId") int enrolleeId,
    				@PathVariable("dependentId") int dependentId, @RequestBody Dependent dependent) {
    	return enrollmentService.updateDependent(enrolleeId, dependentId, dependent);
      }

    /**
     * Delete a dependent by id under enrollee.
     *
     */
    @RequestMapping(value = "/removeDepedent/{enrollmentId}/{dependentId}", method = RequestMethod.GET)
	public ResponseEntity<Void> removeDepedent(@PathVariable("enrollmentId") int enrollmentId,@PathVariable("dependentId") int dependentId) {
    	return enrollmentService.deleteDependentByEnrolleeId(enrollmentId,dependentId);
	}
    
}


















