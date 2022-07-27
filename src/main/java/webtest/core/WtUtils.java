package webtest.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
                text = text.replaceAll(regex, String.valueOf(values.get(key)));
            }
        }

        return text;
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
}
