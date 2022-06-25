package webtest.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import webtest.core.keys.ActionType;
import webtest.core.keys.CsvKeys;

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
     */
    public void execute(WtWebDriver driver) {
        try {
            Method m = getClass().getDeclaredMethod("executeAction" + actionType.name(), WtWebDriver.class);
            m.invoke(this, driver);

        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
//          // タイトル取得
//          System.out.println(webDriver.getTitle());
    //
//          // スクリーンショット取得
//          webDriver.saveScreenshotAsFile("C:\\Users\\y_mizuki.KEINS-AD\\デスクトップ", "test.png");

    /**
     * 【アクション:A0】
     * ページを表示します.
     * <pre>
     * (CSVパラメータ)
     * [0]:URL
     * </pre>
     * @param driver WEBドライバ
     */
    @SuppressWarnings("unused")
    private void executeActionA0(WtWebDriver driver) {
        driver.get(actionParams[0]);
    }
}
