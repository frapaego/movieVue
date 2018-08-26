package com.frapaego.movie.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseMessage {

	public enum Type {
		success, warning, danger, info;
	}

	private final Type type;
	private final String text;
	private String code;

	private List<Error> errors = new ArrayList<Error>();

	public ResponseMessage(final Type type, final String text) {
		this.type = type;
		this.text = text;
	}

	public ResponseMessage(final Type type, final String code, final String message) {
		this.type = type;
		this.code = code;
		this.text = message;
	}

	public String getText() {
		return text;
	}

	public Type getType() {
		return type;
	}

	public String getCode() {
		return code;
	}

	public static ResponseMessage success(final String text) {
		return new ResponseMessage(Type.success, text);
	}

	public static ResponseMessage warning(final String text) {
		return new ResponseMessage(Type.warning, text);
	}

	public static ResponseMessage danger(final String text) {
		return new ResponseMessage(Type.danger, text);
	}

	public static ResponseMessage info(final String text) {
		return new ResponseMessage(Type.info, text);
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(final List<Error> errors) {
		this.errors = errors;
	}

	public void addError(final String field, final String code, final String message) {
		this.errors.add(new Error(field, code, message));
	}

	class Error {

		private final String code;
		private final String message;
		private final String field;

		private Error(final String field, final String code, final String message) {
			this.field = field;
			this.code = code;
			this.message = message;
		}

		public String getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

		public String getField() {
			return field;
		}

	}

}
