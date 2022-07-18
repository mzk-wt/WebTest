package webtest.actions;

/**
 * １つ進むアクション（FORWARD）
 * <pre>
 * (アクションパラメータ)
 * なし
 * </pre>
 */
public class WtActionFORWARD implements WtAction {

    /**
     * アクション実行.
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        params.driver.navigateForward();
        return true;
    }
}
