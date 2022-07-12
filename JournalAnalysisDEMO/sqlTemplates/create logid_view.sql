-- 	总访问人次（PV）
CREATE VIEW logid_pv ( pv_count ) AS SELECT
max( id ) 
FROM
	logid;
-- 访问人数
CREATE VIEW logid_uv ( uv_count ) AS SELECT
count( id ) 
FROM
	logid_first;
-- 独立ip
CREATE VIEW logid_dist_ip ( dist_ip ) AS SELECT
count( DISTINCT ip ) 
FROM
	logid_first;
-- 访问链接Top 10
CREATE VIEW logid_top10 AS SELECT
* 
FROM
	logid_url_top;
	
-- 各个时间访问人次
CREATE VIEW logid_access_time (
	access_hour,
num) AS SELECT
access_hour,
count( id ) 
FROM
	logid 
GROUP BY
	access_hour;

-- 访问来源占比
CREATE VIEW logid_ref_type ( 
ref_type, VALUE ) AS SELECT
ref_type,
count( ref_type )
FROM
	logid 
GROUP BY
	ref_type 
HAVING
	ref_type != 'self' 
	AND ref_type != 'other';


-- -- ip黑名单
-- CREATE VIEW logid_ip_black AS SELECT
-- * 
-- FROM
-- 	logid_ip_black;
	
-- 地域分布logid_prov
CREATE VIEW logid_prov ( province, count ) AS SELECT
province,
count( id ) 
FROM
	logid 
GROUP BY
	province;
	
-- 操作系统占比
CREATE VIEW logid_os AS 
SELECT
client_type,
count( id ) 
FROM
	logid 
GROUP BY
	client_type;
	
-- 浏览器占比
CREATE VIEW logid_brow AS 
SELECT
client_browser,
count( id ) 
FROM
	logid 
GROUP BY
	client_browser;
	
-- 网络运营商占比
CREATE VIEW logid_isp AS 
SELECT
isp,
count( isp ) 
FROM
	logid 
GROUP BY
	client_browser;
	
-- 搜索引擎占比
CREATE VIEW logid_srch_eng ( NAME, count ) AS SELECT
ref_type,
count( ref_type )
FROM
	logid
GROUP BY
	ref_type 
HAVING
	ref_type != 'self' 
	AND ref_type != 'other';