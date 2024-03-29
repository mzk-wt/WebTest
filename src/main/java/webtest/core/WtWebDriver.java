package webtest.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import webtest.keys.InputKeys;

/**
 * WEBドライバのラッパークラス
 */
public class WtWebDriver {

    /** WEBドライバのインスタンス */
    private WebDriver driver;

    public static final String BROWSE_TYPE_CHROME = "chrome";
    public static final String BROWSE_TYPE_EDGE = "edge";
    public static final String BROWSE_TYPE_FIREFOX = "firefox";

    /**
     * コンストラクタ.
     * @params params 初期化パラメータ
     */
    public WtWebDriver(Map<InputKeys, String> params) {
        String browseType = params.get(InputKeys.BROWSE_TYPE);
        if (BROWSE_TYPE_CHROME.equals(browseType)) {
            driver = WebDriverManager.chromedriver().create();

        } else if (BROWSE_TYPE_EDGE.equals(browseType)) {
            driver = WebDriverManager.edgedriver().create();

        } else if (BROWSE_TYPE_FIREFOX.equals(browseType)) {
            driver = WebDriverManager.firefoxdriver().create();
        }
    }

    /*****************************************************************
     * ページ遷移／ブラウザ操作系の処理
     *****************************************************************/
    /**
     * 指定されたURLを表示します.
     * @param url
     */
    public void get(String url) {
        driver.get(url);
    }

    /**
     * ページ遷移します.
     * @param url
     */
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    /**
     * 一つ戻る.
     */
    public void navigateBack() {
        driver.navigate().back();
    }

    /**
     * 一つ進む.
     */
    public void navigateForward() {
        driver.navigate().forward();
    }

    /**
     * ブラウザを更新します.
     */
    public void navigateRefresh() {
        driver.navigate().refresh();
    }

    /**
     * 処理を待機します.
     * @param time 待機時間（秒）
     */
    public void waitLoading(long time) {
        driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
    }

    /**
     * ウィンドウハンドルを取得します.
     * @return カレントウィンドウのハンドル
     */
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    /**
     * ウィンドウハンドルを取得します.
     * @return 全てのウィンドウのハンドル
     */
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    /**
     * ウィンドウを切り替えます.
     */
    public void switchWindow(String nameOrHandle) {
        driver.switchTo().window(nameOrHandle);
    }

    /**
     * ウィンドウを閉じます.
     */
    public void close() {
        driver.close();
    }

    /**
     * すべてのウィンドウを閉じます.
     */
    public void quit() {
        driver.quit();
    }

    /**
     * ウィンドウを最大化します.
     */
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    /**
     * ウィンドウのサイズをセットします.
     * @param w 横のサイズ
     * @param h 縦のサイズ
     */
    public void setWindowSize(int w, int h) {
        Dimension size = new Dimension(w, h);
        driver.manage().window().setSize(size);
    }

    /**
     * スクロールします.
     * @param xOffset 横方向の移動量
     * @param yOffset 縦方向の移動量
     */
    public void scroll(int xOffset, int yOffset) {
        Actions actions = new Actions(driver);
        actions.moveByOffset(xOffset, yOffset);
        actions.perform();
    }

    /**
     * 指定された要素までスクロールします.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     */
    public void scrollToElement(By by) {
        WebElement elem = findElement(by);
        scrollToElement(elem);
    }

    /**
     * 指定された要素までスクロールします.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param elemNo 要素番号
     */
    public void scrollToElement(By by, int elemNo) {
        List<WebElement> elem = findElements(by);
        scrollToElement(elem.get(elemNo));
    }

    /**
     * 指定された要素までスクロールします.
     * @param elem 要素
     */
    public void scrollToElement(WebElement elem) {
        Actions actions = new Actions(driver);
        actions.moveToElement(elem);
        actions.perform();
    }

    /*****************************************************************
     * 取得系の処理
     *****************************************************************/
    /**
     * 現在のURLを取得します.
     * @return 現在のURL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * タイトルを取得します.
     * @return タイトル
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * ページのソースを取得します.
     * @return ページのソース
     */
    public String getPageSource() {
        return driver.getPageSource();
    }

