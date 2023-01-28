package org.psu.lab567.pojo;

import java.util.List;

import org.psu.lab567.utils.ValidationViolation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidationErrorResponse {
    private final List<ValidationViolation> violations;
}
