改行が多く含まれた文章に対してSudachiによる形態素解析を行うと非常に遅くなる場合がある。
その状態を再現するためのコード。

system_core.dicだけapp/src/main/resourcesに入れる必要がある

# 再現コード1
SudachiPerfTest.java
通常の文章、1文字ごとにスペース区切りの文章、1文字ごとに改行区切りの文章に対して、tokenizeSentencesを100回行うコード。
```
plain text
8434ms
----------------------
space separated text. length:8822
5459ms
----------------------
space separated text. length:17644
12029ms
----------------------
newline separated text. length:8822
94093ms
----------------------
newline separated text. length:17644
478105ms
----------------------
```
プロファイリングをしてみると、parenthesisLevelメソッドが遅そう。
![プロファイリング結果](doc/cpu_time.png)


# 結論
* 改行が多い文章の場合、文章の長さに応じて処理時間が増大する(たぶん指数関数的に増大している)

# 計測条件
MacBook Pro (15-inch, 2017)
2.8 GHz クアッドコアIntel Core i7
16 GB 2133 MHz LPDDR3
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.6+10, mixed mode)