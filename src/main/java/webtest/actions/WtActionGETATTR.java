package webtest.actions;

import java.util.Map;

import org.openqa.selenium.By;

import webtest.core.WtWebDriver;
import webtest.keys.ByType;

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

        values.put(params[0], driver.getAttribute(by, params[1]));
        return true;
    }
}
