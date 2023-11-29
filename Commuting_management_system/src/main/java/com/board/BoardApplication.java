package com.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}
	
//	static int i = 60 * 5;
//	@Component
//	public class ScheduledTasks {
//		@Scheduled(fixedDelay = 1000) // 1000=1초
//		public void runEvery10Sec() {
//			if (i > 0) {
//				System.out.println("서버시간:" + i);
//				i = i > 0 ? i - 100 : 0;
//			}
//		}
//	}
}
