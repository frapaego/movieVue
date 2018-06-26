package com.frapaego.movie.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.frapaego.movie.exception.InvalidRequestException;
import com.frapaego.movie.model.MovieForm;
import com.frapaego.movie.model.ResponseMessage;
import com.frapaego.movie.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private static final Logger log = LoggerFactory.getLogger(MovieController.class);

	@Inject
	private MovieService movieService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Page<MovieForm>> list(@RequestParam(value = "q", required = false) final String keyword, //
			@PageableDefault(page = 0, size = 10, sort = "title", direction = Direction.DESC) final Pageable page) {

		log.debug("get all movies of q@" + keyword + ", page@" + page);

		final Page<MovieForm> movies = movieService.searchPostsByCriteria(keyword, page);

		log.debug("get movies size @" + movies.getTotalElements());

		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MovieForm> viewMovie(@PathVariable("id") final Long id) {
		log.debug("get movieinfo by id @" + id);

		final MovieForm movie = movieService.findMovieById(id);

		log.debug("get movie @" + movie);

		return new ResponseEntity<>(movie, HttpStatus.OK);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> newMovie(@RequestBody @Valid final MovieForm movie,
			final BindingResult errResult) {

		log.debug("create a new movie");
		if (errResult.hasErrors()) {
			throw new InvalidRequestException(errResult);
		}

		final MovieForm saved = movieService.save(movie);

		log.debug("saved movie id is @" + saved.getId());

		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentContextPath().path("/movie/{id}")
				.buildAndExpand(saved.getId()).toUri());
		return new ResponseEntity<>(ResponseMessage.success("movie.created"), headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<ResponseMessage> remove(@PathVariable("id") final Long id) {
		log.debug("delete movie by id @" + id);

		movieService.delMovie(id);

		return new ResponseEntity<>(ResponseMessage.success("movie.deleted"), HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> update(@RequestBody @Valid final MovieForm movie,
			final BindingResult errResult) {
		log.debug("update a movie");
		if (errResult.hasErrors()) {
			throw new InvalidRequestException(errResult);
		}

		final MovieForm saved = movieService.editMovie(movie.getId(), movie);

		log.debug("saved movie id is @" + saved.getId());

		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentContextPath().path("/movie/{id}")
				.buildAndExpand(saved.getId()).toUri());
		return new ResponseEntity<>(ResponseMessage.success("movie.updated"), headers, HttpStatus.OK);
	}
}
