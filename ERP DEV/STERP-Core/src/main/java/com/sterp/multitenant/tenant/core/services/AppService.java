package com.sterp.multitenant.tenant.core.services;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.dto.BranchResponse;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.repository.UserRepository;

@Service
public class AppService {

	Logger log = LoggerFactory.getLogger(AppService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	HttpSession session;

	@Autowired
	ServletContext ctx;

//	@Autowired
//	IModuleService moduleService;

	public BranchResponse getBranchCompanyByDefaultLoggedInBranch(HttpServletRequest httpServletRequest) {
		Integer defaultLoggedInBranchId = (Integer) httpServletRequest.getAttribute("defaultloginbranch");
		System.out.println(defaultLoggedInBranchId.longValue());
		Object[][] response = this.userRepository.findBranchAndCompanyNameByDefaultLoggedInBranch(defaultLoggedInBranchId.longValue());
		BranchResponse branchResponse = new BranchResponse();
		branchResponse.setBranchName((String)response[0][0]);
		branchResponse.setCompanyName((String)response[0][1]);
		return branchResponse;
	}

	public long hoursDifference(Date date1, Date date2) {
		final int MILLI_TO_HOUR = 1000 * 60 * 60;
		return (long) (date1.getTime() - date2.getTime()) / MILLI_TO_HOUR;
	}

	public UserEntity getCurrentUser() {
		UserEntity user = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if (userName != null) {
			user = this.userRepository.findByUsername(userName);
		}
		return user;
	}

	public LocalDate validateDate(String search) {

		try {
			return LocalDate.parse(search);
		} catch (Exception e) {
			return null;
		}
	}

	public UserEntity getCurrentUser(String username) {
		UserEntity user = null;
		if (username != null) {
			user = this.userRepository.findByUsername(username);
		}

		return user;
	}

	public String renameFolderOrFile(String destination, String renameTo) {
		String path = ctx.getRealPath(destination);
		File source = new File(path);
		if (source.exists()) {
			System.out.println("Path to Rename: " + path);
			System.out.println("Path existing: " + source);
			String newName = "";
			if (path.contains("\\")) {
				newName = path.substring(0, path.substring(0, path.length() - 1).lastIndexOf("\\")) + "\\" + renameTo;
			} else {
				newName = path.substring(0, path.substring(0, path.length() - 1).lastIndexOf("/")) + "/" + renameTo;
			}
			File destFile = new File(newName);
			if (source.isDirectory()) {

				if (source.renameTo(destFile)) {
					return "Success";
				} else {
					return "Failed";
				}
			} else if (source.isFile()) {
				if (source.renameTo(destFile)) {
					return "Success";
				} else {
					return "Failed";
				}
			}
		} else {
			return "Not Exists in local Storage";
		}
		return null;
	}

	public HttpHeaders getXCountHeader(long count) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Total-Count", String.valueOf(count));
		return headers;
	}

	public Pageable createPageRequest(int firstResult, int maxResults, String sortType, String sortField) {
		if (sortType.equalsIgnoreCase("ASC")) {
			return PageRequest.of(firstResult, maxResults, Sort.by(new String[] { sortField }).ascending());
		}
		if (sortType.equalsIgnoreCase("DESC")) {
			return PageRequest.of(firstResult, maxResults, Sort.by(new String[] { sortField }).descending());
		}
		return PageRequest.of(firstResult, maxResults);
	}

