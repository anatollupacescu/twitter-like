package com.test;

import com.test.core.EntryFormatter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

abstract class TwitterlikeTimedEntryFormatter implements EntryFormatter {

    long getDifferenceInMinutes(Date entryDate, Date now) {
        long duration  = now.getTime() - entryDate.getTime();
        if(duration < 0) {
            throw new IllegalStateException("Entry has a future date");
        }
        return TimeUnit.MILLISECONDS.toMinutes(duration);
    }
}
