package com.squeezecorp.pokermgm.exception.models;

public class SessionNotFoundException extends EntityNotFoundException{

    private static final long serialVersionUID = 1L;

    public SessionNotFoundException(String msg) {
        super(msg);
    }

    public SessionNotFoundException(Long id) {
        this(String.format("Cannot find a Session with id %d", id));
    }
}
