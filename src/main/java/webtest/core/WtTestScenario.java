package webtest.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webtest.keys.CsvKeys;
import webtest.keys.InputKeys;

/**
 * シナリオクラス
 */
public class WtTestScenario {

    /** シナリオ内で実行するアクションリスト */
    private List<WtTestScenarioAction> actions = new ArrayList<>();

    /** 現在実行中のアクション番号 */
    private int currentActionNo = -1;

    /** 現在実行中のアクション */
    private WtTestScenarioAction currentAction;

    /** シナリオ内で取得した値を持ち運ぶためのマップ  */
    private Map<String, Object> scenarioValues = new HashMap<>();

    /**
     * コンストラクタ.
     * @param params 初期化パラメータ
     */
    public WtTestScenario(Map<InputKeys, String> params) {
        init(params);
    }

    /**
     * シナリオを初期化します.
     * @param params 初期化パラメータ
     */
    private void init(Map<InputKeys, String> params) {
        // 共通変数の準備
        prepareCommonScenarioValues();

        // CSVファイルを1行ずつ読込んでシナリオアクションを登録する
        BufferedReader br = null;
        try {
            try {
                String fileName = params.get(InputKeys.SCENARIO_FILE);
                br = new BufferedReader(new FileReader(new File(fileName)));

                List<WtTestScenarioAction> registActions = actions;

                String line;
                while ((line = br.readLine()) != null) {
                    if (WtUtils.isBlank(line)) {
                        continue;
                    }

                    String[] data = Arrays.copyOf(line.split(","), 6);
                    WtTestScenarioAction action = createAction(data);
                    registActions.add(action);

                    // 子アクションの登録開始／終了
                    if (action.getActionType().startChildAction()) {
                        registActions = action.getChildActions();
                    } else if (action.getActionType().endChildAction()) {
                        registActions = actions;
                    }
                }

            } finally {
                br.close();
            }

        } catch (IOException e) {
            throw new RuntimeException("CSVファイル読込み失敗", e);
        }
    }

    /**
     * シナリオアクションを作成します.
     * @param data シナリオデータ１行分
     * @return シナリオアクション
     */
    private WtTestScenarioAction createAction(String[] data) {
        Map<CsvKeys, String> actionParams = new HashMap<>();
        actionParams.put(CsvKeys.ACT_TYPE, data[0]);
        actionParams.put(CsvKeys.ACT_PARAM_1, data[1]);
        actionParams.put(CsvKeys.ACT_PARAM_2, data[2]);
        actionParams.put(CsvKeys.ACT_PARAM_3, data[3]);
        actionParams.put(CsvKeys.ACT_PARAM_4, data[4]);
        actionParams.put(CsvKeys.ACT_PARAM_5, data[5]);

        return new WtTestScenarioAction(actionParams);
    }

    /**
     * 共通変数を準備します.
     */
    private void prepareCommonScenarioValues() {
        // カレントディレクトリ
        scenarioValues.put("@ROOT_PATH", System.getProperty("user.dir"));
        // スクリーンショット保存先
        scenarioValues.put("@SC_SAVEPATH", System.getProperty("user.dir") + "/../");
        // スクリーンショットファイル連番
        scenarioValues.put("@SC_SEQ", 1);
    }

    /**
     * シナリオ内のアクション件数を返します.
     * @return アクション件数
     */
    private int getActionCount() {
        return actions.size();
    }

    /**
     * 次のアクションを返します.
     * @return アクション（次が存在しない場合はnull）
     */
    private WtTestScenarioAction getNextAction() {
        currentActionNo++;
        if (getActionCount() <= currentActionNo) {
            return null;
        }

        currentAction = actions.get(currentActionNo);
        return currentAction;
    }

    /**
     * テストシナリオを実行します.
     * @param driver WEBドライバ
     */
    public void execute(WtWebDriver driver) {
        while (getNextAction() != null) {
            currentAction.execute(driver, scenarioValues);
        }
    }
}
