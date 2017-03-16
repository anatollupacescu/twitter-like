package com.test;

import com.test.core.Entry;
import com.test.core.User;

import java.util.*;

public class TwitterlikeUser extends User {

    public TwitterlikeUser(String name) {
        super(name, new ArrayList<>(), new HashSet<>());
    }

    public List<Entry> getWall() {
        final List<Entry> allEntries = new ArrayList<>(timeline);
        subscription.stream().map(User::getTimeline).forEach(allEntries::addAll);
        return allEntries;
    }

    public List<Entry> getTimeline() {
        return Collections.unmodifiableList((List<Entry>) timeline);
    }

    public Set<User> getSubscription() {
        return Collections.unmodifiableSet((Set<User>) subscription);
    }
}
