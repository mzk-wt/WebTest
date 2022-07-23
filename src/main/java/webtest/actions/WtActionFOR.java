package webtest.actions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import webtest.core.WtTestScenarioAction;
import webtest.core.WtUtils;
import webtest.core.WtWebDriver;
import webtest.keys.ByType;

/**
 * 繰り返し処理アクション（FOR）
 * <pre>
 * (アクションパラメータ)
 * [0]:ID
 * [1]:繰り返し回数（省略可。[1][2]のいずれかは必須）
 * [2]:要素を特定するための検索文字列（省略可。[1][2]のいずれかは必須）
 * [3]:検索方法（省略可、省略時はCSSセレクタで検索する）(@see {@link webtest.keys.ByType})
 * </pre>
 */
public class WtActionFOR implements WtAction {

    /**
     * アクション実行.
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        WtWebDriver driver = params.driver;
        String[] actionParams = params.actionParams;
        Map<String, Object> values = params.values;
        WtTestScenarioAction action = params.action;

        String id = actionParams[0];
        String count = actionParams[1];
        String byQuery = actionParams[2];

        // 指定された回数繰り返し処理
        if (WtUtils.isNotBlank(count)) {
            int c = Integer.parseInt(count);

            for (int i = 0; i < c; i++) {
                values.put(id + "@IDX", i);
                for (WtTestScenarioAction act : action.getChildActions()) {
                    act.execute(driver, values);
                }
            }

        // 指定された要素の数だけ繰り返し処理
        } else if (WtUtils.isNotBlank(byQuery)) {
            ByType type = ByType.CSS;
            if (WtUtils.isNotBlank(actionParams[3])) {
                type = ByType.valueOf(actionParams[3].toUpperCase());
            }
            By by = type.getByInstance(WtUtils.formatValues(byQuery, values));

            List<WebElement> elements = driver.findElements(by);
            for (int i = 0; i < elements.size(); i++) {
                values.put(id + "@IDX", i);
                values.put(id + "@ELEM", elements.get(i));
                for (WtTestScenarioAction act : action.getChildActions()) {
                    act.execute(driver, values);
                }
            }
        }

        return true;
    }
}
