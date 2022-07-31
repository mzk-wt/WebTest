package webtest.actions;

import java.util.Map;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * スクリプト実行アクション（EXECSCRIPT）
 * <pre>
 * (アクションパラメータ)
 * [0]:スクリプト
 * [1]:パラメータ1（省略可）
 * [2]:パラメータ2（省略可）
 * [3]:パラメータ3（省略可）
 * [4]:パラメータ4（省略可）
 * </pre>
 */
public class WtActionEXECSCRIPT implements WtAction {

    /**
     * アクション実行.
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        WtWebDriver driver = params.driver;
        String[] actionParams = params.actionParams;
        Map<String, Object> values = params.values;

        driver.execScript(
                WtUtils.formatValues(actionParams[0], values),
                actionParams[1],
                actionParams[2],
                actionParams[3],
                actionParams[4]);
        return true;
    }
}
