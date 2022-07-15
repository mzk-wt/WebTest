package webtest;

import java.util.HashMap;
import java.util.Map;

import webtest.core.WtWebDriver;
import webtest.keys.InputKeys;

/**
 * FireFoxブラウザ用のテストクラス
 */
public class FireFoxTest extends WtTest {

    protected Map<InputKeys, String> getTestParams(String scenarioCsv) {
        Map<InputKeys, String> params = new HashMap<>();
        params.put(InputKeys.BROWSE_TYPE, WtWebDriver.BROWSE_TYPE_FIREFOX);
        params.put(InputKeys.OUTPUT_PATH, OUTPUT_ROOT_PATH + "firefox");
        params.put(InputKeys.SCENARIO_FILE, SCENARIO_ROOT_PATH + scenarioCsv);
        return params;
    }
}
