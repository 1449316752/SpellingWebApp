# SpellingWebApp：拼写背单词

## 一、简介

1. 在学习编程和英语的过程中，我想到可以通过自己创建一个网站用来学习英语单词。
2. 简单的描述一下形式：拼写单词开始和结束时，需要播放单词的读音和中文读音，在拼写的过程中达到将 音、意、形 关联起来的目的，以此增加背单词的效率。
3. 目前（2023年4月1日），在前端上我学习了Vue框架，后端学习了SpringBoot，想尝试在学习的基础上制作一个简单的web项目。

4. 该项目参考[https://github.com/Kaiyiwing/qwerty-learner](https://github.com/Kaiyiwing/qwerty-learner)的前端表现形式

## 二、项目描述

1. 功能
   - 拼写学习功能
     - 拼写单词
     - 切换单词本
     - 切换组，可自定义每组的个数
   - 模式功能
     - 正常模式：进行正常的单词拼写，点击按钮发声
     - 洗脑模式：不断重复发声
     - 按需提示单词内容：中文、英文、提示等自定义显示与否
   - 学习记录功能
   - 用户功能
  
   
   效果图：
   ![image](https://github.com/1449316752/SpellingWebApp/assets/93976868/cc87c386-e34d-4888-a599-c86c24ecc3fd)


<br/>


## 三、数据库设计

1. 用户表：user
   |字段名|类型|是否为空|主键/外键|备注|
   |--|--|--|--|--|
   |u_id|int|false|主键|id|
   |username|varchar|false||用户名|
   |password|varchar|false||密码|
2. 单词表（有多个）：word_cet4_h（四级高频1162个）
   |字段名|类型|是否为空|主键/外键|备注|
   |--|--|--|--|--|
   |w_id|int|false|主键|id|
   |word|varchar|false||单词本身|
   |tranCn|varchar|false||中文释义|

3. 用户学习记录表（与单词表对应）：record_cet4_h
   |字段名|类型|是否为空|主键/外键|备注|
   |--|--|--|--|--|
   |r_id|long|false|主键|记录id|
   |u_id|int|false|外键|用户id|
   |w_id|int|false|外键|单词id|
   |level|int|默认为0||单词级别|
   |isgrasp|int|默认为0||是否已掌握|
