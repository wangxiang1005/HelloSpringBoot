package com.paladin.starter.region;

import com.paladin.starter.IPStarterService;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import javax.annotation.PostConstruct;
import java.io.InputStream;

@Service
public class RegionIPStarterServiceImpl implements IPStarterService {

    @Value("${ip.convert.conf.xdb.url}")
    private String url;

    private static Searcher searcher;

    /**
     * 在服务启动时加载 ip2region.db到内存中
     * 解决打包jar后找不到 ip2region.db的问题
     * 出现异常应该直接抛出终止程序启动, 避免后续invoke时出现更多错误
     */
    @PostConstruct
    private void initIp2regionResource() {
        try {
            InputStream inputStream = new ClassPathResource(url).getInputStream();
            byte[] dbBinStr = FileCopyUtils.copyToByteArray(inputStream);
            System.out.println("This is @PostConstruct");
            // 创建一个完全基于内存的查询对象
            searcher = Searcher.newWithBuffer(dbBinStr);
        } catch (Exception e) {
            System.out.println("failed to create content cached searcher");
        }
    }

    @Override
    public String ipAnalyze(String ip) {
        if (StringUtils.isBlank(ip)) {
            return "";
        }
        // 1、从dbPath中预先加载VectorIndex缓存,并且把这个得到的数据作为全局变量,后续反复使用
        String xdbPath = "paladin-ip-address-starter/src/main/resources/static/ip2region.xdb";
        System.out.println("=========>>>>>>>>>>"+xdbPath);

        byte[] vIndex = new byte[0];
        try {
            vIndex = Searcher.loadVectorIndexFromFile(xdbPath);
        } catch (Exception e) {
            System.out.printf("failed to load vector index from `%s`: %s\n", xdbPath, e);
        }

        // 2、使用全局的vIndex创建带VectorIndex缓存的查询对象
        Searcher searcher = null;
        try {
            searcher = Searcher.newWithVectorIndex(xdbPath, vIndex);
            String results = searcher.search(ip);
            System.out.println(results);
            String[] ss = results.split("\\|");
            System.out.println(ss[3]);
            return ss[3];
        } catch (Exception e) {
            System.out.printf("failed to create vectorIndex cached searcher with `%s`: %s\n", xdbPath, e);
        }
        return null;
    }

    /*public static void main(String[] args) throws Exception {
        String ipAddr = "39.156.66.10";

        // 1、从dbPath中预先加载VectorIndex缓存,并且把这个得到的数据作为全局变量,后续反复使用
        String xdbPath = "paladin-ip-address-starter/src/main/resources/static/ip2region.xdb";
        System.out.println("=========>>>>>>>>>>"+xdbPath);

        byte[] vIndex = new byte[0];
        try {
            vIndex = Searcher.loadVectorIndexFromFile(xdbPath);
        } catch (Exception e) {
            System.out.printf("failed to load vector index from `%s`: %s\n", xdbPath, e);
        }

        // 2、使用全局的vIndex创建带VectorIndex缓存的查询对象
        Searcher searcher = null;
        try {
            searcher = Searcher.newWithVectorIndex(xdbPath, vIndex);
        } catch (Exception e) {
            System.out.printf("failed to create vectorIndex cached searcher with `%s`: %s\n", xdbPath, e);
        }
        String results = searcher.search(ipAddr);
        System.out.println(results);
        String[] ss = results.split("\\|");
        System.out.println(ss[3]);
    }*/
}