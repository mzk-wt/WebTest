package webtest;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import webtest.keys.InputKeys;

public class Wt001Test extends WtTest {

    @Test
    @DisplayName("ブラウザ起動確認")
    void testOpenUrl() {
        Map<InputKeys, String> params = new HashMap<>();
        params.put(InputKeys.BROWSE_TYPE, "chrome");
        params.put(InputKeys.BROWSE_DRIVER, "selenium/chromedriver/102/chromedriver.exe");
        params.put(InputKeys.OUTPUT_PATH, "out/test001");
        params.put(InputKeys.SCENARIO_FILE, "src/test/resource/test001.csv");

        WebTest test = new WebTest();
        test.start(params);

        System.out.println("test end");
    }
}
