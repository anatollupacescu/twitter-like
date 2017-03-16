package com.test.core;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class User {

    private final String name;

    protected final Collection<Entry> timeline;
    protected final Collection<User> subscription;

    protected User(String name, Collection<Entry> timeline, Collection<User> subscription) {
        this.name = name;
        this.timeline = timeline;
        this.subscription = subscription;
    }

    public void publish(String s) {
        Entry entry = new Entry(this, s, new Date());
        this.timeline.add(entry);
    }

    public void follows(User alice) {
        this.subscription.add(alice);
    }

    public String getName() {
        return name;
    }

    public Collection<Entry> getTimeline() {
        return timeline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(timeline, user.timeline) &&
                Objects.equals(subscription, user.subscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, timeline, subscription);
    }
}
