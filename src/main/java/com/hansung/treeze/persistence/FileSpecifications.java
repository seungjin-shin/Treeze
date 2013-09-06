package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Course_;
import com.hansung.treeze.model.UploadedFile;
import com.hansung.treeze.model.UploadedFile_;

public class FileSpecifications {


	public static Specification<UploadedFile> isfileName(final String fileName){
		return new Specification<UploadedFile>(){
			@Override
			public Predicate toPredicate(Root<UploadedFile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String>get(UploadedFile_.fileName), fileName);
			}
		};
	}

	public static Specification<UploadedFile> isVersion(final String version, final String userType){
		return new Specification<UploadedFile>(){
			@Override
			public Predicate toPredicate(Root<UploadedFile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				return cb.and(cb.equal(root.<String>get(UploadedFile_.version), version),
					      cb.equal(root.<String>get(UploadedFile_.userType), userType));
			}
		};
	}

	public static Specification<UploadedFile> isClassId(final Long classId){
		return new Specification<UploadedFile>(){
			@Override
			public Predicate toPredicate(Root<UploadedFile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Long>get(UploadedFile_.classId), classId);
			}
		};
	}
	
	private static String getLikePattern(final String searchTerm) {
		StringBuilder pattern = new StringBuilder();
		pattern.append("%");
		pattern.append(searchTerm);
		pattern.append("%");
		return pattern.toString();
	}
}
