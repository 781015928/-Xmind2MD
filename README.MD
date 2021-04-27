# Xmind 命令行工具

## 将xmind树形结构转化为MD文档结构


## 配置环境变量
### 1.配置java环境

### 2.配置xmind工具环境变量

## 编译

### windows:
```bash
cd Xmind2MD
gradlew.bat pack
build/libs/Xmind2MD-1.0.0.zip
```

### linux:
```bash
cd Xmind2MD
./gradlew pack
build/libs/Xmind2MD-1.0.0.zip
```

## 用法 -h

```bash
usage: [-h,-2md,-p]
----------------------------------------------------------------------
 -2md,--xmind2md   将输入Xmind转换为MD文档
 -h,--help         usage help
 -p,--println      将输入Xmind打印到控制台
----------------------------------------------------------------------
```
```bash
xmind -2md -i D:/test.xmind -o D:/test.MD
```

## 语法

|   xmind开头语法|描述                           |
|:-------------:|:----------------------------:|
|     @         |表示该记录下所有子目录不显示标题  |
|-              |表示该记录跳过标记              |
|code:language  |  代码块                      | 
|      [0-9]    |不会为该条目增加索引            |


## 支持代码
代码块使用时，在代码xmind条目第一行增加code:language

###示例

#### shell

```
code:bash
#！/bin/bash
...

```
#### java

```
code:java
public class Main{
    public static void main(String args[]){
        System.out.println("Hello xmind");
    }
}

```
### 支持代码一览

|   language|示例                           |
|:-------------:|:----------------------------:|
|bash|code:bash|
|java|code:java|
|scala|code:scala|
|xml|code:xml|
|code|code:code|
|css|code:css|
|javascript|code:javascript|
|c|code:c|
|c++|code:c++|
|dart|code:dart|
|docker|code:docker|
|git|code:git|
|groovy|code:groovy|
|json|code:json|
|go|code:go|
|php|code:php|
|ruby|code:ruby|
|python|code:python|
|kotlin|code:kotlin|
|sql|code:sql|
|yaml|code:yaml|

## Future

### 1.支持xmind表格转换至MD
