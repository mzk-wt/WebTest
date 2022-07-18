package webtest.actions;

/**
 * １つ戻るアクション（BACK）
 * <pre>
 * (アクションパラメータ)
 * なし
 * </pre>
 */
public class WtActionBACK implements WtAction {

    /**
     * アクション実行.
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        params.driver.navigateBack();
        return true;
    }
}
