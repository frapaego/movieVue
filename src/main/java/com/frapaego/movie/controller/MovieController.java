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
import org.springframework.http.MediaType;
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

import com.frapaego.movie.Constants;
import com.frapaego.movie.exception.InvalidRequestException;
import com.frapaego.movie.model.MovieForm;
import com.frapaego.movie.model.ResponseMessage;
import com.frapaego.movie.service.MovieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping(value = Constants.URI_API + Constants.URI_MOVIES)
@Api(value = "MovieController", tags = "API Movies")
public class MovieController {

	private static final Logger log = LoggerFactory.getLogger(MovieController.class);

	@Inject
	private MovieService movieService;

	@RequestMapping(value = "/getAllMovies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Obtiene el listado paginado de películas.")
	@ApiResponse(code = 200, message = "Listado paginado de películas")
	public ResponseEntity<Page<MovieForm>> getAllMovies( //
			@ApiParam(name = "q", value = "Valor a buscar.") //
			@RequestParam(value = "q", required = false) final String keyword, //
			@ApiParam(name = "page", value = "Paginación.") //
			@PageableDefault(page = 0, size = 10, sort = "title", direction = Direction.DESC) final Pageable page) {

		log.debug("get all movies of q@" + keyword + ", page@" + page);

		final Page<MovieForm> movies = movieService.searchPostsByCriteria(keyword, page);

		log.debug("get movies size @" + movies.getTotalElements());

		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@RequestMapping(value = "/getMovieById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Obtiene la película indicada por id.")
	@ApiResponse(code = 200, message = "Película indicada")
	public ResponseEntity<MovieForm> getMovieById(@ApiParam(name = "id", value = "Identificador de la película.") //
	@PathVariable("id") final Long id) {
		log.debug("get movieinfo by id @" + id);

		final MovieForm movie = movieService.findMovieById(id);

		log.debug("get movie @" + movie);

		return new ResponseEntity<>(movie, HttpStatus.OK);
	}

	@RequestMapping(value = "/createMovie", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Añade una nueva película.")
	@ApiResponse(code = 201, message = "movie.created")
	public ResponseEntity<ResponseMessage> createMovie(@ApiParam(name = "movie", value = "Película a crear.") //
	@RequestBody @Valid final MovieForm movie, final BindingResult errResult) {

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

	@RequestMapping(value = "/deleteMovie/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Elimina la película indicada por id.")
	@ApiResponse(code = 202, message = "movie.deleted")
	public ResponseEntity<ResponseMessage> deleteMovie(@ApiParam(name = "id", value = "Identificador de la película.") //
	@PathVariable("id") final Long id) {
		log.debug("delete movie by id @" + id);

		movieService.delMovie(id);

		return new ResponseEntity<>(ResponseMessage.success("movie.deleted"), HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/updateMovie", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Actualiza la película indicada.")
	@ApiResponse(code = 200, message = "movie.updated")
	public ResponseEntity<ResponseMessage> updateMovie(@ApiParam(name = "movie", value = "Película a crear.") //
	@RequestBody @Valid final MovieForm movie, final BindingResult errResult) {
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
