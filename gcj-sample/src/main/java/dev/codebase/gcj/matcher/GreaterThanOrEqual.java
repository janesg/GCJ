package dev.codebase.gcj.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class GreaterThanOrEqual<T extends Comparable<T>> extends BaseMatcher<T> {

    private final Comparable<T> expectedValue;
    
    public GreaterThanOrEqual(Comparable<T> expectedValue) {
        super();
        this.expectedValue = expectedValue;
    }

    @Factory
    public static <T extends Comparable<T>> Matcher<T> greaterThanOrEqual(T t) {
        return new GreaterThanOrEqual(t);
    }
    
    @Override
    public boolean matches(Object obj) {
        int compare = expectedValue.compareTo((T) obj);
        return compare < 1;
    }

    @Override
    public void describeTo(Description desc) {
        desc.appendText(" greater than or equal (>=) " + expectedValue);
    }

}