    /**
     * 要素を取得します.
     * <pre>
     * (Usage)
     *   findElement(By.className("classname")):クラス名で指定する
     *   findElement(By.cssSelector("css selector")):CSSセレクタで指定する
     *   findElement(By.id("id")):IDで指定する
     *   findElement(By.linkText("link text"):リンク文字列で指定する
     *   findElement(By.name("name"):name属性で指定する
     *   findElement(By.partialLinkText("partial link text"):リンク文字列を部分一致で指定する
     *   findElement(By.tagName("tag name"):タグ名で指定する
     *   findElement(By.xpath("xpath"):XPathで指定する
     * </pre>
     * @param by 要素を特定するByクラスのインスタンス
     * @return
     */
    public WebElement findElement(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(1).getSeconds())
                .until(driver -> driver.findElement(by));
    }

    /**
     * 要素を取得します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @return
     */
    public List<WebElement> findElements(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(1).getSeconds())
                .until(driver -> driver.findElements(by));
    }

    /**
     * テキストを取得します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @return テキストの値
     */
    public String getText(By by) {
        WebElement elem = findElement(by);
        return getText(elem);
    }

    /**
     * テキストを取得します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param elemNo 要素番号
     * @return テキストの値
     */
    public String getText(By by, int elemNo) {
        List<WebElement> elem = findElements(by);
        return getText(elem.get(elemNo));
    }

    /**
     * テキストを取得します.
     * @param elem 要素
     * @return テキストの値
     */
    public String getText(WebElement elem) {
        return elem.getText();
    }

    /**
     * 属性を取得します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param attr 属性
     * @return 属性の値
     */
    public String getAttribute(By by, String attr) {
        WebElement elem = findElement(by);
        return getAttribute(elem, attr);
    }

    /**
     * 属性を取得します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param attr 属性
     * @param elemNo 要素番号
     * @return 属性の値
     */
    public String getAttribute(By by, String attr, int elemNo) {
        List<WebElement> elem = findElements(by);
        return getAttribute(elem.get(elemNo), attr);
    }

    /**
     * 属性を取得します.
     * @param elem 要素
     * @param attr 属性
     * @return 属性の値
     */
    public String getAttribute(WebElement elem, String attr) {
        return elem.getAttribute(attr);
    }

    /**
     * スクリーンショットをファイルに保存します.
     * @return ファイル
     */
    public File getScreenshotAsFile() {
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // ファイルが保存されるのを待機
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return f;
    }

    /**
     * スクリーンショットのファイルを指定されたフォルダに保存します.
     * @param path 保存先のパス
     * @param filename 保存ファイル名
     * @return true=成功／false=失敗
     */
    public boolean saveScreenshotAsFile(String path, String filename) {
        // 出力先フォルダが存在しない場合、作成する
        Path out = Paths.get(path);
        if (Files.notExists(out)) {
            out.toFile().mkdirs();
        }

        File f = getScreenshotAsFile();

        Path srcPath = Paths.get(f.getAbsolutePath());
        Path dstPath = Paths.get(path + File.separator + filename);

        try {
            Files.copy(srcPath, dstPath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /*****************************************************************
     * 入力／操作系の処理
     *****************************************************************/
    /**
     * キーボードから入力を行います.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param keys 入力する値
     */
    public void sendKeys(By by, String keys) {
        WebElement elem = findElement(by);
        sendKeys(elem, keys);
    }

    /**
     * キーボードから入力を行います.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param keys 入力する値
     * @param elemNo 要素番号
     */
    public void sendKeys(By by, String keys, int elemNo) {
        List<WebElement> elem = findElements(by);
        sendKeys(elem.get(elemNo), keys);
    }

    /**
     * キーボードから入力を行います.
     * @param elem 要素
     * @param keys 入力する値
     */
    public void sendKeys(WebElement elem, String keys) {
        elem.sendKeys(keys);
    }

    /**
     * 順番を指定してドロップダウンを選択します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param index 順番
     */
    public void selectByIndex(By by, int index) {
        WebElement elem = findElement(by);
        selectByIndex(elem, index);
    }

    /**
     * 順番を指定してドロップダウンを選択します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param index 順番
     * @param elemNo 要素番号
     */
    public void selectByIndex(By by, int index, int elemNo) {
        List<WebElement> elem = findElements(by);
        selectByIndex(elem.get(elemNo), index);
    }

    /**
     * 順番を指定してドロップダウンを選択します.
     * @param elem 要素
     * @param index 順番
     */
    public void selectByIndex(WebElement elem, int index) {
        Select select = new Select(elem);
        select.selectByIndex(index);
    }

    /**
     * value属性の値を指定してドロップダウンを選択します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param value value属性の値
     */
    public void selectByValue(By by, String value) {
        WebElement elem = findElement(by);
        selectByValue(elem, value);
    }

    /**
     * value属性の値を指定してドロップダウンを選択します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param value value属性の値
     * @param elemNo 要素番号
     */
    public void selectByValue(By by, String value, int elemNo) {
        List<WebElement> elem = findElements(by);
        selectByValue(elem.get(elemNo), value);
    }

    /**
     * value属性の値を指定してドロップダウンを選択します.
     * @param elem 要素
     * @param value value属性の値
     */
    public void selectByValue(WebElement elem, String value) {
        Select select = new Select(elem);
        select.selectByValue(value);
    }

    /**
     * 表示テキストを指定してドロップダウンを選択します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param text 表示テキスト
     */
    public void selectByVisibleText(By by, String text) {
        WebElement elem = findElement(by);
        selectByVisibleText(elem, text);
    }

    /**
     * 表示テキストを指定してドロップダウンを選択します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param text 表示テキスト
     * @param elemNo 要素番号
     */
    public void selectByVisibleText(By by, String text, int elemNo) {
        List<WebElement> elem = findElements(by);
        selectByVisibleText(elem.get(elemNo), text);
    }

    /**
     * 表示テキストを指定してドロップダウンを選択します.
     * @param elem 要素
     * @param text 表示テキスト
     */
    public void selectByVisibleText(WebElement elem, String text) {
        Select select = new Select(elem);
        select.selectByVisibleText(text);
    }

    /**
     * 要素をクリックします.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     */
    public void clickElement(By by) {
        findElement(by).click();
    }

    /**
     * 要素をクリックします.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param elemNo 要素番号
     */
    public void clickElement(By by, int elemNo) {
        findElements(by).get(elemNo).click();
    }

    /**
     * 要素をクリックします.
     * @param elem 要素
     */
    public void clickElement(WebElement elem) {
        elem.click();
    }

    /**
     * Javascriptを実行します.
     * @param script 実行するスクリプト
     * @param args パラメータ
     */
    public void execScript(String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, args);
    }

    /*****************************************************************
     * その他の処理
     *****************************************************************/
    /**
     * 要素が表示されているかどうかを判定します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @return true=表示／false=非表示
     */
    public boolean isDisplayed(By by) {
        WebElement elem = findElement(by);
        return isDisplayed(elem);
    }

    /**
     * 要素が表示されているかどうかを判定します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param elemNo 要素番号
     * @return true=表示／false=非表示
     */
    public boolean isDisplayed(By by, int elemNo) {
        List<WebElement> elem = findElements(by);
        return isDisplayed(elem.get(elemNo));
    }

    /**
     * 要素が表示されているかどうかを判定します.
     * @param elem 要素
     * @return true=表示／false=非表示
     */
    public boolean isDisplayed(WebElement elem) {
        return elem.isDisplayed();
    }

    /**
     * 要素が有効かどうかを判定します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @return true=有効／false=無効
     */
    public boolean isEnabled(By by) {
        WebElement elem = findElement(by);
        return isEnabled(elem);
    }

    /**
     * 要素が有効かどうかを判定します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param elemNo 要素番号
     * @return true=有効／false=無効
     */
    public boolean isEnabled(By by, int elemNo) {
        List<WebElement> elem = findElements(by);
        return isEnabled(elem.get(elemNo));
    }

    /**
     * 要素が有効かどうかを判定します.
     * @param elem 要素
     * @return true=有効／false=無効
     */
    public boolean isEnabled(WebElement elem) {
        return elem.isEnabled();
    }

    /**
     * 要素が選択されているかどうかを判定します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @return true=選択されている／false=選択されていない
     */
    public boolean isSelected(By by) {
        WebElement elem = findElement(by);
        return isSelected(elem);
    }

    /**
     * 要素が選択されているかどうかを判定します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link WtWebDriver#findElement(By)})
     * @param elemNo 要素番号
     * @return true=選択されている／false=選択されていない
     */
    public boolean isSelected(By by, int elemNo) {
        List<WebElement> elem = findElements(by);
        return isSelected(elem.get(elemNo));
    }

    /**
     * 要素が選択されているかどうかを判定します.
     * @param elem 要素
     * @return true=選択されている／false=選択されていない
     */
    public boolean isSelected(WebElement elem) {
        return elem.isSelected();
    }
}
