package com.frapaego.movie;

public final class Constants {

	/**
	 * prefix of REST API
	 */
	public static final String URI_API = "/api";

	public static final String URI_MOVIES = "/movies";

	public static final String URI_COMMENTS = "/comments";

	private Constants() {
		throw new InstantiationError("No puede instanciar esta clase");
	}

}
