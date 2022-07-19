-- 初始化临时表 日志文件属性提取
DROP TABLE IF EXISTS logid_tmp1;
CREATE TABLE logid_tmp1 (
     id BIGINT,
     ip STRING,
     ip_num BIGINT,      -- ip对应的十进制数
     ip_1 BIGINT,        -- ip首位数字
     access_time STRING, -- 访问时间
     url STRING,         -- 访问链接
     status_code STRING,      -- http状态码
     traffic STRING,     -- 流量
     referer STRING,     -- 来源
     c_info STRING       -- 客户端信息
);
-- 初始化IP表
DROP TABLE IF EXISTS cz_ip;
CREATE TABLE cz_ip (
   ip_start BIGINT,   -- 起始ip对应的十进制
   ip_start_1 STRING, -- 起始ip首位
   ip_end BIGINT,     -- 结束ip对应的十进制
   city STRING,       -- 城市
   isp STRING         -- 运营商
);
-- 初始化临时表 信息合并（地域及运营商）
DROP TABLE IF EXISTS logid_tmp2;
CREATE TABLE logid_tmp2 (
     id BIGINT,
     ip STRING,
     city STRING,
     isp STRING,
     access_time STRING,
     url STRING,
     status_code STRING,
     traffic STRING,
     referer STRING,
     c_info STRING
);
-- 初始化临时表 用户所在省和访问链接
DROP TABLE IF EXISTS logid_tmp3;
CREATE TABLE logid_tmp3 (
     id BIGINT,
     ip STRING,
     province STRING,
     city STRING,
     isp STRING,
     access_time STRING,
     url STRING,
     status_code STRING,
     traffic STRING,
     referer STRING,
     c_info STRING
);
-- 初始化临时表 访问时间和referer
DROP TABLE IF EXISTS logid_tmp4;
CREATE TABLE logid_tmp4 (
     id BIGINT,
     ip STRING,
     province STRING,
     city STRING,
     isp STRING,
     access_time STRING,
     access_hour STRING,
     url STRING,
     status_code STRING,
     traffic STRING,
     referer STRING,
     ref_type STRING,
     c_info STRING
);
-- 初始化访问日志表
DROP TABLE IF EXISTS logid;
CREATE TABLE logid (
    id BIGINT,
    ip STRING,
    province STRING,
    city STRING,
    isp STRING,
    access_time STRING,
    access_hour STRING,
    url STRING,
    status_code STRING,
    traffic STRING,
    referer STRING,
    ref_type STRING,
    c_info STRING,
    client_type STRING,
    client_browser STRING
);
-- 初始化访问链接TopN表
DROP TABLE IF EXISTS logid_url_top;
CREATE TABLE logid_url_top (
    url STRING,
    times INT
);
-- 初始化每个访客的第一个访问日志
DROP TABLE IF EXISTS logid_first;
CREATE TABLE logid_first (
    id BIGINT,
    ip STRING,
    province STRING,
    city STRING,
    isp STRING,
    access_time STRING,
    access_hour STRING,
    url STRING,
    status_code STRING,
    traffic STRING,
    referer STRING,
    ref_type STRING,
    c_info STRING,
    client_type STRING,
client_browser STRING
);
-- 初始化访问IP黑名单表，如果404次数多余10次，认为是恶意访问
DROP TABLE IF EXISTS logid_ip_black;
CREATE TABLE logid_ip_black (
    ip STRING,
    times INT
);