	public Set<String> getNullPropertyNames(Object source, String... extra) {
		BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();
		Set<String> emptyNames = new HashSet<>();
		for (PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) {
				emptyNames.add(pd.getName());
			}
		}
		for (String args : extra) {
			emptyNames.add(args);
		}
		return emptyNames;
	}

	public Long getSessionSite() {
		return Long.parseLong(session.getAttribute("siteId").toString());
	}

	/*
	 * @file parameter accepts File type argument which hold the template file for
	 * which the user has requested for Pre-proccessing. The file is check if is in
	 * .xlsx or .xls format or not.
	 * 
	 * @FormulaEvaluator evaluator evaluates the sheet present in the excel to fetch
	 * the data for the cell as string, numeric, boolean or date values and also
	 * evaluates the values calculated using the excel formula. If any row is
	 * indicated with empty cell that row is stored with empty @ArrayList. If any
	 * cell is merged in the more than one cell that information is stored in
	 * the @HashMap with Key @MERGED_RANGE, and data as
	 * 
	 * @DATA. NOTE: IF NO CELL IS MERGED IN MORE THAN ONE CELL THAT INFORMATION IS
	 * STORED AS Collections.emptyList() is the DATA key of the @HashMap
	 */

	public Map<String, Map<Integer, Object>> callReadFile(Object file, String fileName) throws IOException {
		Workbook workbook = null;
		log.debug("started creating instance of the workbook");
		if (file instanceof File) {
			FileInputStream excelInputStream = new FileInputStream((File) file);
			if (fileName.contains(".xlsx"))
				workbook = new XSSFWorkbook(excelInputStream);
			else if (fileName.contains(".xls"))
				workbook = new HSSFWorkbook(excelInputStream);
			else {
				excelInputStream.close();
				throw new CustomException("The file you have requested for must be in .xlsx or .xls format. "
						+ "Please try again with appropite file. If you still face the same problem please contact out technical team.");
			}
		}
		if (file instanceof MultipartFile) {
			byte[] fileByteArray = ((MultipartFile) file).getBytes();
			InputStream finStream = new ByteArrayInputStream(fileByteArray);
			if (fileName.contains(".xlsx"))
				workbook = new XSSFWorkbook(finStream);
			else if (fileName.contains(".xls"))
				workbook = new HSSFWorkbook(finStream);
			else {
				finStream.close();
				throw new CustomException("The file you have requested for must be in .xlsx or .xls format. "
						+ "Please try again with appropite file. If you still face the same problem please contact out technical team.");
			}
		}

		log.debug("created instance of workbook");
		Map<String, Map<Integer, Object>> excelReturn = new HashMap<String, Map<Integer, Object>>();
		int sheetNumber = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetNumber; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			excelReturn.put(sheet.getSheetName(), readFile(workbook, sheet));
		}
		workbook.close();
		return excelReturn;
	}

	public Map<Integer, Object> readFile(Workbook workbook, Sheet sheet) {
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		Map<Integer, Object> dataContent = new HashMap<>();
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);
			Map<String, List<String>> response = new HashMap<>();
			List<String> cellData = new ArrayList<String>();
			if (row != null) {
				int cellLastCount = row.getLastCellNum();
				for (int cn = 0; cn < cellLastCount; cn++) {
					Cell c = row.getCell(cn, MissingCellPolicy.RETURN_BLANK_AS_NULL);
					if (c != null) {
						DataFormatter formatter = new DataFormatter();
						cellData.add(formatter.formatCellValue(c, evaluator) + "");
					} else
						cellData.add("");
				}
			}
			response.put("DATA", cellData);
			response.put("MERGED_RANGE", getNbOfMergedRegions(sheet, i));
			dataContent.put(i, response);
		}

		return dataContent;

	}

	/*
	 * return address of the cell merge in one than one cell
	 */
	public static List<String> getNbOfMergedRegions(Sheet sheet, int row) {
		@SuppressWarnings("unused")
		int count = 0;
		List<String> addresses = new ArrayList<String>();
		for (int i = 0; i < sheet.getNumMergedRegions(); ++i) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			if (range.getFirstRow() <= row && range.getLastRow() >= row) {
				addresses.add(range.formatAsString());
				++count;
			}
		}
		return addresses;
	}

	public static File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	public BranchResponse getBranchCompanyByDefault(Long loginBranchId) {
		Object[][] response = this.userRepository.findBranchAndCompanyNameByDefaultLoggedInBranch(loginBranchId);
		BranchResponse branchResponse = new BranchResponse();
		branchResponse.setBranchName((String) response[0][0]);
		branchResponse.setCompanyName((String) response[0][1]);
		return branchResponse;
	}
}
