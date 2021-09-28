package com.sterp.multitenant.tenant.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.sterp.multitenant.controller.AuthenticationController;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.repository.UserRepository;
import com.sterp.multitenant.tenant.service.SecurityAppService;

@Service
public class SecurityAppServiceImpl implements SecurityAppService {

	Logger logger = LoggerFactory.getLogger(SecurityAppServiceImpl.class);

	@Autowired
	UserRepository userEntityRepository;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Override
	public UserEntity getCurrentUser() {
		UserEntity user = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if (userName != null) {
			user = this.userEntityRepository.findByUsername(userName);
		}
		return user;
	}

	@Override
	public HttpHeaders getXCountHeader(long count) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Total-Count", String.valueOf(count));
		return headers;
	}

	@Override
	public String getClientIPAddress(HttpServletRequest request) {
		try {
			String ip = request.getRemoteAddr();
			InetAddress inetaddress = InetAddress.getLocalHost(); // Get LocalHost
			// refrence
			// String ip = inetaddress.getHostAddress(); // Get Host IP Address
			return ip; // return IP Address
		} catch (Exception E) {
			E.printStackTrace(); // print Exception StackTrace
			return null;
		}
	}

	@Override
	public String getLoginLocation(String ip) throws IOException, GeoIp2Exception {
		String location = "UNKNOWN";
		ClassLoader classLoader = getClass().getClassLoader();
		
		//File database = new File("webapps/STERP-1.0.0/GeoLite2-City.mmdb"); // local
//		File database = new File("http://192.168.1.33:8887/GeoLite2-City.mmdb");
//		File database = new File(classLoader.getResource("GeoLite2-City.mmdb").getFile());
		Resource resource = this.resourceLoader.getResource("classpath:GeoLite2-City.mmdb");
		InputStream dbAsStream = resource.getInputStream(); // <-- this is the difference

        // Initialize the reader
		DatabaseReader reader = null;
        reader = new DatabaseReader
                    .Builder(dbAsStream)
                    .fileMode(Reader.FileMode.MEMORY)
                    .build();
//		DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
		CityResponse cityResponse = reader.city(InetAddress.getByName(ip));
		if (Objects.nonNull(cityResponse) && Objects.nonNull(cityResponse.getCity())
				&& !Strings.isNullOrEmpty(cityResponse.getCity().getName())) {
			location = cityResponse.getCity().getName();
		}

		return location;
	}

	Pattern macpt = null;

	@Override
	public String getClientMACAddress(String ip) throws Exception {
//		try {
//			InetAddress inetaddress = InetAddress.getLocalHost();
//			NetworkInterface network = NetworkInterface.getByInetAddress(inetaddress);
//			byte[] macArray = network.getHardwareAddress();
//			StringBuilder str = new StringBuilder();
//			for (int i = 0; i < macArray.length; i++) {
//				str.append(String.format("%02X%s", macArray[i], (i < macArray.length - 1) ? "-" : ""));
//			}
//			String macAddress = str.toString();
//
//			return macAddress; // return MAc Address
//		} catch (Exception E) {
//			E.printStackTrace(); // print Exception StackTrace
//			return null;
//		}

		String macAddress = null;
		String command = "ifconfig";

		String osName = System.getProperty("os.name");
		System.out.println("Operating System is " + osName);

		if (osName.startsWith("Windows")) {
			command = "ipconfig /all";
		} else if (osName.startsWith("Linux") || osName.startsWith("Mac") || osName.startsWith("HP-UX")
				|| osName.startsWith("NeXTStep") || osName.startsWith("Solaris") || osName.startsWith("SunOS")
				|| osName.startsWith("FreeBSD") || osName.startsWith("NetBSD")) {
			command = "ifconfig -a";
		} else if (osName.startsWith("OpenBSD")) {
			command = "netstat -in";
		} else if (osName.startsWith("IRIX") || osName.startsWith("AIX") || osName.startsWith("Tru64")) {
			command = "netstat -ia";
		} else if (osName.startsWith("Caldera") || osName.startsWith("UnixWare") || osName.startsWith("OpenUNIX")) {
			command = "ndstat";
		} else {// Note: Unsupported system.
			throw new Exception("The current operating system '" + osName + "' is not supported.");
		}

		Process pid = Runtime.getRuntime().exec(command);
		BufferedReader in = new BufferedReader(new InputStreamReader(pid.getInputStream()));
		Pattern p = Pattern.compile("([\\w]{1,2}(-|:)){5}[\\w]{1,2}");
		while (true) {
			String line = in.readLine();
			System.out.println("line " + line);
			if (line == null)
				break;

			Matcher m = p.matcher(line);
			if (m.find()) {
				macAddress = m.group();
				break;
			}
		}
		in.close();
		return macAddress;
	}

	@Override
	public String getSystemName() {
		try {

			InetAddress inetaddress = InetAddress.getLocalHost(); // Get LocalHost refrence
			String name = inetaddress.getHostName(); // Get Host Name
			return name; // return Host Name
		} catch (Exception E) {
			E.printStackTrace(); // print Exception StackTrace
			return null;
		}
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@Override
	public Pageable createPageRequestSortedSingleColumn(int firstResult, int maxResults, String sortType,
			String sortField) {
		if (sortType.equalsIgnoreCase("ASC")) {
			LOGGER.info(PageRequest.of(firstResult, maxResults, Sort.by(new String[] { sortField }).descending())
					.toString());
			return PageRequest.of(firstResult, maxResults, Sort.by(new String[] { sortField }).ascending());
		}
		if (sortType.equalsIgnoreCase("DESC")) {
			LOGGER.info(PageRequest.of(firstResult, maxResults, Sort.by(new String[] { sortField }).descending())
					.toString());
			return PageRequest.of(firstResult, maxResults, Sort.by(new String[] { sortField }).descending());
		}
		LOGGER.info(PageRequest.of(firstResult, maxResults).toString());
		return PageRequest.of(firstResult, maxResults);
	}

	@Override
	public Pageable createPageRequestSortedMultiColumn(int firstResult, int maxResults, String sortType,
			String[] sortFields) {
		if (sortType.equalsIgnoreCase("ASC")) {
			return PageRequest.of(firstResult, maxResults, Sort.by(sortFields).ascending());
		}
		if (sortType.equalsIgnoreCase("DESC")) {
			return PageRequest.of(firstResult, maxResults, Sort.by(sortFields).descending());
		}
		return PageRequest.of(firstResult, maxResults);
	}

	@Override
	public Pageable createPageRequestUnsorted(int firstResult, int maxResults) {
		return PageRequest.of(firstResult, maxResults, Sort.unsorted());
	}

	@Override
	public void printClientInfo(HttpServletRequest request) {
		final String referer = getReferer(request);
		final String fullURL = getFullURL(request);
		final String clientIpAddr = getClientIpAddr(request);
		final String clientOS = getClientOS(request);
		final String clientBrowser = getClientBrowser(request);
		final String userAgent = getUserAgent(request);

		logger.info("\n" + "User Agent \t" + userAgent + "\n" + "Operating System\t" + clientOS + "\n"
				+ "Browser Name\t" + clientBrowser + "\n" + "IP Address\t" + clientIpAddr + "\n" + "Full URL\t"
				+ fullURL + "\n" + "Referrer\t" + referer);
	}

	public String getReferer(HttpServletRequest request) {
		final String referer = request.getHeader("referer");
		return referer;
	}

	public String getFullURL(HttpServletRequest request) {
		final StringBuffer requestURL = request.getRequestURL();
		final String queryString = request.getQueryString();

		final String result = queryString == null ? requestURL.toString()
				: requestURL.append('?').append(queryString).toString();

		return result;
	}

	// http://stackoverflow.com/a/18030465/1845894
	public String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// http://stackoverflow.com/a/18030465/1845894
	public String getClientOS(HttpServletRequest request) {
		final String browserDetails = request.getHeader("User-Agent");

		// =================OS=======================
		final String lowerCaseBrowser = browserDetails.toLowerCase();
		if (lowerCaseBrowser.contains("windows")) {
			return "Windows";
		} else if (lowerCaseBrowser.contains("mac")) {
			return "Mac";
		} else if (lowerCaseBrowser.contains("x11")) {
			return "Unix";
		} else if (lowerCaseBrowser.contains("android")) {
			return "Android";
		} else if (lowerCaseBrowser.contains("iphone")) {
			return "IPhone";
		} else {
			return "UnKnown, More-Info: " + browserDetails;
		}
	}

	// http://stackoverflow.com/a/18030465/1845894
	public String getClientBrowser(HttpServletRequest request) {
		final String browserDetails = request.getHeader("User-Agent");
		final String user = browserDetails.toLowerCase();
		String browser = "";
		// ===============Browser===========================
		if (user.contains("msie")) {
			String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera"))
				browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
			else if (user.contains("opr"))
				browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
						.replace("OPR", "Opera");
		} else if (user.contains("chrome")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
				|| (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			// browser=(userAgent.substring(userAgent.indexOf("MSIE")).split("
			// ")[0]).replace("/", "-");
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			browser = "IE";
		} else {
			browser = "UnKnown, More-Info: " + browserDetails;
		}

		return browser;
	}

	public String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}

	@Override
	public int getTenantId(HttpServletRequest httpServletRequest) {
		return Integer.parseInt((String) httpServletRequest.getAttribute("audience"));
	}
}
