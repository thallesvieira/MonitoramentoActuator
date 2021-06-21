package com.agenda.agenda.domain.util;

public class Longs {

    public static Boolean isNullOrZero(Long l) {
        if (l == null || l.compareTo(0L) == 0)
            return true;

        return false;
    }
}
