package com.epam.jmp;

import java.time.OffsetDateTime;

/**
 * @author Tim Ryzhov
 */
public class ServerTime {
    public static String getLocalTime(){
        String time = OffsetDateTime.now().toString();
        return time;
    }
}
