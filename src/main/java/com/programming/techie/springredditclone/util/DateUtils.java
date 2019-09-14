package com.programming.techie.springredditclone.util;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.Instant;

import static java.time.Duration.between;

@UtilityClass
public class DateUtils {

    public static String calcDuration(Instant createdDate) {
        Duration between = between(createdDate, Instant.now());
        if (between.toMinutes() < 1) {
            return between.getSeconds() + " seconds ago";
        } else if (between.toMinutes() < 60) {
            return between.toMinutes() + " minutes ago";
        }
        return between.toHours() + " hours ago";
    }
}
