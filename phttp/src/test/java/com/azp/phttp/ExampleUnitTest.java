package com.azp.phttp;

import com.azp.phttp.proxy.AProxy;
import com.azp.phttp.proxy.AddApi;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        AProxy aProxy = new AProxy();
        int add = aProxy.create(AddApi.class).add(1, 2);
        assertEquals(3, add);
    }
}