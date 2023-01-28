package org.psu.lab567.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationViolation {
    private final String fieldName;
    private final String message;
}
