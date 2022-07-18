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
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        WtWebDriver driver = params.driver;
        String[] actionParams = params.actionParams;
        Map<String, Object> values = params.values;

        values.put(actionParams[0], driver.getCurrentUrl());
        return true;
    }
}
