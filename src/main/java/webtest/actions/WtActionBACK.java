package webtest.actions;

import java.util.Map;

import webtest.core.WtWebDriver;

/**
 * １つ戻るアクション（BACK）
 * <pre>
 * (アクションパラメータ)
 * なし
 * </pre>
 */
public class WtActionBACK implements WtAction {

    /**
     * アクション実行.
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(WtWebDriver driver, String[] params, Map<String, Object> values) {
        driver.navigateBack();
        return true;
    }
}
