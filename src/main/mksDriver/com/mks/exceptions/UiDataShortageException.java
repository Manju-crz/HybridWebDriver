package com.mks.exceptions;

public class UiDataShortageException extends RuntimeException{

	  private static final long serialVersionUID = -6338397732180408157L;

	  public UiDataShortageException(String message) {
	    super(message);
	  }

	  public UiDataShortageException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  public UiDataShortageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }

	  public UiDataShortageException(Throwable cause) {
	    super(cause);
	  }
}
