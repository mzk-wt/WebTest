package topamtest.fwc;

import java.util.Map;

import topamtest.fwc.keys.InputKeys;

public class FwcBaseTest {

    /** WEBドライバ */
    protected FwcWebDriver webDriver;

    /** テストシナリオ */
    protected FwcTestScenario scenario;

    /**
     * コンストラクタ
     */
    public FwcBaseTest() {
    }

    /**
     * テストを開始します.
     * @param params 初期化パラメータ
     */
    protected void start(Map<InputKeys, String> params) {
        try {
            // 初期化
            init(params);

            // テスト実行
            scenario.execute(webDriver);

        } finally {
            // 終了
            close();
        }
    }

    /**
     * テスト開始前の処理を行います.
     * @param params 初期化パラメータ
     */
    protected void init(Map<InputKeys, String> params) {
        // WEBドライバを初期化
        webDriver = new FwcWebDriver(params);

        // テストシナリオを初期化
        scenario = new FwcTestScenario(params);
    }

    /**
     * テスト終了後の処理を行います.
     */
    protected void close() {
        // ブラウザを閉じる
        if (webDriver != null) {
            webDriver.close();
            webDriver.quit();
        }
    }
}
