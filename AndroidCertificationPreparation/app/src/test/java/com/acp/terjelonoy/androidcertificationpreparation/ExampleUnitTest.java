package com.acp.terjelonoy.androidcertificationpreparation;

import com.acp.terjelonoy.androidcertificationpreparation.helpers.Calculator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExampleUnitTest {

    @Test
    public void testCalculation() {
        assertThat(Calculator.addition(2, 2), is(4));
    }
}