package others.problems;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @auther: liweizhi
 * Date: 2019/4/9 19:11
 * Description:
 */
@Slf4j
public class RegularExpression {
    @Test
    public void problem001(){
        try {
            String a =
                    "<style>\n" +
                            ".topbar .search .ip_text {\n" +
                            "    position: absolute;\n" +
                            "    top: 1px;\n" +
                            "    left: 10px;\n" +
                            "    padding: 1px 3px;\n" +
                            "    background: none;\n" +
                            "    border: none;\n" +
                            "    width: 122px;\n" +
                            "    height: 20px;\n" +
                            "    line-height: 20px;\n" +
                            "}\n" +
                            ".topbar .search .in_button {\n" +
                            "    position: absolute;\n" +
                            "    top: 3px;\n" +
                            "    right: 10px;\n" +
                            "    width: 18px;\n" +
                            "    height: 18px;\n" +
                            "    display: block;\n" +
                            "    cursor: pointer;\n" +
                            "    border: none;\n" +
                            "    background: url(http://static.ljcd.gov.cn/statics/images/ljcd2017/bg_search_btn1.png) no-repeat top center;\n" +
                            "}\n" +
                            ".topbar .language{\n" +
                            "    margin-top: 15px;\n" +
                            "    color: #1e9446;\n" +
                            "}\n" +
                            ".topbar .language a{\n" +
                            "    color: #1e9446 !important;\n" +
                            "    font-size: 14px;\n" +
                            "}\n" +
                            ".topbar .link-site-map {\n" +
                            "    margin-top: 15px;\n" +
                            "    margin-left: 26px;\n" +
                            "    color: #1e9446;\n" +
                            "    font-size: 14px;\n" +
                            "}\n" +
                            ".topbar .date{\n" +
                            "    margin-top: 15px;\n" +
                            "    margin-left: 30px;\n" +
                            "    color: #1e9446;\n" +
                            "    font-size: 14px;\n" +
                            "}\n" +
                            ".topimg{\n" +
                            "    height: 220px;\n" +
                            "    width: 100%;\n" +
                            "    min-width: 1200px;\n" +
                            "    background: url(http://static.ljcd.gov.cn/statics/images/ljcd/quxian/plate04/huanbao/bg-header1.jpg) no-repeat top center;\n" +
                            "}\n" +
                            "</style>\n aaa<style>asdasdsa</style>sss";
            String contentStr = a.replaceAll("<style>((.)|(\r\n)|(\n)|(\r))*?</style>", "");
            System.out.println(contentStr);
        } catch (Throwable e) {
            log.info("error!!!",e);
        }
        System.out.println("=============end==================");
    }
}
