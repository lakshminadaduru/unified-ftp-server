package uk.hmrc.spring.servers;

import org.apache.ftpserver.ftplet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

public class UnifiedFTPlet extends DefaultFtplet {
	private static final Logger logger = LoggerFactory.getLogger(UnifiedFTPlet.class);

	@Override
	public FtpletResult onUploadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
		// Get the upload path of the uploaded file
		String path = session.getUser().getHomeDirectory();
		// Get upload user
		String name = session.getUser().getName();

		// Get upload file name
		String filename = request.getArgument();
	//xs	session.setAttribute("homeDirectory", "/tmp/clientone");
		logger.info ("XXXXXXXX user: '{}', upload file to directory '{}', file name: '{}', status: start uploading ~ ", name, path, filename);
		return super.onUploadStart(session, request);
	}

	@Override
	public FtpletResult beforeCommand(FtpSession session, FtpRequest request) throws FtpException, IOException {
		// TODO Auto-generated method stub
		logger.info(request.getCommand().toString());
		if (request.getCommand().toString().equalsIgnoreCase("DELE"))
		{
			FtpReply reply = new DefaultFtpReply(FtpReply.REPLY_450_REQUESTED_FILE_ACTION_NOT_TAKEN, 
					"Deletion not supported");
					session.write(reply );
		}	
		return super.beforeCommand(session, request);
	}

	@Override
	public FtpletResult onUploadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
		// Get the upload path of the uploaded file
		String path = session.getUser().getHomeDirectory();
		// Get upload user
		String name = session.getUser().getName();
		// Get upload file name
		String filename = request.getArgument();
		logger.info("user: '{}', upload file to directory: '{}', file name: '{}, status: success! '", name, path,
				filename);
		return super.onUploadEnd(session, request);
	}

	@Override
	public FtpletResult onDownloadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
		// todo servies...
		return super.onDownloadStart(session, request);
	}

	@Override
	public FtpletResult onDownloadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
		// todo servies...
		return super.onDownloadEnd(session, request);
	}
	
	
}