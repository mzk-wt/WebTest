package webtest.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import webtest.actions.WtAction;
import webtest.keys.ActionType;
import webtest.keys.CsvKeys;

/**
 * シナリオアクションクラス
 */
public class WtTestScenatioAction {

    /** アクション種別 */
    private ActionType actionType;

    /** アクションパラメータ */
    private String[] actionParams;

    /**
     * コンストラクタ.
     * @param params 初期化パラメータ
     */
    public WtTestScenatioAction(Map<CsvKeys, String> params) {
        init(params);
    }

    /**
     * アクションを初期化します.
     * @param params 初期化パラメータ
     */
    private void init(Map<CsvKeys, String> params) {
        // アクション種別
        actionType = ActionType.valueOf((String) params.get(CsvKeys.ACT_TYPE));

        // アクションパラメータ
        actionParams = new String[] {
                params.get(CsvKeys.ACT_PARAM_1),
                params.get(CsvKeys.ACT_PARAM_2),
                params.get(CsvKeys.ACT_PARAM_3),
                params.get(CsvKeys.ACT_PARAM_4),
                params.get(CsvKeys.ACT_PARAM_5)};
    }

    /**
     * アクションを実行します.
     * @param driver WEBドライバ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     */
    @SuppressWarnings("unchecked")
    public void execute(WtWebDriver driver, Map<String, Object> values) {
        if (actionType.equals(ActionType.NONE)) {
            return;
        }

        try {
            Class<WtAction> c = (Class<WtAction>) Class.forName("webtest.actions.WtAction" + actionType.name());
            Object obj = c.getDeclaredConstructor().newInstance();
            Method m = c.getDeclaredMethod("executeAction", WtWebDriver.class, String[].class, Map.class);
            boolean result = (boolean) m.invoke(obj, driver, actionParams, values);

            if (!result) {
                throw new RuntimeException("異常発生：" + actionType.name());
            }

        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
