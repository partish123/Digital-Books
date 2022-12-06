package com.digitalbooks.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookException extends Exception{

    private static final long serialVersionUID = 1L;
    public static final Logger logger = LoggerFactory.getLogger(BookException.class);

    public BookException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        logger.error(errorMessage,cause);

    }

    public BookException(String errorMessage) {
        logger.error(errorMessage);
    }

}
