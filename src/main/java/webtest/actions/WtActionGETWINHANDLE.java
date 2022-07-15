package webtest.actions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * ウィンドウハンドル取得アクション（GETWINHANDLE）
 * <pre>
 * (アクションパラメータ)
 * [0]:取得した値を識別するためのキー
 * [1]:ウィンドウ番号（省略可。省略した場合は現在のウィンドウ。-1で最後のウィンドウ。）
 * </pre>
 */
public class WtActionGETWINHANDLE implements WtAction {

    /**
     * アクション実行.
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(WtWebDriver driver, String[] params, Map<String, Object> values) {
        if (WtUtils.isBlank(params[1])) {
            values.put(params[0], driver.getWindowHandle());

        } else {
            List<String> handles = Arrays.asList(driver.getWindowHandles().toArray(new String[0]));
            int winNum = Integer.parseInt(params[1]);
            if (winNum == -1) {
                winNum = handles.size() - 1;
            }
            values.put(params[0], handles.get(winNum));
        }

        return true;
    }
}
