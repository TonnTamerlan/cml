package com.cmlteam.geoip.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The class presents an exception that is thrown if the request is not valid
 * 
 * @author Alexey Kopylov
 *
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class NotValidRequestException extends RuntimeException {

    public NotValidRequestException() {
    }

    public NotValidRequestException(String message) {
        super(message);
    }

    public NotValidRequestException(Throwable cause) {
        super(cause);
    }

    public NotValidRequestException(String message, Throwable cause) {
        super(message, cause);
    }


}
