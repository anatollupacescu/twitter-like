package com.test;

import com.test.core.Entry;
import com.test.core.EntryFormatter;
import com.test.core.TimelinePresenter;
import com.test.core.User;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class TwitterlikeTest {

    @Test
    public void canPostToOwnFeedAndReadEntries() {
        TwitterlikeUser bob = new TwitterlikeUser("Bob");
        String statusUpdate1 = "hello world";
        bob.publish(statusUpdate1);
        String statusUpdate2 = "second update";
        bob.publish(statusUpdate2);
        List<Entry> bobsTimeline = bob.getTimeline();
        assertThat(bobsTimeline.size(), is(2));
        Iterator<Entry> iterator = bobsTimeline.iterator();
        Entry entry = iterator.next();
        assertThat(entry.getStatus(), is(statusUpdate1));
        assertThat(entry.getDate(), is(notNullValue()));
        entry = iterator.next();
        assertThat(entry.getStatus(), is(statusUpdate2));
    }

    @Test
    public void canFollowAndReadMergedEntries() {
        TwitterlikeUser alice = new TwitterlikeUser("Alice");
        String aliceStatusUpdate = "hi bob";
        alice.publish(aliceStatusUpdate);
        TwitterlikeUser bob = new TwitterlikeUser("Bob");
        String bobStatusUpdate = "what is up alice";
        bob.publish(bobStatusUpdate);
        bob.follows(alice);
        List<Entry> bobsTimeline = bob.getWall();
        Set<User> bobsSubscription = bob.getSubscription();
        assertThat(bobsSubscription.size(), is(equalTo(1)));
        assertThat(bobsTimeline.size(), is(equalTo(2)));
    }

    @Test
    public void timelinePresenterSortedByDatesAndFormatsOK() {
        TwitterlikeUser alice = new TwitterlikeUser("Alice");
        String aliceStatusUpdate = "hi bob";
        alice.publish(aliceStatusUpdate);
        TwitterlikeUser bob = new TwitterlikeUser("Bob");
        String bobStatusUpdate = "what is up alice";
        bob.publish(bobStatusUpdate);
        bob.follows(alice);
        List<Entry> bobsTimeline = bob.getWall();
        EntryFormatter formatter = new WallEntryFormatter();
        TimelinePresenter<PrintStream> presenter = new SortedPrintStreamTimelinePresenter(bobsTimeline, formatter);
        PrintStream printStream = mock(PrintStream.class);
        presenter.present(printStream);
        verify(printStream, times(1)).println("Bob - what is up alice (0 minutes ago)");
        verify(printStream, times(1)).println("Alice - hi bob (0 minutes ago)");
        verify(printStream, times(1)).flush();
    }
}
