package ryudongjae.websiteNoticeBoard.Configuartion;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfiguration {

    //ApplicationContext는 스프링 컨테이너(Spring Container) 중 하나입니다.
    @Autowired
    private ApplicationContext applicationContext;



    @Bean //@Bean이 지정된 객체는 컨테이너에 의해 관리되는 빈(Bean)으로 등록됨,Configuration 클래스의 메서드 레벨에만 지정이 가능
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }
        //히카리CP 객체를 생성한다.
        //히카리CP는 커넥션 풀(Connection Pool) 라이브러리 중 하나
        //해당 애너테이션은 인자에 prefix 속성을 지정할 수 있다.

    //prefix=접두사
    //Connnection Pool은 객체를 생성해두고, 데이터베이스 사용자에게 미리 생성해둔 커넥션을 제공했다가 다시 돌려받는 방
    @Bean
    public DataSource dataSource() { //데이터베이스 객체 생
        return new HikariDataSource(hikariConfig());
    }



    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        return factoryBean.getObject();
    }
    // SqlSessionTemplate은 마이바티스 스프링 연동 모듈의 핵심이다.
    // SqlSessionTemplate은 SqlSession을 구현하고,코드에서 SqlSession을 대체하는 역할을 한다.
    //SqlSessionTemplate은 SqlSessionFactory를 통해 생성
    //SqlSessionFactoryBean은 마이바티스와 스프링의 연동 모듈로 사용
    //SqlSessionFactoryBean 자체가 아닌, getObject 메서드가 리턴하는SqlSessionFactory를 생성합니다.
    //SqlSession은 SQL의 실행에 필요한 모든 메서드를 갖는 객체로 생각할 수 있다.
    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }


}
