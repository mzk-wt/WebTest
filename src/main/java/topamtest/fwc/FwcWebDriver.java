package topamtest.fwc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import topamtest.fwc.keys.InputKeys;

/**
 * WEBドライバのラッパークラス
 */
public class FwcWebDriver {

    /** WEBドライバのインスタンス */
    private WebDriver driver;

    /**
     * コンストラクタ.
     * @params params 初期化パラメータ
     */
    public FwcWebDriver(Map<InputKeys, String> params) {
        // TODO
        if ("chrome".equals(params.get(InputKeys.BROWSE_TYPE))) {
            System.setProperty("webdriver.chrome.driver", params.get(InputKeys.BROWSE_DRIVER));
            driver = new ChromeDriver();
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
        return driver.findElement(by);
    }

    /**
     * テキストを取得します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link FwcWebDriver#findElement(By)})
     * @return テキストの値
     */
    public String getText(By by) {
        WebElement elem = findElement(by);
        return elem.getText();
    }

    /**
     * 属性を取得します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link FwcWebDriver#findElement(By)})
     * @param attr 属性
     * @return 属性の値
     */
    public String getAttribute(By by, String attr) {
        WebElement elem = findElement(by);
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
            Thread.sleep(10000);
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
     * @param by 要素を特定するByクラスのインスタンス(@see {@link FwcWebDriver#findElement(By)})
     * @param keys 入力する値
     */
    public void sendKeys(By by, String keys) {
        WebElement elem = findElement(by);
        elem.sendKeys(keys);
    }

    /**
     * 順番を指定してドロップダウンを選択します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link FwcWebDriver#findElement(By)})
     * @param index 順番
     */
    public void selectByIndex(By by, int index) {
        WebElement elem = findElement(by);
        Select select = new Select(elem);
        select.selectByIndex(index);
    }

    /**
     * value属性の値を指定してドロップダウンを選択します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link FwcWebDriver#findElement(By)})
     * @param value value属性の値
     */
    public void selectByValue(By by, String value) {
        WebElement elem = findElement(by);
        Select select = new Select(elem);
        select.selectByValue(value);
    }

    /**
     * 表示テキストを指定してドロップダウンを選択します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link FwcWebDriver#findElement(By)})
     * @param text 表示テキスト
     */
    public void selectByVisibleText(By by, String text) {
        WebElement elem = findElement(by);
        Select select = new Select(elem);
        select.selectByVisibleText(text);
    }

    /**
     * 要素をクリックします.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link FwcWebDriver#findElement(By)})
     */
    public void clickElement(By by) {
        findElement(by).click();
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
     * @param by 要素を特定するByクラスのインスタンス(@see {@link FwcWebDriver#findElement(By)})
     */
    public void scrollToElement(By by) {
        WebElement elem = findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(elem);
        actions.perform();
    }

    /*****************************************************************
     * その他の処理
     *****************************************************************/
    /**
     * 要素が表示されているかどうかを判定します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link FwcWebDriver#findElement(By)})
     * @return true=表示／false=非表示
     */
    public boolean isDisplaye(By by) {
        WebElement elem = findElement(by);
        return elem.isDisplayed();
    }

    /**
     * 要素が有効かどうかを判定します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link FwcWebDriver#findElement(By)})
     * @return true=有効／false=無効
     */
    public boolean isEnabled(By by) {
        WebElement elem = findElement(by);
        return elem.isEnabled();
    }

    /**
     * 要素が選択されているかどうかを判定します.
     * @param by 要素を特定するByクラスのインスタンス(@see {@link FwcWebDriver#findElement(By)})
     * @return true=選択されている／false=選択されていない
     */
    public boolean isSelected(By by) {
        WebElement elem = findElement(by);
        return elem.isSelected();
    }
}
