package com.paladin.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class GuavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuavaApplication.class, args);
    }

    @PostConstruct
    public void generalURltoFile() {
        // 初次启动的时候开启, 会往url.txt中添加5000条数据
        /*try{
            //注意这块写上自己电脑的路径
            File file = new File("D:\\JakartaEE\\IntelliJ-WorkSpace\\Hello\\HelloSpringBoot\\hello-springboot-guava\\src\\main\\resources\\url.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 5000; i++) {
                String name = RandomStringUtils.randomAlphabetic(5);
                String fileName = "https://www." + name + ".com" + i + "\n";
                builder.append(fileName);
            }
            bw.write(String.valueOf(builder));
            bw.newLine();
            bw.flush();
            bw.close();
            osw.close();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
    }

    /**
     * 将文件内容读入到布隆过滤器中
     * @return
     * @throws IOException
     */
    @Bean
    public BloomFilter bloomFilter() throws IOException {
        BloomFilter bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8),5000000,0.01);
        FileInputStream fileInputStream = new FileInputStream(new File("D:\\JakartaEE\\IntelliJ-WorkSpace\\Hello\\HelloSpringBoot\\hello-springboot-guava\\src\\main\\resources\\url.txt"));
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        while(true){
            line = bufferedReader.readLine();
            if(line !=null){
                bloomFilter.put(line);
            }else{
                break;
            }
        }
        inputStreamReader.close();
        return bloomFilter;
    }
}