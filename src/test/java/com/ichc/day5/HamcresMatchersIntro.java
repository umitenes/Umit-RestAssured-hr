package com.ichc.day5;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class HamcresMatchersIntro {
    @DisplayName("Assertion with numbers")
    @Test
    public void simpleTest(){
        assertThat(5+5, is(10));
        assertThat(5+5,equalTo(10));
        assertThat(5+5,is(equalTo(10)));

        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        assertThat(5+5,is(greaterThan(9)));


    }
    @DisplayName("Assertion with strings")
    @Test
    public void simpleTest1(){
        String text="Learning Hamcrest";
        assertThat(text,is("Learning Hamcrest"));
        assertThat(text,equalTo("Learning Hamcrest"));
        assertThat(text,is(equalTo("Learning Hamcrest")));

        assertThat(text,startsWith("Learning"));
        assertThat(text,startsWithIgnoringCase("learn"));

        assertThat(text, containsStringIgnoringCase("learning"));

        String str="  ";
        assertThat(str,blankString());
        assertThat(str.trim(),emptyString());

    }
    @DisplayName("Hamcrest for collecion")
    @Test
    public void test(){
        List<Integer> listOfNumbers= Arrays.asList(1,4,6,7,8,52,41,47);
        assertThat(listOfNumbers,hasSize(8));

        assertThat(listOfNumbers,hasItem(52));

        assertThat(listOfNumbers,hasItems(6,7,8));

        assertThat(listOfNumbers,everyItem(greaterThan(0)));

    }
}
