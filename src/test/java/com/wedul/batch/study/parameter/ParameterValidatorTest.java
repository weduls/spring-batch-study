package com.wedul.batch.study.parameter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@SpringBootTest(args = "--spring.batch.job.names=parameterJob", classes = {
//    ParameterJob.class,
//    TestBatchConfig.class,
//    DatasourceConfig.class
//}) // (2)
class ParameterValidatorTest {

    private JobParameterValidator jobParameterValidator;
    private ParameterValidator parameterValidator;

    @BeforeEach
    public void setup() {
        this.jobParameterValidator = new JobParameterValidator();
        this.parameterValidator = new ParameterValidator();
    }

    @Test
    @DisplayName("job parameter validator 필수값 없을 시 에러 확인")
    void job_parameter_validator_not_have_required_field_test() {
        // given
        JobParameters parameters= new JobParametersBuilder()
            .addString("name", "123")
            .toJobParameters();

        // when
        // then
        assertThrows(JobParametersInvalidException.class, () -> jobParameterValidator.validate(parameters));
    }

    @Test
    @DisplayName("job parameter validator 필수값 존재 시 동작 확인")
    void job_parameter_validator_have_required_field_test() {
        // given
        JobParameters parameters= new JobParametersBuilder()
            .addString("name", "123")
            .addString("wedul", "chul")
            .toJobParameters();

        // when
        // then
        assertDoesNotThrow(() -> jobParameterValidator.validate(parameters));
    }

    @Test
    @DisplayName("parameterValidator 필수값 없을 시 동작 확인")
    void parameter_validator_have_not_required_field_test(){
        // given
        JobParameters parameters= new JobParametersBuilder()
            .addString("name", "123")
            .addString("wedul", "chul")
            .toJobParameters();

        // when
        // then
        assertThrows(JobParametersInvalidException.class, () -> parameterValidator.validate(parameters));
    }

    @Test
    @DisplayName("parameterValidator 필수값 존재하나 조건 불만족 시 validate")
    void parameter_validator_have_required_field_but_not_satisfied_test() {
        // given
        JobParameters parameters= new JobParametersBuilder()
            .addString("size", "123")
            .toJobParameters();

        // when
        // then
        assertThrows(JobParametersInvalidException.class, () -> parameterValidator.validate(parameters));
    }

    @Test
    @DisplayName("parameterValidator 정상 조건 테스트")
    void parameter_validator_have_required_field_and_satisfied_test() {
        // given
        JobParameters parameters= new JobParametersBuilder()
            .addString("size", "123kg")
            .toJobParameters();

        // when
        // then
        assertDoesNotThrow(() -> parameterValidator.validate(parameters));
    }

}
