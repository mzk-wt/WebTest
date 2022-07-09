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
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(WtWebDriver driver, String[] params, Map<String, Object> values) {
        String filename = WtUtils.formatValues(params[0], values);
        if (params[0].contains("{{@SC_SEQ}}")) {
            values.put("@SC_SEQ", ((Integer) values.get("@SC_SEQ")) + 1);
        }

        String path = values.containsKey("@SC_SAVEPATH") ? (String) values.get("@SC_SAVEPATH") : "";
        if (1 < params.length && WtUtils.isNotBlank(params[1])) {
            path = params[1];
        }

        driver.saveScreenshotAsFile(path, filename);
        return true;
    }
}
