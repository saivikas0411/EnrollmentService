package com.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enrollment.entity.Dependent;

@Repository
public interface DependentRepository extends JpaRepository<Dependent, Integer>{

}
