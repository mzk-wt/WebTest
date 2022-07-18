package webtest.actions;

import java.util.Map;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * ページ遷移アクション（NAVIGATE）
 * <pre>
 * (アクションパラメータ)
 * [0]:URL
 * </pre>
 */
public class WtActionNAVIGATE implements WtAction {

    /**
     * アクション実行.
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        WtWebDriver driver = params.driver;
        String[] actionParams = params.actionParams;
        Map<String, Object> values = params.values;

        driver.navigateTo(WtUtils.formatValues(actionParams[0], values));
        return true;
    }
}
