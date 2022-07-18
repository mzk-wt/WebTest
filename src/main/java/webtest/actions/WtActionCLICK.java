package webtest.actions;

import java.util.Map;

import org.openqa.selenium.By;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;
import webtest.keys.ByType;

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

        ByType type = ByType.CSS;
        if (WtUtils.isNotBlank(actionParams[1])) {
            type = ByType.valueOf(actionParams[1].toUpperCase());
        }
        By by = type.getByInstance(WtUtils.formatValues(actionParams[0], values));

        driver.clickElement(by);
        return true;
    }
}
