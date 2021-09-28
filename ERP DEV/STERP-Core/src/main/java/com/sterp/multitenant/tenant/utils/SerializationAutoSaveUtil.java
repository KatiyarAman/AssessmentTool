package com.sterp.multitenant.tenant.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.service.ModuleServiceMain;

@Component
public class SerializationAutoSaveUtil {

	@Autowired
	ServletContext servletContext;

	@Autowired
	AppService appService;

	@Autowired
	ModuleServiceMain moduleService;

	public static int noOfQuickServiceThreads = 20;

//	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);

	public void autosaveASynchronous(Object obj, String tenantInfo, long moduleId)
			throws MailException, RuntimeException {
		try {
//			String className = this.moduleService.getModuleById(moduleId, true).getServerClassName();
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			System.out.println("===> Username: " + userName);
//			quickService.submit(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						FileOutputStream fileOutputStream = new FileOutputStream(
//								"webapps/STERP-1.0.0/obj/" + tenantInfo + "_" + userName + "_" + className);
//						ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//						objectOutputStream.writeObject(obj);
//						objectOutputStream.flush();
//						objectOutputStream.close();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getSerialize(String tenantInfo, long moduleId) throws IOException {
		Object obj = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("===> Username: " + userName);
		ObjectInputStream objectInputStream = null;
		try {
			String className = this.moduleService.getModuleById(moduleId, true).getServerClassName();
			FileInputStream fileInputStream = new FileInputStream(
					"webapps/STERP-1.0.0/obj/" + tenantInfo + "_" + userName + "_" + className);
			objectInputStream = new ObjectInputStream(fileInputStream);
			obj = objectInputStream.readObject();
			objectInputStream.close();
		} catch (IOException e) {
			objectInputStream.close();
		} catch (ClassNotFoundException e) {
			objectInputStream.close();
		} catch (Exception e) {
			objectInputStream.close();
		} finally {
			objectInputStream.close();
		}
		return obj;
	}
}
