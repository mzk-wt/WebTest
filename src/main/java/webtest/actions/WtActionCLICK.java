package webtest.actions;

import java.util.Map;

import org.openqa.selenium.By;

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
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(WtWebDriver driver, String[] params, Map<String, Object> values) {
        ByType type = ByType.CSS;
        if (1 < params.length) {
            type = ByType.valueOf(params[1].toUpperCase());
        }
        By by = type.getByInstance(params[0]);

        driver.clickElement(by);
        return true;
    }
}
