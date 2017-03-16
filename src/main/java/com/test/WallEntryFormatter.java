package com.test;

import com.test.core.Entry;

import java.util.Date;

public class WallEntryFormatter extends TwitterlikeTimedEntryFormatter {

    private final String mask = "%s - %s (%s %s ago)";

    @Override
    public String format(Entry entry) {
        long minuteCount = getDifferenceInMinutes(entry.getDate(), new Date());
        String minuteText = minuteCount == 1 ? "minute" : "minutes";
        return String.format(mask, entry.getUser().getName(), entry.getStatus(), minuteCount, minuteText);
    }
}
