DROP TABLE IF EXISTS logid;
CREATE TABLE logid(
    id VARCHAR(255) NOT NULL   COMMENT 'id' ,
    ip VARCHAR(255)    COMMENT 'ip地址' ,
    province VARCHAR(255)    COMMENT '省份' ,
    city VARCHAR(255)    COMMENT '城市' ,
    isp VARCHAR(255)    COMMENT '网络服务提供商' ,
    access_time DATETIME    COMMENT '登入时间' ,
    access_hour DECIMAL(24,6)    COMMENT '时间' ,
    url VARCHAR(255)    COMMENT '统一资源定位器' ,
    status_code VARCHAR(255)    COMMENT '状态码' ,
    traffic VARCHAR(255)    COMMENT '流量' ,
    referer VARCHAR(255)    COMMENT '来源' ,
    ref_type VARCHAR(255)    COMMENT '访问方式' ,
    c_info VARCHAR(255)    COMMENT '电脑信息' ,
    client_type VARCHAR(255)    COMMENT '操作系统' ,
    client_browser VARCHAR(255)    COMMENT '浏览器' ,
    PRIMARY KEY (id)
)  COMMENT = '日志分析结果样例';

DROP TABLE IF EXISTS `logid_first`;
CREATE TABLE `logid_first`  (
  `id` bigint(20) NULL DEFAULT NULL,
  `ip` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `province` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `city` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `isp` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `access_time` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `access_hour` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `url` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `status_code` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `traffic` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `referer` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `ref_type` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `c_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `client_type` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `client_browser` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `logid_ip_black`;
CREATE TABLE `logid_ip_black`  (
  `ip` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `times` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `logid_url_top`;
CREATE TABLE `logid_url_top`  (
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `times` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;