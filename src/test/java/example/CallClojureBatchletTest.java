package example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import example.CallClojureBatchlet;
import output.ConsoleSnatcher;

public class CallClojureBatchletTest {
    private final static CallClojureBatchlet TARGET = new CallClojureBatchlet();
    private ConsoleSnatcher stdout = ConsoleSnatcher.getlnstance();

    @Before
    public void setUp() {
        stdout.snatch();
    }

    @After
    public void tearDown() {
        stdout.release();
    }

    @Test
    public void callTest_args1() {
        TARGET.callClojure("hello-world", "hello", "args1");
        String[] outLines = stdout.getOutput().split("\\R");
        String actual = outLines[outLines.length - 1];
        String expected = "args1";
        assertThat(actual, is(expected));
    }

    @Test
    public void callTest_args0() {
        TARGET.callClojure("hello-world", "hello");
        String[] outLines = stdout.getOutput().split("\\R");
        String actual = outLines[outLines.length - 1];
        String expected = "Hello World";
        assertThat(actual, is(expected));
    }

}
