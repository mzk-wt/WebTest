package webtest.actions;

import java.util.Map;

import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * ログ出力アクション（LOG）
 * <pre>
 * (アクションパラメータ)
 * [0]:出力する値
 * [1]:出力先（0:ファイル, 1:標準出力, 2:両方）
 * </pre>
 */
public class WtActionLOG implements WtAction {

    /**
     * アクション実行.
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(WtWebDriver driver, String[] params, Map<String, Object> values) {
        // TODO
        System.out.println(WtUtils.formatValues(params[0], values));
        return true;
    }
}
