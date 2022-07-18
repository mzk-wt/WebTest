package webtest.actions;

import java.util.Map;

import webtest.core.WtTestScenarioAction;
import webtest.core.WtWebDriver;

/**
 * アクション実行用パラメータクラス
 */
public class ExecuteActionParameter {
    public WtWebDriver driver;
    public String[] actionParams;
    public Map<String, Object> values;
    public WtTestScenarioAction action;

    /**
     * コンストラクタ.
     * @param driver WEBドライバ
     * @param params アクションパラメータ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @param action シナリオアクション
     */
    public ExecuteActionParameter(WtWebDriver driver, String[] actionParams, Map<String, Object> values,
            WtTestScenarioAction action) {
        this.driver = driver;
        this.actionParams = actionParams;
        this.values = values;
        this.action = action;
    }
}