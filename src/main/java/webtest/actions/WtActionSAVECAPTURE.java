package webtest.actions;

import java.util.Map;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * スクリーンショット取得アクション（SAVECAPTURE）
 * <pre>
 * (アクションパラメータ)
 * [0]:保存先のパス（省略可。省略した場合、"@SC_SAVEPATH"で登録した変数の値を使用。）
 * [1]:ファイル名（省略可。省略した場合、”@SC_FILENAME"で登録した変数の値＋連番を使用。）
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
        String path = values.containsKey("@SC_SAVEPATH") ? (String) values.get("@SC_SAVEPATH") : "";
        if (0 < params.length && WtUtils.isNotBlank(params[0])) {
            path = params[0];
        }

        int seq = ((Integer) values.get("@SC_SEQ")) + 1;
        String filename = values.containsKey("@SC_FILENAME") ? (String) values.get("@SC_FILENAME") + seq : "";
        if (1 < params.length && WtUtils.isNotBlank(params[1])) {
            filename = params[1];

        } else {
            values.put("@SC_SEQ", seq);
        }

        driver.saveScreenshotAsFile(path, filename);
        return true;
    }
}
