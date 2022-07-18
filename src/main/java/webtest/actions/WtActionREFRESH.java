package webtest.actions;

/**
 * ページ更新アクション（REFRESH）
 * <pre>
 * (アクションパラメータ)
 * なし
 * </pre>
 */
public class WtActionREFRESH implements WtAction {

    /**
     * アクション実行.
     * @param params アクション実行用パラメータ
     * @return true=正常終了/false=異常終了
     */
    public boolean executeAction(ExecuteActionParameter params) {
        params.driver.navigateRefresh();
        return true;
    }
}
