package webtest.actions;

import webtest.core.WtWebDriver;

/**
 * ページ表示アクション（OPEN）
 * <pre>
 * (アクションパラメータ)
 * [0]:URL
 * </pre>
 */
public class WtActionOPEN implements WtAction<Void> {

    /**
     * アクション実行.
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     */
    public Void executeAction(WtWebDriver driver, String[] params) {
        driver.get(params[0]);
        return null;
    }
}
