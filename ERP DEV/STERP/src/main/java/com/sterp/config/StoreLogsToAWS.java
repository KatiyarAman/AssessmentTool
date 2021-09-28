//package com.sterp.config;
//
//import java.io.File;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//
//@Component
//public class StoreLogsToAWS {
//	@Autowired
//	private AmazonS3 s3Client;
//
//	@Autowired
//	private Environment env;
//
//	@Scheduled(cron = "0 5 1 * * *")
//	public void moveLogsFromEC2ToS3() {
//		try {
//			File logsDir = new File(env.getProperty("AWS_EC2_LOG_PATH"));
//			for (File logFile : logsDir.listFiles()) {
//				String fileName = logFile.getName();
//				if (fileName.endsWith(".log")) {
//					s3Client.putObject(
//							new PutObjectRequest(env.getProperty("AWS_S3_LOGS_BUCKET_NAME"), fileName, logFile));
//				}
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//			// logger.error("Error in moving log files! : {}", e);
//		}
//	}
//}
