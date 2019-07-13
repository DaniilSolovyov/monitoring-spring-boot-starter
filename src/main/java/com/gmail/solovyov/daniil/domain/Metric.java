package com.gmail.solovyov.daniil.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Metric {
    private Integer eventId;
    private Long value;
    private String parameters;
    private Timestamp eventTimestamp;
}
