package webtest.actions;

import java.util.Map;

import org.openqa.selenium.WebElement;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * 属性取得アクション（GETATTR）
 * <pre>
 * (アクションパラメータ)
 * [0]:取得した値を識別するためのキー
 * [1]:取得したい属性の名称
 * [2]:要素を特定するための検索文字列
 * [3]:検索方法（省略可、省略時はCSSセレクタで検索する）(@see {@link webtest.keys.ByType})
 * </pre>
 */
public class WtActionGETATTR implements WtAction {

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
        values.put(actionParams[0], driver.getAttribute(elem, actionParams[1]));

        return true;
    }
}
