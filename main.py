# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
import random
import time

url_paths = [
    "class/112.html",
    "class/128.html",
    "class/145.html",
    "class/146.html",
    "class/500.html",
    "class/250.html",
    "class/131.html",
    "class/130.html",
    "class/271.html",
    "class/127.html",
    "learn/821",
    "learn/823",
    "learn/987",
    "learn/500",
    "course/list"
]
ip_first_code = [58, 60, 61, 112, 116, 118, 120, 124, 168, 180, 202, 203, 210, 211, 218, 219, 220, 222, 221]
ip_second_code = [4, 5, 7, 12, 29, 90, 93, 22, 240, 241, 161, 98, 99, 96, 217, 128, 139, 134, 221, 222, 101, 102, 103,
                  85, 140, 138]
ip_third_code = [128, 192, 78, 249, 1, 2, 39, 81, 150, 111, 134, 213, 85, 88, 222, 200, 130, 245]
ip_fourth_code = [1, 2, 4, 68, 141, 88, 222, 99, 100, 131, 181, 193, 10, 101, 200, 66, 86]
status_code = [404, 502, 200, 403, 301, 503, 202]
http_refers = ["https://www,baidu.com/s?wd={query}",
               "https://cn.bing.com/search?q={query}",
               "https://www.sogou.com/web?query={query}",
               "https://www.google.com.hk/search?q={query}"
               ]
search_keyword = ["Spark SQL实战",
                  "Hadoop基础",
                  "Storm实战",
                  "Spark Streaming实战",
                  "Java从入门到放弃",
                  "SpringBoot实战",
                  "Linux进阶",
                  "Vue.js",
                  "SpringBoot入门"
                  ]
BrowserHeader = [
    "User-Agent:Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 10.0; Win64; x64; Trident/5.0)",  # IE9请求头
    "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/603.3.8 "
    + "(KHTML, like Gecko) Version/10.1.2 Safari/603.3.8",  # Safari MAC请求头
    "User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/"
    + "537.36",  # chrome Windows请求头
    "User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0",  # firefox请求头
    "User-Agent:Mozilla/5.0 (compatible; Baiduspider/2.0;+https://www.baidu.com/search/spider.html）",  # 百度请求头
    "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) "
    "Chrome/17.0.963.56 Safari/535.11",  # chrome mac请求头
    "User-Agent:Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 "
    + "Safari/534.50 ",  # safari windows
    "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) Gecko/20100101 Firefox/4.0.1",  # firefox mac
    "User-Agent:Mozilla/5.0 (Linux;u;Android 4.2.2;zh-cn;) "
    + "AppleWebKit/534.46 (KHTML,likeGecko) Version/5.1 Mobile Safari/10600.6.3 (compatible; "
    + "Baiduspider/2.0;+https://www.baidu.com/search/spider.html) ",
    # safari iOS 4.33 – iPhone
    "User-Agent:Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, "
    + "like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5 ",
    # safari iOS 4.33 – iPad
    "User-Agent:Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) "
    + "Version/5.0.2 Mobile/8J2 Safari/6533.18.5 ",
    # Android QQ浏览器 For android
    "User-Agent: MQQBrowser/26 Mozilla/5.0 (Linux; U; Android 2.3.7; zh-cn; MB200 Build/GRJ22; CyanogenMod-7) "
    + "AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",

    # Android Opera Mobile
    "User-Agent: Opera/9.80 (Android 2.3.4; Linux; Opera Mobi/build-1107180945; U; en-GB) Presto/2.8.149 Version/11.10"
]

method = ["GET", "POST"]


def sample_url():
    return random.sample(url_paths, 1)[0]


def sample_ip():
    ip1 = random.sample(ip_first_code, 1)
    ip_slice1 = "".join(repr(e) for e in ip1)
    ip2 = random.sample(ip_second_code, 1)
    ip_slice2 = "".join(repr(e) for e in ip2)
    ip3 = random.sample(ip_third_code, 1)
    ip_slice3 = "".join(repr(e) for e in ip3)
    ip4 = random.sample(ip_fourth_code, 1)
    ip_slice4 = "".join(repr(e) for e in ip4)
    ip_slice = [ip_slice1, ip_slice2, ip_slice3, ip_slice4]
    return ".".join(ip_slice)


def sample_browser():
    return random.sample(BrowserHeader, 1)[0]


def sample_status_code():
    return random.sample(status_code, 1)[0]


def sample_method():
    return random.sample(method, 1)[0]


def sample_referer():
    if random.uniform(0, 1) > 0.5:
        return "-"
    refer_str = random.sample(http_refers, 1)
    query_str = random.sample(search_keyword, 1)
    return refer_str[0].format(query=query_str[0])


def generate_log(count=10):
    time_str = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
    f = open("./access.log", "w+")
    while count >= 1:
        query_log = "{ip}\t{local_time}\t\"{method} /{url} HTTP/1.1\"\t{status_code}\t{referer}\t{browser}". \
            format(url=sample_url(),
                   ip=sample_ip(),
                   method=sample_method(),
                   referer=sample_referer(),
                   status_code=sample_status_code(),
                   local_time=time_str,
                   browser=sample_browser())
        f.write(query_log + "\n")
        count = count - 1


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    generate_log(20000)

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
