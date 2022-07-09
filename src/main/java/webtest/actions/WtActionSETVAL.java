package webtest.actions;

import java.util.Map;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * 変数に値をセットするアクション（SETVAL）
 * <pre>
 * (アクションパラメータ)
 * [0]:変数名
 * [1]:セットする値
 * </pre>
 */
public class WtActionSETVAL implements WtAction {

    /**
     * アクション実行.
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(WtWebDriver driver, String[] params, Map<String, Object> values) {
        values.put(params[0], WtUtils.formatValues(params[1], values));
        return true;
    }
}
