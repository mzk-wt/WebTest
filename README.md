# WebTest
WebアプリのUIテスト自動化ツール

## Feature
- テキスト（CSV）によるテストシナリオ実行・管理
- Seleniumによるテスト実行（マルチブラウザ対応）
- Javaによる拡張可能　※準備中
- GUIによるテストシナリオ編集　※準備中

## Usage
### Install
1. クローン

```
git clone https://github.com/mzk-wt/WebTest.git
```
2. Gradleプロジェクトとしてインポート

### Execution
1. シナリオ作成
2. webtest.WebTest.javaを実行  
【コマンドラインパラメータ】  
　[0]ブラウザ種別（"chrome", "edge", "firefox"）  
　[1]出力フォルダのパス（フルパス）  
　[2]テストシナリオのパス（フルパス）  

## Sample
テストコード参照

## Reference
### Scenario
#### Format
テストシナリオは以下のフォーマットでCSVファイルを用意する
```
<アクションコード>,<パラメータ１>,<パラメータ2>,<パラメータ3>,<パラメータ4>,<パラメータ5>
```

#### Actions
ページ遷移／ブラウザ操作系アクション
|アクションコード|パラメータ1|パラメータ2|パラメータ3|パラメータ4|パラメータ5|
|:---|:---|:---|:---|:---|:---|
|||||||

取得系アクション
|アクションコード|パラメータ1|パラメータ2|パラメータ3|パラメータ4|パラメータ5|
|:---|:---|:---|:---|:---|:---|
|||||||

入力系アクション
|アクションコード|パラメータ1|パラメータ2|パラメータ3|パラメータ4|パラメータ5|
|:---|:---|:---|:---|:---|:---|
|||||||

その他のアクション
|アクションコード|パラメータ1|パラメータ2|パラメータ3|パラメータ4|パラメータ5|
|:---|:---|:---|:---|:---|:---|
|||||||
