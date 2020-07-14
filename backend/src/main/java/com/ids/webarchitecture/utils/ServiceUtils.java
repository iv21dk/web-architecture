package com.ids.webarchitecture.utils;

import com.ids.webarchitecture.exception.NotFoundException;

import java.util.Collection;
import java.util.Optional;

public class ServiceUtils {

    public static <T> T checkFound(Optional<T> object) {
        if (object.isEmpty()) {
            throw new NotFoundException(String.format("entity is not found"));
        }
        return object.get();
    }

    public static <T> T checkFound(T object) {
        if (object == null) {
            throw new NotFoundException(String.format("entity is not found"));
        }
        return object;
    }

    public static Collection checkFound(Collection collection) {
        if (collection.isEmpty()) {
            throw new NotFoundException(String.format("entities list is not found"));
        }
        return collection;
    }
}
