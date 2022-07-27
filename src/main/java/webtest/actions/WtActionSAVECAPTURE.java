package webtest.actions;

import java.util.Map;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * スクリーンショット取得アクション（SAVECAPTURE）
 * <pre>
 * (アクションパラメータ)
 * [0]:ファイル名
 * [1]:保存先のパス（省略可。省略した場合、"@SC_SAVEPATH"で登録した変数の値を使用。）
 * </pre>
 */
public class WtActionSAVECAPTURE implements WtAction {

    /**
     * アクション実行.
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        WtWebDriver driver = params.driver;
        String[] actionParams = params.actionParams;
        Map<String, Object> values = params.values;

        String filename = WtUtils.formatValues(actionParams[0], values);
        if (actionParams[0].contains("{{@SC_SEQ}}")) {
            values.put("@SC_SEQ", Integer.parseInt(String.valueOf(values.get("@SC_SEQ"))) + 1);
        }

        String path = values.containsKey("@SC_SAVEPATH") ? (String) values.get("@SC_SAVEPATH") : "";
        if (WtUtils.isNotBlank(actionParams[1])) {
            path = WtUtils.formatValues(actionParams[1], values);
        }

        driver.saveScreenshotAsFile(path, filename);
        return true;
    }
}
