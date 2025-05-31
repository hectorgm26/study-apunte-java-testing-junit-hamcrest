package org.studyjunit;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class MatcherPersonalizado extends TypeSafeMatcher<Integer> {

    // Como atributo, se define un Matcher que espera un Integer
    private Matcher<Integer> expectedValue;

    // Constructor que recibe un Matcher personalizado
    public MatcherPersonalizado(Matcher<Integer> matcher) {
        this.expectedValue = matcher;
    }

    @Override
    protected boolean matchesSafely(Integer item) {
        return expectedValue.matches(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendDescriptionOf(expectedValue);
    }

    public static MatcherPersonalizado matchesInteger(Matcher<Integer> matcher) {
        return new MatcherPersonalizado(matcher);
    }
}
