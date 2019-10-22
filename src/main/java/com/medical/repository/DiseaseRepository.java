/**
 * 
 */
package com.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.entity.Disease;

/**
 * @author Shiva
 *
 */
public interface DiseaseRepository extends JpaRepository<Disease, Integer> {

}
