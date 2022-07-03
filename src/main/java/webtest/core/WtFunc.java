package webtest.core;

public class WtFunc {

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
}
