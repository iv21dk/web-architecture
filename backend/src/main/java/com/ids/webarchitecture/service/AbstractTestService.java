package com.ids.webarchitecture.service;

import com.ids.webarchitecture.exception.AppLogicException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Log4j2
public abstract class AbstractTestService implements TestActionService {
    public static final int READ_AUTHORS_DELAY_MS = 2 * 60 * 1000; // 2 min
    //public static final int READ_AUTHORS_DELAY_MS = 10 * 1000; // 10 sec for debug

    @Autowired
    private TestLockService testLockService;

    private List<String> authorIds = new CopyOnWriteArrayList<>();

    abstract List<String> getAuthorIds();

    @PostConstruct
    protected void init() {
        authorIds.addAll(getAuthorIds());
    }

    @Override
    public int getAuthorsCount() {
        return authorIds.size();
    }

    @Override
    public String getRandomAuthorId() {
        if (authorIds == null || authorIds.isEmpty()) {
            throw new AppLogicException("Impossible to get random author id because list is empty");
        }
        Random rand = new Random();
        int randomIndex = rand.nextInt(authorIds.size());
        return authorIds.get(randomIndex);
    }

    @Override
    public void deleteById(String id) {
        authorIds.remove(id);
    }

    @Scheduled(fixedDelay = READ_AUTHORS_DELAY_MS)
    protected void readAllAuthorIdsScheduled() {
        if (testLockService.locked()) {
            log.info("Reading of author ids is skipped because service locked by test");
            return;
        }
        if (authorIds.size() != readAuthorsCount()) {
            authorIds.clear();
            authorIds.addAll(getAuthorIds());
        }
    }
}
