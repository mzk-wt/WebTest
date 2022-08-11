package webtest.actions;

import java.util.Map;

import org.openqa.selenium.WebElement;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * ドロップダウン選択アクション（SELECT）
 * <pre>
 * (アクションパラメータ)
 * [0]:選択方法（0:index, 1:値, 2:表示文字列）
 * [1]:選択する値
 * [2]:要素を特定するための検索文字列
 * [3]:検索方法（省略可、省略時はCSSセレクタで検索する）(@see {@link webtest.keys.ByType})
 * </pre>
 */
public class WtActionSELECT implements WtAction {

    /**
     * アクション実行.
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        WtWebDriver driver = params.driver;
        String[] actionParams = params.actionParams;
        Map<String, Object> values = params.values;

        WebElement elem = WtUtils.getWebElement(driver, values, actionParams[3], actionParams[2]);

        switch (actionParams[0]) {
        case "0":
            driver.selectByIndex(elem, Integer.parseInt(actionParams[1]));
            break;
        case "1":
            driver.selectByValue(elem, actionParams[1]);
            break;
        case "2":
            driver.selectByVisibleText(elem, actionParams[1]);
            break;
        }
        return true;
    }
}
