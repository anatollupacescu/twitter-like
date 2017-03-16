package com.test.core;

import java.util.Collection;

public abstract class TimelinePresenter<T> {

    protected final EntryFormatter formatter;
    protected final Collection<Entry> timeline;

    protected TimelinePresenter(Collection<Entry> timeline, EntryFormatter formatter) {
        this.timeline = timeline;
        this.formatter = formatter;
    }

    public abstract void present(T out);
}
