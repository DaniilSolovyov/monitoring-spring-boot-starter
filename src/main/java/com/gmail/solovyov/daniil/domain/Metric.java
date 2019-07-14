package com.gmail.solovyov.daniil.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Metric {
    private Integer id;
    private String eventName;
    private Long value;
    private String parameters;
    private Timestamp eventTimestamp;

    public Metric(String eventName, Long value, String parameters, Timestamp eventTimestamp) {
        this.eventName = eventName;
        this.value = value;
        this.parameters = parameters;
        this.eventTimestamp = eventTimestamp;
    }
}
