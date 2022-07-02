package webtest.actions;

import webtest.core.WtWebDriver;

public interface WtAction<T> {

    /**
     * アクション実行.
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     */
    public T executeAction(WtWebDriver driver, String[] params);
}
