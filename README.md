# OTools

一直想在手机的负一屏上有一个倒计时的插件，但是找了好多APP，都没能找到比较满意的，这种倒计时的APP好多Widget都做得不好，偶尔几个好的又不能和系统的插件风格统一，放在负一屏不好看，于是干脆自己写了这个APP

## 功能完成情况

2018-09-02

- 初步完成纪念日基本功能的开发

2018-09-08

- 完善通知功能，倒计时，周年纪念，在当天9点进行通知
- Widget自动刷新时间修改为1小时

2018-09-09

- 优化对日期的处理方式
- 修复修改纪念日之后返回主界面，列表不刷新的问题

2018-09-09

- 修复一个计算错误问题
- 移除一个不必要的服务

2018-09-17

- 新增widget可以选择显示的纪念日，可以添加多个widget

2018-09-18

- 修复删除widget时，数据库中对应的数据无法删除或者可能错删除的问题

2018-09-19

- 优化widget的布局
- 修复只能有一个widget能够响应点击事件的bug

2018-09-20

- 修复有多个通知时只有一个通知能跳转的bug

2018-09-21

- ~~修复不能修改纪念日日期为当前日期的bug(虽然很奇怪，但这个bug似乎是Litepal引起的)~~ 代码强迫症，这个bug不修了
- 浮动按钮自动隐藏，目前碰到问题，隐藏之后无法重新显示，先同步下代码吧...

2018-10-14

- 修复状态栏颜色问题