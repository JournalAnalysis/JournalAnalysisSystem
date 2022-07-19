DROP TABLE IF EXISTS `logid`;
CREATE TABLE `logid`  (
  `id` bigint(20) NULL DEFAULT NULL,
  `ip` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `province` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `city` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `isp` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `access_time` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `access_hour` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `url` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `status` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `traffic` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `referer` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `ref_type` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `c_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `client_type` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `client_browser` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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