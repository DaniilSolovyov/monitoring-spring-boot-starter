package com.gmail.solovyov.daniil.metric;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Metric {
    private int id;
    private String eventName;
    private long value;
    private String parameters;
    private Timestamp eventTimestamp;
}
