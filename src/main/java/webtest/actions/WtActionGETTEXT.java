package webtest.actions;

import java.util.Map;

import org.openqa.selenium.By;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;
import webtest.keys.ByType;

/**
 * テキスト取得アクション（GETTEXT）
 * <pre>
 * (アクションパラメータ)
 * [0]:取得した値を識別するためのキー
 * [1]:要素を特定するための検索文字列
 * [2]:検索方法（省略可、省略時はCSSセレクタで検索する）(@see {@link webtest.keys.ByType})
 * [3]:要素番号（省略可、省略時は0）
 * </pre>
 */
public class WtActionGETTEXT implements WtAction {

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
        if (WtUtils.isNotBlank(actionParams[2])) {
            type = ByType.valueOf(actionParams[2].toUpperCase());
        }
        By by = type.getByInstance(WtUtils.formatValues(actionParams[1], values));

        int elemNo = 0;
        if (WtUtils.isNotBlank(actionParams[3])) {
            elemNo = Integer.parseInt(actionParams[3]);
        }

        values.put(actionParams[0], driver.getText(by, elemNo));
        return true;
    }
}
