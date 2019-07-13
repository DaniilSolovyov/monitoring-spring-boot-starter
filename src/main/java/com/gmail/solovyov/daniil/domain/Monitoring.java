package com.gmail.solovyov.daniil.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Monitoring {
    private Integer id;
    private String eventName;
    private Long value;
    private String parameters;
    private Timestamp eventTimestamp;
}
