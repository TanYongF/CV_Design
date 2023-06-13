package fun.tans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("fun.tans.mapper")
public class CVApplication {
    public static void main(String[] args) {
        SpringApplication.run(CVApplication.class, args);
    }
}
