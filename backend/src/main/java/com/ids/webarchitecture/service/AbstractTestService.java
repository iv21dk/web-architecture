package com.ids.webarchitecture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class AbstractTestService implements TestActionService {

    protected List<String> authorIds = new ArrayList<>();

//    protected List<String> getAuthorIds() {
//        return authorIds;
//    }

    @Override
    public long getAuthorsCount() {
        return authorIds.size();
    }

    public Optional<String> getRandomAuthorId() {
        if (authorIds == null || authorIds.isEmpty()) {
            return Optional.empty();
        }
        Random rand = new Random();
        int randomIndex = rand.nextInt(authorIds.size());
        return Optional.of(authorIds.get(randomIndex));
    }

}
