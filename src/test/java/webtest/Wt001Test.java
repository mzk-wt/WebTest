package webtest;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import webtest.core.WtWebDriver;
import webtest.keys.InputKeys;

public class Wt001Test extends WtTest {

    String rootPath = System.getProperty("user.dir");

    @Test
    @DisplayName("ブラウザ起動確認")
    void testOpenUrl() {
        Map<InputKeys, String> params = new HashMap<>();
        params.put(InputKeys.BROWSE_TYPE, WtWebDriver.BROWSE_TYPE_CHROME);
        params.put(InputKeys.OUTPUT_PATH, rootPath + "/out/test001");
        params.put(InputKeys.SCENARIO_FILE, rootPath + "/src/test/resource/scenario/test001.csv");

        WebTest test = new WebTest();
        test.start(params);

        System.out.println("test end");
    }
}
