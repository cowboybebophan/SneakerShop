package com.ascending.exception;

import com.ascending.model.User;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class AuthenticationException extends AppBaseException {
    private User user;

    public AuthenticationException() {
        super(HttpStatus.BAD_REQUEST.value(), "Bad request.");
    }

    public AuthenticationException(String message) {
        this();
        if (message != null) this.message = message;
    }

    public AuthenticationException(String message, User user) {
        this(message);
        this.user = user;
    }

    @Override
    public Map<String, Object> getFormattedMessage() {
        Map<String, Object> map = super.getFormattedMessage();
        if (user != null) map.put("user", user);
        return map;
    }
}
