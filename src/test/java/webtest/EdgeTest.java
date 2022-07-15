package webtest;

import java.util.HashMap;
import java.util.Map;

import webtest.core.WtWebDriver;
import webtest.keys.InputKeys;

/**
 * Edgeブラウザ用のテストクラス
 */
public class EdgeTest extends WtTest {

    protected Map<InputKeys, String> getTestParams(String scenarioCsv) {
        Map<InputKeys, String> params = new HashMap<>();
        params.put(InputKeys.BROWSE_TYPE, WtWebDriver.BROWSE_TYPE_EDGE);
        params.put(InputKeys.OUTPUT_PATH, OUTPUT_ROOT_PATH + "edge");
        params.put(InputKeys.SCENARIO_FILE, SCENARIO_ROOT_PATH + scenarioCsv);
        return params;
    }
}
