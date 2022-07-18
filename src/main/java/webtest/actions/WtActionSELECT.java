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

        ByType type = ByType.CSS;
        if (WtUtils.isNotBlank(actionParams[3])) {
            type = ByType.valueOf(actionParams[3].toUpperCase());
        }
        By by = type.getByInstance(WtUtils.formatValues(actionParams[2], values));

        switch (actionParams[0]) {
        case "0":
            driver.selectByIndex(by, Integer.parseInt(actionParams[1]));
            break;
        case "1":
            driver.selectByValue(by, actionParams[1]);
            break;
        case "2":
            driver.selectByVisibleText(by, actionParams[1]);
            break;
        }
        return true;
    }
}
