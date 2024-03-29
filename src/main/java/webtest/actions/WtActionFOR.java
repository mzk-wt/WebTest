package webtest.actions;

import java.util.Map;

import webtest.core.WtTestScenarioAction;
import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * 繰り返し処理アクション（FOR）
 * <pre>
 * (アクションパラメータ)
 * [0]:ID
 * [1]:繰り返し回数
 * [2]:オプション（START=インデックス初期値(省略時0);STEP=インデックスのステップ数(省略時1)）
 * </pre>
 */
public class WtActionFOR implements WtAction {

    public final static String OP_START = "START";
    public final static String OP_STEP = "STEP";
    public final static String OP_ORDER = "ORDER";

    /**
     * アクション実行.
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        WtWebDriver driver = params.driver;
        String[] actionParams = params.actionParams;
        Map<String, Object> values = params.values;
        WtTestScenarioAction action = params.action;

        String id = actionParams[0];
        String count = actionParams[1];
        Map<String, String> options = WtUtils.parseOption(actionParams[2]);

        // オプション設定
        int s = options.containsKey(OP_START) ? Integer.parseInt(options.get(OP_START)) : 0;
        int c = Integer.parseInt(count);
        int step = options.containsKey(OP_STEP) ? Integer.parseInt(options.get(OP_STEP)) : 1;

        // 指定された回数繰り返し処理
        for (int i = s; i < c + s; i = i + step) {
            values.put(id + "@IDX", i);
            for (WtTestScenarioAction act : action.getChildActions()) {
                act.execute(driver, values);
            }
        }

        return true;
    }
}
