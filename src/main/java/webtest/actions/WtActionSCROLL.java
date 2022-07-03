package webtest.actions;

import java.util.Map;

import org.openqa.selenium.By;

import webtest.core.WtWebDriver;
import webtest.keys.ByType;

/**
 * スクロールアクション（SCROLL）
 * <pre>
 * (アクションパラメータ)
 * [0]:スクロール方法（0:指定した移動量だけ移動, 1:指定した要素まで移動）
 * [1]:横方向の移動量（スクロール方法＝0の場合）
 * [2]:縦方向の移動量（スクロール方法＝0の場合）
 * [3]:要素を特定するための検索文字列（スクロール方法＝1の場合）
 * [4]:検索方法（スクロール方法＝1の場合）（省略可、省略時はCSSセレクタで検索する）(@see {@link webtest.keys.ByType})
 * </pre>
 */
public class WtActionSCROLL implements WtAction {

    /**
     * アクション実行.
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(WtWebDriver driver, String[] params, Map<String, Object> values) {
        if ("0".equals(params[0])) {
            driver.scroll(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
            return true;

        } else if ("1".equals(params[1])) {
            ByType type = ByType.CSS;
            if (4 < params.length) {
                type = ByType.valueOf(params[4].toUpperCase());
            }
            By by = type.getByInstance(params[3]);
            driver.scrollToElement(by);
            return true;
        }

        return false;
    }
}
