
package com.frapaego.movie.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Long id;

	public ResourceNotFoundException(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
