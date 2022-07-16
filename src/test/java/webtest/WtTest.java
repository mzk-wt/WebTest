package webtest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import webtest.keys.InputKeys;

public abstract class WtTest {

    protected static final String ROOT_PATH = System.getProperty("user.dir");

    protected static final String OUTPUT_ROOT_PATH = ROOT_PATH + "/out/";

    protected static final String SCENARIO_ROOT_PATH = ROOT_PATH + "/src/test/resource/scenario/";

    protected abstract Map<InputKeys, String> getTestParams(String scenarioCsv);

    protected String doTest(String scenarioFile) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);
        System.setOut(ps);

        new WebTest().start(getTestParams(scenarioFile));

        return bos.toString();
    }

    @Test
    @DisplayName("ブラウザナビゲーション確認")
    void testNavigation() {
        String out = doTest("testNavigation.csv");
        assertEquals(out,
                  "OPEN:WebTest(page1)\n"
                + "NAVIGATE:WebTest(page2)\n"
                + "BACK:WebTest(page1)\n"
                + "FORWARD:WebTest(page2)\n"
                + "REFRESH:WebTest(page2)\n"
                + "WINSWITCH:WebTest(page3)\n"
                + "WINSWITCH:WebTest(page2)\n");
    }

    @Test
    @DisplayName("URL取得確認")
    void testGetUrl() {
        String out = doTest("testGetUrl.csv");
        assertEquals(out,
                  "OPEN:WebTest(page1)\n"
                + "GETURL:file://" + ROOT_PATH + "/src/test/resource/samplepage/page1.html\n");
    }

    @Test
    @DisplayName("タイトル取得確認")
    void testGetTitle() {
        String out = doTest("testGetTitle.csv");
        assertEquals(out,
                  "TITLE:WebTest(page1)\n");
    }

    @Test
    @DisplayName("テキスト取得確認")
    void testGetText() {
        String out = doTest("testGetText.csv");
        assertEquals(out,
                  "OPEN:WebTest(page1)\n"
                + "text(classname)\n"
                + "text(classname)\n"
                + "text(classname)\n"
                + "text(id1)\n"
                + "text(linktext)\n"
                + "text(name1)\n"
                + "text(name2-1)\n"
                + "text(linktext)\n"
                + "text(classname)\n"
                + "text(id1)\n");
    }

}
