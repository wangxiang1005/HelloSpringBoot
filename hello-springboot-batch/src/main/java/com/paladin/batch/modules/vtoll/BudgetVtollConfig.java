package com.paladin.batch.modules.vtoll;

import com.alibaba.druid.pool.DruidDataSource;
import com.paladin.batch.modules.MyBeanValidator;
import com.paladin.batch.modules.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * CsvBatchConfig
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/3
 */
@Configuration
public class BudgetVtollConfig {
    /**
     * ItemReader定义,用来读取数据
     * 1，使用FlatFileItemReader读取文件
     * 2，使用FlatFileItemReader的setResource方法设置csv文件的路径
     * 3，对此对cvs文件的数据和领域模型类做对应映射
     *
     * @return FlatFileItemReader
     */
    @Bean(name = "vtollReader")
    @StepScope
    public FlatFileItemReader<BudgetVtoll> reader(@Value("#{jobParameters['input.file.name']}") String pathToFile) {
        FlatFileItemReader<BudgetVtoll> reader = new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource(pathToFile));
        reader.setResource(new FileSystemResource(pathToFile));
        reader.setLineMapper(new DefaultLineMapper<BudgetVtoll>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer(",") {
                    {
                        setNames(new String[]{
                                "id", "year", "tollid", "budgetid", "cbudgetid", "version", "auditmsg", "trialstatus",
                                "firauditer", "firaudittime", "finauditer", "finaudittime", "edittime", "startdate", "enddate"
                        });
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<BudgetVtoll>() {{
                    setTargetType(BudgetVtoll.class);
                }});
            }
        });
        // 如果包含header，需要忽略掉
        reader.setLinesToSkip(1);
        return reader;
    }

    /**
     * ItemProcessor定义，用来处理数据
     *
     * @return
     */
    @Bean(name = "vtollProcessor")
    public ItemProcessor<BudgetVtoll, BudgetVtoll> processor() {
        //使用我们自定义的ItemProcessor的实现CsvItemProcessor
        ValidatingItemProcessor<BudgetVtoll> processor = new ValidatingItemProcessor<BudgetVtoll>() {
            @Override
            public BudgetVtoll process(BudgetVtoll item) throws ValidationException {
                /*
                 * 需要执行super.process(item)才会调用自定义校验器
                 */
                super.process(item);
                /*
                 * 对数据进行简单的处理和转换 todo
                 */
                return item;
            }
        };
        //为processor指定校验器为CsvBeanValidator()
        processor.setValidator(csvBeanValidator());
        return processor;
    }

    /**
     * ItemWriter定义，用来输出数据
     * spring能让容器中已有的Bean以参数的形式注入，Spring Boot已经为我们定义了dataSource
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "vtollWriter")
    public ItemWriter<BudgetVtoll> writer(DruidDataSource dataSource) {
        JdbcBatchItemWriter<BudgetVtoll> writer = new JdbcBatchItemWriter<>();
        //我们使用JDBC批处理的JdbcBatchItemWriter来写数据到数据库
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());

        String sql = "insert into nt_bsc_BudgetVtoll " + " (f_id,f_year,f_tollid,f_budgetid,f_cbudgetid,f_version,f_auditmsg,f_trialstatus,f_firauditer,f_firaudittime,f_finauditer,f_finaudittime,f_edittime,f_startdate,f_enddate) "
                + " values(:id,:year,:tollid,:budgetid,:cbudgetid,:version,:auditmsg,:trialstatus,:firauditer,:firaudittime,:finauditer,:finaudittime,:edittime,:startdate,:enddate)";
        //在此设置要执行批处理的SQL语句
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }

    /**
     * Job定义，我们要实际执行的任务，包含一个或多个Step
     *
     * @param jobBuilderFactory
     * @param s1
     * @return
     */
    @Bean(name = "vtollJob")
    public Job vtollJob(JobBuilderFactory jobBuilderFactory, @Qualifier("vtollStep1") Step s1) {
        return jobBuilderFactory.get("vtollJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1)//为Job指定Step
                .end()
                .listener(new MyJobListener())//绑定监听器csvJobListener
                .build();
    }

    /**
     * step步骤，包含ItemReader，ItemProcessor和ItemWriter
     *
     * @param stepBuilderFactory
     * @param reader
     * @param writer
     * @param processor
     * @return
     */
    @Bean(name = "vtollStep1")
    public Step vtollStep1(StepBuilderFactory stepBuilderFactory,
                           @Qualifier("vtollReader") ItemReader<BudgetVtoll> reader,
                           @Qualifier("vtollWriter") ItemWriter<BudgetVtoll> writer,
                           @Qualifier("vtollProcessor") ItemProcessor<BudgetVtoll, BudgetVtoll> processor) {
        return stepBuilderFactory
                .get("vtollStep1")
                .<BudgetVtoll, BudgetVtoll>chunk(5000)//批处理每次提交5000条数据
                .reader(reader)//给step绑定reader
                .processor(processor)//给step绑定processor
                .writer(writer)//给step绑定writer
                .faultTolerant()
                .retry(Exception.class)   // 重试
                .noRetry(ParseException.class)
                .retryLimit(1)           //每条记录重试一次
                .skip(Exception.class)
                .skipLimit(200)         //一共允许跳过200次异常
//                .taskExecutor(new SimpleAsyncTaskExecutor()) //设置每个Job通过并发方式执行，一般来讲一个Job就让它串行完成的好
//                .throttleLimit(10)        //并发任务数为 10,默认为4
                .build();
    }

    @Bean
    public Validator<BudgetVtoll> csvBeanValidator() {
        return new MyBeanValidator<>();
    }
}




