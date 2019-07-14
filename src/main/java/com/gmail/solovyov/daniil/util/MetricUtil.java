package com.gmail.solovyov.daniil.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class MetricUtil {

    public static final String NULL = "null";

    public static String argsToString(Object[] args) {
        return Arrays.stream(args)
                .map(i -> i == null ? NULL : i.toString())
                .collect(Collectors.joining(","));
    }
}
