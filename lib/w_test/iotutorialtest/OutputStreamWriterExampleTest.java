package cn.byhieg.iotutorialtest;

import cn.byhieg.iotutorial.charsetstreamio.OutputStreamWriterExample;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/2/23.
 * Mail byhieg@gmail.com
 */
public class OutputStreamWriterExampleTest extends TestCase {

    public void testWriteToFile() throws Exception {
        new OutputStreamWriterExample().writeToFile();
    }

}