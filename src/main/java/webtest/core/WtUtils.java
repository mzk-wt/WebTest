package webtest.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import webtest.keys.ByType;

public class WtUtils {

    /**
     * 文字列がBlankかどうかを判定
     * @param value 検査対象の文字列
     * @return true=blank / false=not blank
     */
    public static boolean isBlank(String value) {
        if (value == null) {
            return true;
        } else if (value.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 文字列がBlankではないことを判定
     * @param value 検査対象の文字列
     * @return true=not blank / false=blank
     */
    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    /**
     * オプション文字列を解析してマップに格納
     * @param optionString オプション文字列
     *                     key=value[;key=value[;...]]
     * @return オプションマップ
     */
    public static Map<String, String> parseOption(String optionString) {
        Map<String, String> optionMap = new HashMap<>();
        if (isNotBlank(optionString)) {
            String[] options = split(optionString, ";");
            for (String option : options) {
                String[] o = split(option, "=");
                optionMap.put(o[0], o[1]);
            }
        }

        return optionMap;
    }

    /**
     * 文字列に埋め込まれた変数部分を値に置換
     * @param text 文字列（"{{変数名}}"とすることで変数を埋め込むことができる）
     * @param values 変数マップ
     * @return 置換後の文字列
     */
    public static String formatValues(String text, Map<String, Object> values) {
        Pattern p = Pattern.compile("\\{\\{(.*?)\\}\\}");
        Matcher m = p.matcher(text);

        while (m.find()) {
            String key = m.group(1).trim();
            if (isNotBlank(key) && values.containsKey(key)) {
                String regex = Pattern.quote(m.group(0));
                text = text.replaceAll(regex, String.valueOf(values.get(key)));
            }
        }

        return text;
    }

    /**
     * 文字列に埋め込まれた変数部分からオブジェクトを取得
     * @param text 文字列（"{{変数名}}"とすることで変数を埋め込むことができる）
     * @param values 変数マップ
     * @return 変数マップに格納されているオブジェクト
     */
    public static Object getObjectFromValuesMap(String text, Map<String, Object> values) {
        Pattern p = Pattern.compile("\\{\\{(.*?)\\}\\}");
        Matcher m = p.matcher(text);

        Object obj = null;
        if (m.find()) {
            String key = m.group(1).trim();
            if (isNotBlank(key) && values.containsKey(key)) {
                obj = values.get(key);
            }
        }

        return obj;
    }

    /**
     * 文字列を分割（カンマ区切り）
     * @param value 文字列
     * @return 文字列配列
     */
    public static String[] split(String value) {
        return split(value, -1);
    }

    /**
     * 文字列を分割
     * @param value 文字列
     * @param delim 区切り文字
     * @return 文字列配列
     */
    public static String[] split(String value, String delim) {
        return split(value, delim, -1);
    }

    /**
     * 文字列を分割（カンマ区切り）
     * @param value 文字列
     * @param size 分割後の配列の大きさ
     * @return 文字列配列
     */
    public static String[] split(String value, int size) {
        return split(value, ",", size);
    }

    /**
     * 文字列を分割
     * @param value 文字列
     * @param delim 区切り文字
     * @param size 分解後の配列の大きさ
     * @return 文字列配列
     */
    public static String[] split(String value, String delim, int size) {
        String[] values = value.split(delim);
        if (-1 < size) {
            values = Arrays.copyOf(values, size);
        }
        return values;
    }

    /**
     * ファイルを削除.(サブディレクトリが存在する場合、まとめて削除する)
     * @param path ファイルパス
     * @throws IOException
     */
    public static void deleteFiles(Path path) throws IOException {
        // サブディレクトリも削除
        if (Files.exists(path)) {

            //ファイル存在チェック
            if (!Files.isDirectory(path)) {
                //存在したら削除する
                Files.delete(path);

            //対象がディレクトリの場合
            } else {
                //ディレクトリ内の一覧を取得
                File[] files = path.toFile().listFiles();

                //存在するファイル数分ループして再帰的に削除
                for (int i = 0; i < files.length; i++) {
                    deleteFiles(files[i].toPath());
                }

                //ディレクトリを削除する
                Files.delete(path);
            }
        }
    }

    /**
     * DOM要素を取得
     * @param driver WEBドライバ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @param type ByType or "ELEM"
     * @param query type=ByTypeの場合、CSV形式（[0]要素を特定するための文字列 [1]要素番号（省略時0））
     *             type="ELEM"の場合、valuesのキー
     * @return DOM要素
     */
    public static WebElement getWebElement(WtWebDriver driver, Map<String, Object> values, String type, String... query) {
        if ("ELEM".equals(type)) {
            WebElement elem = (WebElement) getObjectFromValuesMap(query[0], values);
            return elem;

        } else {
            ByType byType = ByType.CSS;
            if (WtUtils.isNotBlank(type)) {
                byType = ByType.valueOf(type.toUpperCase());
            }
            By by = byType.getByInstance(WtUtils.formatValues(query[0], values));
            if (1 < query.length && isNotBlank(query[1])) {
                int index = Integer.parseInt(WtUtils.formatValues(query[1], values));
                return driver.findElements(by).get(index);

            } else {
                return driver.findElement(by);
            }
        }
    }

    /**
     * 複数のDOM要素を取得
     * @param driver WEBドライバ
     * @param values シナリオ内で取得した値を持ち運ぶためのマップ
     * @param type ByType
     * @param query 要素を特定するための文字列
     * @return DOM要素のリスト
     */
    public static List<WebElement> getWebElements(WtWebDriver driver, Map<String, Object> values, String type, String query) {
        ByType byType = ByType.CSS;
        if (WtUtils.isNotBlank(type)) {
            byType = ByType.valueOf(type.toUpperCase());
        }
        By by = byType.getByInstance(WtUtils.formatValues(query, values));

        return driver.findElements(by);
    }
}
