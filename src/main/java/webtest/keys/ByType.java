package webtest.keys;

import org.openqa.selenium.By;

public enum ByType {
    // クラス名
    CLASS,
    // CSSセレクタ
    CSS,
    // ID属性
    ID,
    // リンク文字列
    LINK,
    // name属性
    NAME,
    // リンク文字列の部分一致
    PLINK,
    // タグ名
    TAG,
    // XPath
    XPATH;

    public By getByInstance(String query) {
        if (this.equals(ByType.CLASS)) {
            return By.className(query);

        } else if (this.equals(ByType.CSS)) {
            return By.cssSelector(query);

        } else if (this.equals(ByType.ID)) {
            return By.id(query);

        } else if (this.equals(ByType.LINK)) {
            return By.linkText(query);

        } else if (this.equals(ByType.NAME)) {
            return By.name(query);

        } else if (this.equals(ByType.PLINK)) {
            return By.partialLinkText(query);

        } else if (this.equals(ByType.TAG)) {
            return By.tagName(query);

        } else if (this.equals(ByType.XPATH)) {
            return By.xpath(query);

        } else {
            return null;
        }
    }
}
