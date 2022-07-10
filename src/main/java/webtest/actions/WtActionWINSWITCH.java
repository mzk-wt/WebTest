package webtest.actions;

import java.util.Map;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * ウィンドウを切り替えるアクション（WINSWITCH）
 * <pre>
 * (アクションパラメータ)
 * [0]:ウィンドウ名もしくはウィンドウハンドル
 * </pre>
 */
public class WtActionWINSWITCH implements WtAction {

    /**
     * アクション実行.
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(WtWebDriver driver, String[] params, Map<String, Object> values) {
        driver.switchWindow(WtUtils.formatValues(params[0], values));
        return true;
    }
}
