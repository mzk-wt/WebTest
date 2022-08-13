package webtest.actions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import webtest.core.WtTestScenarioAction;
import webtest.core.WtUtils;
import webtest.core.WtWebDriver;

/**
 * 繰り返し処理アクション（FORELEM）
 * <pre>
 * (アクションパラメータ)
 * [0]:ID
 * [1]:要素を特定するための検索文字列（省略可。[1][2]のいずれかは必須）
 * [2]:検索方法（省略可、省略時はCSSセレクタで検索する）(@see {@link webtest.keys.ByType})
 * [3]:オプション（START=インデックス初期値(省略時0);STEP=インデックスのステップ数(省略時1)）
 * </pre>
 */
public class WtActionFORELEM implements WtAction {

    public final static String OP_START = "START";
    public final static String OP_STEP = "STEP";

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
        String byQuery = actionParams[1];
        String byType = actionParams[2];
        Map<String, String> options = WtUtils.parseOption(actionParams[3]);

        // オプション設定
        int s = options.containsKey(OP_START) ? Integer.parseInt(options.get(OP_START)) : 0;
        int step = options.containsKey(OP_STEP) ? Integer.parseInt(options.get(OP_STEP)) : 1;
        List<WebElement> elements = WtUtils.getWebElements(driver, values, byType, byQuery);

        // 指定された要素の数だけ繰り返し処理
        for (int i = s; i < elements.size() + s; i = i + step) {
            values.put(id + "@IDX", i);
            values.put(id + "@ELEM", elements.get(i));
            for (WtTestScenarioAction act : action.getChildActions()) {
                act.execute(driver, values);
            }
        }

        return true;
    }
}
