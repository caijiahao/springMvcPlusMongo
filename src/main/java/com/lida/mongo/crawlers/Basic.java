package com.lida.mongo.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.xpath.model.JXDocument;

import java.util.List;

/**
 * Created by lenovo on 2016/11/29.
 */
@Crawler(name = "basic")
public class Basic extends BaseSeimiCrawler {
    @Override
    public String[] startUrls() {
        return new String[]{"http://www.gdagri.gov.cn/was5/web/search?channelid=218763"};
    }
    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {
            List<Object> urls = doc.sel("//a[@class='result_link']/@href");
            logger.info("{}==========================================", urls.size());
            for (Object s:urls){
                push(new Request(s.toString(),"getTitle"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getTitle(Response response){
        JXDocument doc = response.document();
        try {
           // logger.info("url:{} {}", response.getUrl(), doc.sel("//h1[@class='postTitle']/a/text()|//a[@id='cb_post_title_url']/text()"));
            byte[] temp=doc.sel("//h2/text()").toString().getBytes("ISO-8859-1");
            String newStr=new String(temp,"utf-8");
            logger.info("url:{} {} ", response.getUrl(),newStr);
            //do something
            //System.out.println(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
