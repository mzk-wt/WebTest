package webtest;

import java.util.HashMap;
import java.util.Map;

import webtest.core.WtBaseTest;
import webtest.keys.InputKeys;

public class WebTest extends WtBaseTest {

    /**
     * メインメソッド
     * @param args コマンドラインパラメータ
     *             [0]ブラウザ種別
     *             [1]出力フォルダのパス
     *             [2]テストシナリオのパス
     */
    public static void main(String[] args) {
        // コマンドラインパラメータから初期化パラメータを取得
        Map<InputKeys, String> params = new HashMap<>();
        params.put(InputKeys.BROWSE_TYPE, args[0]);
        params.put(InputKeys.OUTPUT_PATH, args[1]);
        params.put(InputKeys.SCENARIO_FILE, args[2]);

        new WebTest().start(params);
    }
}
