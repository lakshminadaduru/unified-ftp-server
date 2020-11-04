package uk.hmrc.spring.servers.sshd;



import org.apache.ftpserver.filesystem.nativefs.NativeFileSystemFactory;
import org.apache.ftpserver.filesystem.nativefs.impl.NativeFileSystemView;
import org.apache.ftpserver.ftplet.FileSystemView;
import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.common.session.Session;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.config.keys.AuthorizedKeysAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.subsystem.sftp.SftpSubsystemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


import uk.hmrc.spring.servers.ftp.UnifiedFTPServer;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collections;

@Configuration("UnifiedSSHDServer")
public class UnifiedSSHDServer {
	private static final Logger logger = LoggerFactory.getLogger(UnifiedFTPServer.class);


    boolean sshdEnabled;
    
    public UnifiedSSHDServer(@Value("${sshd.enabled}") final boolean sshdEnabled){
    	
    	logger.info(" SSHD Enabled :: "+ sshdEnabled  );
    	this.sshdEnabled = sshdEnabled;
    }

    @PostConstruct
    public void startServer() throws IOException {
    	logger.info(" SStarting SHD Server  :: "+ sshdEnabled  );
        start();
    }

    private void start() throws IOException {
        SshServer sshd = SshServer.setUpDefaultServer();
        sshd.setPort(4444);
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(new File("host.ser")));
        sshd.setSubsystemFactories(Collections.singletonList(new SftpSubsystemFactory()));
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
        sshd.setPublickeyAuthenticator((username, key, session) -> true);
  //      sshd.setPublickeyAuthenticator(new AuthorizedKeysAuthenticator(new File("src/main/resources/authorized_keys")));
  //      sshd.setPasswordAuthenticator((username,password,session) -> username.equals("test"));
 //       sshd.setPublickeyAuthenticator(new AuthorizedKeysAuthenticator(new File("src/main/resources/authorized_keys")));
        VirtualFileSystemFactory fileSystemFactory = new VirtualFileSystemFactory();
        Path defaultHomeDir= FileSystems.getDefault().getPath("/tmp/");
		fileSystemFactory.setDefaultHomeDir(defaultHomeDir);
        sshd.setFileSystemFactory(fileSystemFactory);   
        sshd.start();
        logger.info("SFTP server started");
    }
}