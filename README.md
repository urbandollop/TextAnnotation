# TextAnnotation
文本标注系统

需要开发的功能：
 
登录页面

普通用户：
项目列表页面（显示参加项目-文书列表页面）
文书列表页面（显示需标注文书列表（标注过的标灰）-标注页面）
标注页面（提交-下一篇文书标注页面）（都为（update））
修改密码页面

经理用户：
项目管理页面（显示已有项目-项目文书标注情况列表页面，添加项目-添加项目页面）
添加项目页面（提交-用户分配页面）（初始化judgement表）
用户分配页面（提交-项目文书标注情况列表页面）
项目文书标注情况列表页面-（导出结果-导出结果页面）
导出结果页面（选择法条-导出-预览页面）
预览页面（下载excel）
普通用户页面也包含在内
新建用户页面？不确定，需确认
导入页面？


文本标注系统数据库设计，加粗为主键，数据库中表名，字段名字母都为小写

文书表 instrument
字段名	数据类型	说明
instrument_id	varchar	id，例：005_1305111.xml_ft2jl_xsys(在后面加_xsys，代表刑事一审)
num	int	序号，例： 5
xml	varchar	文书名，例：1305111.xml

法条表 statute
字段名	数据类型	说明
statute_id	varchar	id,使用规则编码
lxxx.aaa-bb.cc.dd.ee
法合，法条序号，之序，款序，项，目
name	varchar	法条名称
text	text	法条内容

事实表 fact
字段名	数据类型	说明
fact_id	int,自增	id 
instrument_id	varchar	文书id
text	text	事实内容
num	int	文书中顺序

文书-法条（文书引用哪些法条）表 instrumentandstatute
字段名	数据类型	说明
instrument_id	varchar	文书id，
statute_id	varchar	法条id，
num	int	文书中顺序

用户表user
字段名	数据类型	说明
user_id	int，自增	id
name	varchar	用户名
password	varchar	密码
role	int	角色（普通用户为0，经理为1）

用户分配任务表task
字段名	数据类型	说明
task_id	int，自增	id
user_id	int	用户id
project_id	int	项目id
begin	int	分配文书开始序号
end	int	分配文书结束序号

判断表judgement 
字段名	数据类型	说明
judgement_id	int，自增	id
user_id	int	用户id
fact_id	int	事实id
statute_id	varchar	法条id
isrelated	int	是否相关，0不相关，1相关，-1表示还未判断
project_id	int	项目id

项目表project
字段名	数据类型	说明
project_id	int,自增	项目id
name	varchar	项目名称
starttime	datetime	项目建立时间


