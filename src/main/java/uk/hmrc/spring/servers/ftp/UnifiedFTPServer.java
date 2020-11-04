package uk.hmrc.spring.servers.ftp;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.ftplet.FtpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@Configuration("UnifiedFTPServer")
public class UnifiedFTPServer {
	private static final Logger logger = LoggerFactory.getLogger(UnifiedFTPServer.class);
	ApplicationContext classPathXmlApplicationContext;
	FtpServer ftpServer;

	public UnifiedFTPServer() {
		// TODO Auto-generated constructor stub
		logger.info("Loading ftp-server.xml ");
		classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:ftp-server.xml");
		ftpServer = (FtpServer) classPathXmlApplicationContext.getBean("server");
		logger.info("Starting FTPserver from xml config: ftp-server.xml ");
		initFTPServer();
		logger.info("FTPserver Up and Running ");
	}

	public void initFTPServer() {
		try {
			ftpServer.start();
		} catch (FtpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
