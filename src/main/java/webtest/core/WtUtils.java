package webtest.core;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                text = text.replaceAll(regex, (String) values.get(key));
            }
        }

        return text;
    }
}
