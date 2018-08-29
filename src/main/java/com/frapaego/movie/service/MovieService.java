package com.frapaego.movie.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.frapaego.movie.DTOUtils;
import com.frapaego.movie.domain.Movie;
import com.frapaego.movie.exception.ResourceNotFoundException;
import com.frapaego.movie.model.MovieForm;
import com.frapaego.movie.repository.MovieRepository;
import com.frapaego.movie.repository.MovieSpecifications;

@Service
@Transactional
public class MovieService {

	private static final Logger log = LoggerFactory.getLogger(MovieService.class);

	@Inject
	private MovieRepository movieMapper;

	public Page<MovieForm> searchPostsByCriteria(final String q, final Pageable page) {

		log.debug("search movies by keyword@" + q + ", page @" + page);

		final Page<Movie> movies = movieMapper.findAll(MovieSpecifications.filterByKeyword(q), page);

		log.debug("get movies size @" + movies.getTotalElements());

		return DTOUtils.mapPage(movies, MovieForm.class);
	}

	public MovieForm save(final MovieForm form) {
		log.debug("save movie @" + form);

		final Movie movie = DTOUtils.map(form, Movie.class);

		final Movie saved = movieMapper.save(movie);

		log.debug("saved movie is @" + saved);

		return DTOUtils.map(saved, MovieForm.class);

	}

	public MovieForm findMovieById(final Long id) {
		Assert.notNull(id, "movie id can not be null");

		log.debug("find movie by id@" + id);

		final Movie movie = movieMapper.findOne(id);

		if (movie == null) {
			throw new ResourceNotFoundException(id);
		}

		return DTOUtils.map(movie, MovieForm.class);
	}

	public boolean delMovie(final Long id) {
		Assert.notNull(id, "movie id can not be null");

		log.debug("find movie by id@" + id);

		final Movie movie = movieMapper.findOne(id);

		if (movie == null) {
			throw new ResourceNotFoundException(id);
		}

		movieMapper.delete(movie);

		return true;
	}

	public MovieForm editMovie(final Long id, final MovieForm form) {
		Assert.notNull(id, "post id can not be null");

		log.debug("update post @" + form);

		final Movie movie = movieMapper.findOne(id);
		DTOUtils.mapTo(form, movie);

		final Movie saved = movieMapper.save(movie);

		log.debug("updated post@" + saved);

		return DTOUtils.map(saved, MovieForm.class);
	}
}
