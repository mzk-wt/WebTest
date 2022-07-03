package webtest.actions;

import java.util.Map;

import webtest.core.WtWebDriver;

/**
 * ウィンドウサイズ変更アクション（WINSIZE）
 * <pre>
 * (アクションパラメータ)
 * [0]:0=ウィンドウサイズ設定、1=ウィンドウ最大化
 * [1]:横幅（[0]=0の場合のみ）
 * [2]:高さ（[0]=0の場合のみ）
 * </pre>
 */
public class WtActionWINSIZE implements WtAction {

    /**
     * アクション実行.
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(WtWebDriver driver, String[] params, Map<String, Object> values) {
        if ("0".equals(params[0])) {
            driver.setWindowSize(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
        } else if ("1".equals(params[1])) {
            driver.maximizeWindow();
        }
        return true;
    }
}
