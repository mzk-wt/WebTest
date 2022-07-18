package webtest.actions;

import webtest.core.WtWebDriver;

/**
 * 待機アクション（WAIT）
 * <pre>
 * (アクションパラメータ)
 * [0]:待機時間（秒）
 * </pre>
 */
public class WtActionWAIT implements WtAction {

    /**
     * アクション実行.
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        WtWebDriver driver = params.driver;
        String[] actionParams = params.actionParams;

        driver.waitLoading(Integer.parseInt(actionParams[0]));
        return true;
    }
}
