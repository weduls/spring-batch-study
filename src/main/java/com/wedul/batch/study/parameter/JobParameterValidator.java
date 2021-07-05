package com.wedul.batch.study.parameter;

import org.springframework.batch.core.job.DefaultJobParametersValidator;

public class JobParameterValidator extends DefaultJobParametersValidator {

    public JobParameterValidator() {
        super(new String[] {"wedul"}, new String[] {"name", "size"});
    }

}
