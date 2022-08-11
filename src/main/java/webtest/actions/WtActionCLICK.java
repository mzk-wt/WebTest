package webtest.actions;

import java.util.Map;

import org.openqa.selenium.WebElement;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * ドロップダウン選択アクション（SELECT）
 * <pre>
 * (アクションパラメータ)
 * [0]:要素を特定するための検索文字列
 * [1]:検索方法（省略可、省略時はCSSセレクタで検索する）(@see {@link webtest.keys.ByType})
 * </pre>
 */
public class WtActionCLICK implements WtAction {

    /**
     * アクション実行.
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        WtWebDriver driver = params.driver;
        String[] actionParams = params.actionParams;
        Map<String, Object> values = params.values;

        WebElement elem = WtUtils.getWebElement(driver, values, actionParams[1], actionParams[0]);
        driver.clickElement(elem);

        return true;
    }
}
