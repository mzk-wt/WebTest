package webtest.actions;

import java.util.Map;

import org.openqa.selenium.WebElement;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * スクロールアクション（SCROLL）
 * <pre>
 * (アクションパラメータ)
 * [0]:スクロール方法（0:指定した移動量だけ移動, 1:指定した要素まで移動）
 *     ※TODO:0を指定した場合にスクロールが動かない！！
 * [1]:横方向の移動量（スクロール方法＝0の場合）
 * [2]:縦方向の移動量（スクロール方法＝0の場合）
 * [3]:要素を特定するための検索文字列（スクロール方法＝1の場合）
 * [4]:検索方法（スクロール方法＝1の場合）（省略可、省略時はCSSセレクタで検索する）(@see {@link webtest.keys.ByType})
 * </pre>
 */
public class WtActionSCROLL implements WtAction {

    /**
     * アクション実行.
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        WtWebDriver driver = params.driver;
        String[] actionParams = params.actionParams;
        Map<String, Object> values = params.values;

        if ("0".equals(actionParams[0])) {
            driver.scroll(Integer.parseInt(actionParams[1]), Integer.parseInt(actionParams[2]));
            return true;

        } else if ("1".equals(actionParams[0])) {
            WebElement elem = WtUtils.getWebElement(driver, values, actionParams[4], actionParams[3]);
            driver.scrollToElement(elem);
            return true;
        }

        return false;
    }
}
