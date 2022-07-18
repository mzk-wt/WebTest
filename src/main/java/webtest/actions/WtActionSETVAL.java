package webtest.actions;

import java.util.Map;

import webtest.core.WtUtils;

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
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        String[] actionParams = params.actionParams;
        Map<String, Object> values = params.values;

        values.put(actionParams[0], WtUtils.formatValues(actionParams[1], values));
        return true;
    }
}
