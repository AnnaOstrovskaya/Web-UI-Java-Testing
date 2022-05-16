package com.geekbrains;

import com.geekbrains.lesson4.Functions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJExamplesTests {

    @Test
    void assertJExample() {
        assertThat(Functions.isPrime(5)).isTrue();
        List<String> strings = new ArrayList<>(Arrays.asList("test", "test1", "test2"));
        assertThat(strings).containsAnyOf("testa", "test1");
    }

}
