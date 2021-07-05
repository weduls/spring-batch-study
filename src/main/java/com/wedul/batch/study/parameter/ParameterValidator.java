package com.wedul.batch.study.parameter;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

public class ParameterValidator implements JobParametersValidator {

    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        String size = parameters.getString("size");

        if (!StringUtils.hasText(size)) {
            throw new JobParametersInvalidException("size is missing");
        }
        else if (!StringUtils.endsWithIgnoreCase(size, "kg")) {
            throw new JobParametersInvalidException("input value does not use the kg");
        }
    }
}
