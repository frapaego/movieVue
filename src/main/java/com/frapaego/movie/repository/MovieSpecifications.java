package com.frapaego.movie.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.frapaego.movie.domain.Movie;
import com.frapaego.movie.domain.Movie_;

public final class MovieSpecifications {

	private MovieSpecifications() {
		throw new InstantiationError("No puede instanciar esta clase");
	}

	public static Specification<Movie> filterByKeyword(final String keyword) {
		return (final Root<Movie> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) -> {
			final List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.hasText(keyword)) {
				predicates.add(cb.or(cb.like(root.get(Movie_.title), "%" + keyword + "%")));
			}

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

}
