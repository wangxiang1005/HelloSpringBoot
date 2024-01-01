package com.paladin.screw.service;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 生成数据库文档实现类
 **/
@Service
public class ScrewServiceImpl implements ScrewService {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public boolean contextLoads() {
        try {
            DataSource dataSourceMysql = applicationContext.getBean(DataSource.class);
            // 生成文件配置
            EngineConfig engineConfig = EngineConfig.builder()
                    // 生成文件路径
                    .fileOutputDir("D:\\JakartaEE\\CCDC\\ccdcsm-apm")
                    // 打开目录
                    .openOutputDir(false)
                    // 文件类型
                    .fileType(EngineFileType.HTML)
                    // 生成模板实现
                    .produceType(EngineTemplateType.freemarker)
                    // 自定义文件名（默认：数据库+描述）
                    .fileName("APM数据库文档-1.0.0")
                    .build();

            // 生成文档配置（包含以下自定义版本号、描述等配置连接）
            Configuration config = Configuration.builder()
                    .version("1.0.0")
                    .description("APM数据库文档")
                    .dataSource(dataSourceMysql)
                    .engineConfig(engineConfig)
                    .produceConfig(getProcessConfig())
                    .build();

            // 执行生成
            new DocumentationExecute(config).execute();
            return true;
        } catch (Exception e) {
            System.out.println("生成数据库文档异常：" + e);
        }
        return false;
    }

    /**
     * 配置想要生成的表+配置想要忽略的表
     *
     * @return 生成表配置
     */
    public static ProcessConfig getProcessConfig() {
        // 忽略表名
        //List<String> ignoreTableName = Arrays.asList("a", "b");
        // 忽略表前缀,如忽略a开头的数据库表
        //List<String> ignorePrefix = Arrays.asList("a", "b");
        // 忽略表后缀
        List<String> ignoreSuffix = Arrays.asList("_20231216");
        return ProcessConfig.builder()
                //根据名称指定表生成
                .designatedTableName(new ArrayList<>())
                //根据表前缀生成
                .designatedTablePrefix(new ArrayList<>())
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                //忽略表名
                //.ignoreTableName(ignoreTableName)
                //忽略表前缀
                //.ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
                .ignoreTableSuffix(ignoreSuffix).build();
    }
}