package cn.byhieg.iotutorialtest;

import cn.byhieg.iotutorial.bytestreamio.BufferdOutputStreamExample;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class BufferdOutputStreamExampleTest extends TestCase {
    public void testWriteToFile() throws Exception {
        new BufferdOutputStreamExample().writeToFile();

    }

}
