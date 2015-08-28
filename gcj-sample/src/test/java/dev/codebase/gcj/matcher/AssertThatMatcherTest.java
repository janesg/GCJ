package dev.codebase.gcj.matcher;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static dev.codebase.gcj.matcher.GreaterThanOrEqual.greaterThanOrEqual;

import org.junit.Test;

public class AssertThatMatcherTest {

    @Test
    public void greaterThanOrEqualMatcher() {
        
        int actualGoalsScored = 3;
        int expectedGoalsScored = 2;
        
        assertThat(actualGoalsScored, greaterThanOrEqual(expectedGoalsScored));
        
        actualGoalsScored = 2;
        assertThat(actualGoalsScored, greaterThanOrEqual(expectedGoalsScored));
        
        float actualFloat = 3.14f;
        float expectedFloat = 1.59f;

        assertThat(actualFloat, greaterThanOrEqual(expectedFloat));
        
        actualFloat = 1.59f;
        assertThat(actualFloat, greaterThanOrEqual(expectedFloat));
        
        String actualStr = "Bob Ajob";
        String expectedStr = "Bob Ajib";

        assertThat(actualStr, greaterThanOrEqual(expectedStr));
        
        actualStr = "Bob Ajib";
        assertThat(actualStr, greaterThanOrEqual(expectedStr));
              
        actualStr = "Bob Ajab";

        Throwable th = null;
        
        try {
            assertThat(actualStr, greaterThanOrEqual(expectedStr));
            fail("AssertionError should have been thrown");
        } catch (AssertionError e) {
            th = e;
        }
        
        assertNotNull(th);
        assertThat(th.getMessage(), both(containsString("greater than or equal (>=) Bob Ajib")).
                                    and(containsString("was \"Bob Ajab\"")));
    }

}
