package com.ids.webarchitecture.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractTestService implements TestActionService {
    Logger log = LoggerFactory.getLogger(AbstractTestService.class);
    public static final int READ_AUTHORS_DELAY_MS = 2 * 60 * 1000; // 2 min
    //public static final int READ_AUTHORS_DELAY_MS = 10 * 1000; // 10 sec for debug

    @Autowired
    private TestLockService testLockService;

    private List<String> authorIds = new CopyOnWriteArrayList<>();

    abstract List<String> getAuthorIds();

    abstract long getAuthorsCount();

    @PostConstruct
    protected void init() {
        authorIds.addAll(getAuthorIds());
    }

    @Override
    public int getReadedAuthorsCount() {
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

    @Scheduled(fixedDelay = READ_AUTHORS_DELAY_MS)
    protected void readAllAuthorIdsScheduled() {
        if (testLockService.locked()) {
            log.info("Reading of author ids is skipped because service locked by test");
            return;
        }
        if (authorIds.size() != getAuthorsCount()) {
            authorIds.clear();
            authorIds.addAll(getAuthorIds());
        }
    }
}
