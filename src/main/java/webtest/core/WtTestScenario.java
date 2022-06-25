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

import webtest.core.keys.CsvKeys;
import webtest.core.keys.InputKeys;

/**
 * シナリオクラス
 */
public class WtTestScenario {

    /** シナリオ内で実行するアクションリスト */
    private List<WtTestScenatioAction> actions = new ArrayList<>();

    /** 現在実行中のアクション番号 */
    private int currentActionNo = -1;

    /** 現在実行中のアクション */
    private WtTestScenatioAction currentAction;

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
        // CSVファイルを1行ずつ読込んでシナリオアクションを初期化する
        BufferedReader br = null;
        try {
            try {
                String fileName = params.get(InputKeys.SCENARIO_FILE);
                br = new BufferedReader(new FileReader(new File(fileName)));

                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = Arrays.copyOf(line.split(","), 6);

                    Map<CsvKeys, String> actionParams = new HashMap<>();
                    actionParams.put(CsvKeys.ACT_TYPE, data[0]);
                    actionParams.put(CsvKeys.ACT_PARAM_1, data[1]);
                    actionParams.put(CsvKeys.ACT_PARAM_2, data[2]);
                    actionParams.put(CsvKeys.ACT_PARAM_3, data[3]);
                    actionParams.put(CsvKeys.ACT_PARAM_4, data[4]);
                    actionParams.put(CsvKeys.ACT_PARAM_5, data[5]);

                    actions.add(new WtTestScenatioAction(actionParams));
                }

            } finally {
                br.close();
            }

        } catch (IOException e) {
            throw new RuntimeException("CSVファイル読込み失敗", e);
        }
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
    private WtTestScenatioAction getNextAction() {
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
            currentAction.execute(driver);
        }
    }
}