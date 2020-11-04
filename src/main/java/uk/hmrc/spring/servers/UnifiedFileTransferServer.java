/**
 * 
 */
package uk.hmrc.spring.servers;

import org.apache.ftpserver.FtpServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lakshminadaduru
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class UnifiedFileTransferServer {

	/**
	 * 
	 */
	public UnifiedFileTransferServer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */

	public static void main(String[] args){
		SpringApplication.run(UnifiedFileTransferServer.class,args);
	}
}