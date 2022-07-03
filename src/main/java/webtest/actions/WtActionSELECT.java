package webtest.actions;

import java.util.Map;

import org.openqa.selenium.By;

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
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(WtWebDriver driver, String[] params, Map<String, Object> values) {
        ByType type = ByType.CSS;
        if (3 < params.length) {
            type = ByType.valueOf(params[3].toUpperCase());
        }
        By by = type.getByInstance(params[2]);

        switch (params[0]) {
        case "0":
            driver.selectByIndex(by, Integer.parseInt(params[1]));
            break;
        case "1":
            driver.selectByValue(by, params[1]);
            break;
        case "2":
            driver.selectByVisibleText(by, params[1]);
            break;
        }
        return true;
    }
}
