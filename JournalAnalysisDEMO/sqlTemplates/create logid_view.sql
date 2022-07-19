-- 	总访问人次（PV）
DROP VIEW IF EXISTS logid_pv;
CREATE VIEW logid_pv ( pv_count ) AS SELECT
max( id ) 
FROM
	logid;
-- 访问人数
DROP VIEW IF EXISTS logid_uv;
CREATE VIEW logid_uv ( uv_count ) AS SELECT
count( id ) 
FROM
	logid_first;
-- 独立ip
DROP VIEW IF EXISTS logid_dist_ip;
CREATE VIEW logid_dist_ip ( dist_ip ) AS SELECT
count( DISTINCT ip ) 
FROM
	logid_first;
-- 访问链接Top 10
DROP VIEW IF EXISTS logid_top10;
CREATE VIEW logid_top10 ( url, times) AS SELECT
* 
FROM
	logid_url_top;
	
-- 各个时间访问人次
DROP VIEW IF EXISTS logid_access_time;
CREATE VIEW logid_access_time (
	access_hour,
count) AS SELECT
access_hour,
count( id ) 
FROM
	logid 
GROUP BY
	access_hour;

-- 访问来源占比
DROP VIEW IF EXISTS logid_ref_type;
CREATE VIEW logid_ref_type ( 
ref_type, count ) AS SELECT
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
DROP VIEW IF EXISTS logid_prov;
CREATE VIEW logid_prov ( province, count ) AS SELECT
province,
count( id ) 
FROM
	logid 
GROUP BY
	province;
	
-- 操作系统占比
DROP VIEW IF EXISTS logid_os;
CREATE VIEW logid_os ( client_type, count ) AS
SELECT
client_type,
count( id ) 
FROM
	logid 
GROUP BY
	client_type;
	
-- 浏览器占比
DROP VIEW IF EXISTS logid_brow;
CREATE VIEW logid_brow ( client_browser, count ) AS
SELECT
client_browser,
count( id )
FROM
	logid 
GROUP BY
	client_browser;
	
-- 网络运营商占比
DROP VIEW IF EXISTS logid_isp;
CREATE VIEW logid_isp ( isp, count ) AS SELECT
t.isp AS NAME,
count( t.isp ) AS VALUE
FROM(
SELECT
CASE
    WHEN INSTR( isp, "移动" ) > 0 THEN	"移动"
    WHEN INSTR( isp, "联通" ) > 0 THEN	"联通"
    WHEN INSTR( isp, "电信" ) > 0 THEN	"电信"
    ELSE "其他"
END AS isp
    FROM
        logid
) t
GROUP BY
		t.isp
ORDER BY
	VALUE;
	
-- 搜索引擎占比
DROP VIEW IF EXISTS logid_srch_eng;
CREATE VIEW logid_srch_eng ( ref_type, count ) AS SELECT
ref_type,
count( ref_type )
FROM
	logid
GROUP BY
	ref_type 
HAVING
	ref_type != 'self' 
	AND ref_type != 'other';