package webtest;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import webtest.core.WtWebDriver;
import webtest.keys.InputKeys;

/**
 * Chromeブラウザ用のテストクラス
 */
public class ChromeTest extends WtTest {

    private Map<InputKeys, String> getTestParams(String scenarioCsv) {
        Map<InputKeys, String> params = new HashMap<>();
        params.put(InputKeys.BROWSE_TYPE, WtWebDriver.BROWSE_TYPE_CHROME);
        params.put(InputKeys.OUTPUT_PATH, OUTPUT_ROOT_PATH + "chrometest");
        params.put(InputKeys.SCENARIO_FILE, SCENARIO_ROOT_PATH + scenarioCsv);
        return params;
    }

    @Test
    @DisplayName("ブラウザ起動確認")
    void testBrowserBoot() {
        new WebTest().start(getTestParams("testBrowserBoot.csv"));
    }
}
