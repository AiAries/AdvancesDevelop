
package cn.com.codequality;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static org.junit.Assert.*;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("cn.com.codequality", appContext.getPackageName());
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().addHeader("content","txt")
                .put(RequestBody.create(MediaType.parse("txet"),"i am it boy ")).build();
        Call call = okHttpClient.newCall(request);
        call.
    }
}

