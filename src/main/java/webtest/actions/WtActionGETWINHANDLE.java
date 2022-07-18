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
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        WtWebDriver driver = params.driver;
        String[] actionParams = params.actionParams;
        Map<String, Object> values = params.values;

        if (WtUtils.isBlank(actionParams[1])) {
            values.put(actionParams[0], driver.getWindowHandle());

        } else {
            List<String> handles = Arrays.asList(driver.getWindowHandles().toArray(new String[0]));
            int winNum = Integer.parseInt(actionParams[1]);
            if (winNum == -1) {
                winNum = handles.size() - 1;
            }
            values.put(actionParams[0], handles.get(winNum));
        }

        return true;
    }
}
