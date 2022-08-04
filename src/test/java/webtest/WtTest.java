package webtest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import webtest.core.WtUtils;
import webtest.keys.InputKeys;

public abstract class WtTest {

    protected static final String ROOT_PATH = System.getProperty("user.dir");

    protected static final String OUTPUT_ROOT_PATH = ROOT_PATH + "/src/test/out/";

    protected static final String SCENARIO_ROOT_PATH = ROOT_PATH + "/src/test/resource/scenario/";

    protected ByteArrayOutputStream bos;

    protected abstract Map<InputKeys, String> getTestParams(String scenarioCsv);

    protected String doTest(String scenarioFile) {
        setOut();
        new WebTest().start(getTestParams(scenarioFile));
        return getOut();
    }

    protected void setOut() {
        bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);
        System.setOut(ps);
    }

    protected String getOut() {
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
                + "text(id1)\n"
                + "text(name2-2)\n");
    }

    @Test
    @DisplayName("属性取得確認")
    void testGetAttr() {
        String out = doTest("testGetAttr.csv");
        assertEquals(out,
                  "OPEN:WebTest(page1)\n"
                + "attr1\n"
                + "c1 c2\n"
                + "data1\n");
    }

    @Test
    @DisplayName("画面キャプチャ取得確認")
    void testSaveCapture() {
        // 出力先フォルダを削除する
        Map<InputKeys, String> params = getTestParams("testSaveCapture.csv");
        Path outPath = Paths.get(params.get(InputKeys.OUTPUT_PATH));
        try {
            WtUtils.deleteFiles(outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // テスト実行
        doTest("testSaveCapture.csv");

        // 出力されたファイル一覧を取得して検証
        try (Stream<Path> stream = Files.walk(outPath)) {
            stream.forEach(p -> System.out.println(p.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(getOut(),
                  ROOT_PATH + "/src/test/out/chrometest\n"
                + ROOT_PATH + "/src/test/out/chrometest/outsub1\n"
                + ROOT_PATH + "/src/test/out/chrometest/outsub1/CAPTURE_3.png\n"
                + ROOT_PATH + "/src/test/out/chrometest/outsub1/CAPTURE_2.png\n"
                + ROOT_PATH + "/src/test/out/chrometest/outsub1/CAPTURE_1.png\n"
                + ROOT_PATH + "/src/test/out/chrometest/outsub2\n"
                + ROOT_PATH + "/src/test/out/chrometest/outsub2/CAPTURE_2.png\n"
                + ROOT_PATH + "/src/test/out/chrometest/outsub2/CAPTURE_1.png\n"
                + ROOT_PATH + "/src/test/out/chrometest/CAPTURE_2.png\n"
                + ROOT_PATH + "/src/test/out/chrometest/CAPTURE_1.png\n");
    }

    @Test
    @DisplayName("入力確認")
    void testInputValues() {
        String out = doTest("testInputValues.csv");
        assertEquals(out,
                  "abc123!\"#あいう\n"
                + "2022-01\n"
                + "2022-01-01\n"
                + "2022-01-01T12:30\n"
                + "12:30\n"
                + "2022-W20\n"
                + "https://www.google.com/\n"
                + "test.0123-4567@xxx.yyy\n"
                + "090-1234-5678\n"
                + "9876543210\n"
                + "password\n"
                + "search\n"
                + "true\n"
                + "null\n"
                + "true;null\n"
                + "null;true\n"
                + "25\n"
                + "#fc23e5\n"
                + "C:fakepathuploadfile1.txt\n"
                + "click button!\n"
                + "click image button!\n"
                + "click submit button!\n"
                + "hidden\n"
                + "1\n"
                + "2\n"
                + "3\n");
    }
}
