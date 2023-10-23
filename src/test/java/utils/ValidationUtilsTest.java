package utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ValidationUtilsTest {

    @Test
    void 문자열_숫자_검증() {
        assertThat(ValidationUtils.isNumber("number")).isFalse();
        assertThat(ValidationUtils.isNumber("10s")).isFalse();
        assertThat(ValidationUtils.isNumber("-1")).isTrue();
        assertThat(ValidationUtils.isNumber("0")).isTrue();
        assertThat(ValidationUtils.isNumber("1")).isTrue();
    }

    @Test
    void 숫자_1_9_검증() {
        assertThat(ValidationUtils.isSingleNumber(0)).isFalse();
        assertThat(ValidationUtils.isSingleNumber(1)).isTrue();
        assertThat(ValidationUtils.isSingleNumber(9)).isTrue();
        assertThat(ValidationUtils.isSingleNumber(10)).isFalse();
    }
}
