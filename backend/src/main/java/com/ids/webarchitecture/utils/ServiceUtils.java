package com.ids.webarchitecture.utils;

import com.ids.webarchitecture.exception.NotFoundException;

import java.util.Optional;

public class ServiceUtils {

    public static <T> T checkFound(Optional<T> object) {
        if (object.isEmpty()) {
            throw new NotFoundException(String.format("entity not found"));
        }
        return object.get();
    }

    public static <T> T checkFound(T object) {
        if (object == null) {
            throw new NotFoundException(String.format("entity not found"));
        }
        return object;
    }
}
