package cn.byhieg.iotutorialtest;

import cn.byhieg.iotutorial.charsetstreamio.FileReaderExample;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class FileReaderExampleTest extends TestCase {
    public void testReadFromFile() throws Exception {
        new FileReaderExample().readFromFile();
    }

}