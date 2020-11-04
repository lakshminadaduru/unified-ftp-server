package uk.hmrc.spring.servers.ftps;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.ftplet.FtpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.hmrc.spring.servers.ftp.UnifiedFTPServer;

@Configuration("UnifiedFTPSServer")
public class UnifiedFTPSServer {
	private static final Logger logger = LoggerFactory.getLogger(UnifiedFTPServer.class);
	
	ApplicationContext classPathXmlApplicationContext;
	FtpServer ftpsServer;

	public UnifiedFTPSServer() {
		// TODO Auto-generated constructor stub

		logger.info("Loading secure-ftp-server.xml ");
		classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:secure-ftp-server.xml");
		ftpsServer = (FtpServer) classPathXmlApplicationContext.getBean("server");
		logger.info("Starting FTPserver from xml config: secure-ftp-server.xml ");
		initFTPServer();
		logger.info("FTPSserver Up and Running ");
	}

	public void initFTPServer() {
		try {
			ftpsServer.start();
		} catch (FtpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
