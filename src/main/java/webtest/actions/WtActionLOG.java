package webtest.actions;

import java.util.Map;

import webtest.core.WtUtils;

/**
 * ログ出力アクション（LOG）
 * <pre>
 * (アクションパラメータ)
 * [0]:出力する値
 * [1]:出力先（0:ファイル, 1:標準出力, 2:両方）(省略可。省略時は1)
 * </pre>
 */
public class WtActionLOG implements WtAction {

    /**
     * アクション実行.
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        String[] actionParams = params.actionParams;
        Map<String, Object> values = params.values;

        // TODO
        System.out.println(WtUtils.formatValues(actionParams[0], values));
        return true;
    }
}
