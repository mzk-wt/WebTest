package webtest.actions;

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
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        WtWebDriver driver = params.driver;
        String[] actionParams = params.actionParams;

        if ("0".equals(actionParams[0])) {
            driver.setWindowSize(Integer.parseInt(actionParams[1]), Integer.parseInt(actionParams[2]));
        } else if ("1".equals(actionParams[1])) {
            driver.maximizeWindow();
        }
        return true;
    }
}
