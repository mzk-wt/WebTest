package webtest.actions;

import java.util.Map;

import webtest.core.WtWebDriver;

/**
 * 現在のURL取得アクション（GETURL）
 * <pre>
 * (アクションパラメータ)
 * [0]:取得した値を識別するためのキー
 * </pre>
 */
public class WtActionGETURL implements WtAction {

    /**
     * アクション実行.
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(WtWebDriver driver, String[] params, Map<String, Object> values) {
        values.put(params[0], driver.getCurrentUrl());
        return true;
    }
}
