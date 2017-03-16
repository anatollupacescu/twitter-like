package com.test;

import com.test.core.Entry;
import com.test.core.EntryFormatter;
import com.test.core.TimelinePresenter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortedPrintStreamTimelinePresenter extends TimelinePresenter<PrintStream> {

    public SortedPrintStreamTimelinePresenter(List<Entry> timeline, EntryFormatter formatter) {
        super(timeline, formatter);
    }

    @Override
    public void present(PrintStream out) {
        List<Entry> sortedTimeline = new ArrayList<>(timeline);
        sortedTimeline.sort(Comparator.comparing(Entry::getDate).reversed());
        sortedTimeline.stream().map(formatter::format).forEach(out::println);
        out.flush();
    }
}
