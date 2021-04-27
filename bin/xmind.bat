@echo 输入XMind路径 %1
@echo BLOG_HOME %BLOG_HOME%

java -Xbootclasspath/a:./ -jar ../libs/Xmind2MD-1.0.0.jar


python %BLOG_HOME%\bin\xmind2md.py -source %1




::，pause