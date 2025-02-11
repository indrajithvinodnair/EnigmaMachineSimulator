package com.wehrmacht.enigma.logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SimpleMessageFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return record.getMessage() + "\n";
    }
}
