package com.test;

import com.test.core.Entry;
import com.test.core.User;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TwitterlikeTimedEntryFormatterTest {

    private final TimelineEntryFormatter formatter = new TimelineEntryFormatter();

    @Test
    public void formatWorksCorrectlyFor0Minutes() throws Exception {
        User tom = new TwitterlikeUser("Tom");
        Entry entry = new Entry(tom, "status", new Date());
        String formattedRow = formatter.format(entry);
        assertThat(formattedRow, is("status (0 minutes ago)"));
    }

    @Test
    public void formatWorksCorrectlyFor1Minute() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        User tom = new TwitterlikeUser("Tom");
        Entry entry = new Entry(tom, "status one minute old", new Date(currentTimeMillis - 60 * 1000));
        String formattedRow = formatter.format(entry);
        assertThat(formattedRow, is("status one minute old (1 minute ago)"));
    }

    @Test
    public void formatWorksCorrectlyForOlderStatusMessages() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        User tom = new TwitterlikeUser("Tom");
        Entry entry = new Entry(tom, "status few minutes old", new Date(currentTimeMillis - 500 * 1000));
        String formattedRow = formatter.format(entry);
        assertThat(formattedRow, is("status few minutes old (8 minutes ago)"));
    }

    @Test
    public void wallFormattingWorksOK() {
        WallEntryFormatter wallEntryFormatter = new WallEntryFormatter();
        User tom = new TwitterlikeUser("Tom");
        Entry entry = new Entry(tom, "status", new Date());
        String formattedRow = wallEntryFormatter.format(entry);
        assertThat(formattedRow, is("Tom - status (0 minutes ago)"));
    }
}
