package webtest;

import java.util.HashMap;
import java.util.Map;

import webtest.core.WtBaseTest;
import webtest.keys.InputKeys;

public class WebTest extends WtBaseTest {

    /**
     * メインメソッド
     * @param args コマンドラインパラメータ
     */
    public static void main(String[] args) {
        // コマンドラインパラメータから初期化パラメータを取得 TODO
        Map<InputKeys, String> params = new HashMap<>();
        params.put(InputKeys.BROWSE_TYPE, "chrome");
        params.put(InputKeys.OUTPUT_PATH, "D:\\work\\topamtest\\topamtest\\out");
        params.put(InputKeys.SCENARIO_FILE, "D:\\work\\topamtest\\topamtest\\selenium\\scenario\\test.csv");

        new WebTest().start(params);
    }
}
