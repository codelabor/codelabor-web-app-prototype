/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codelabor.system.security.authentication.account.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.codelabor.system.security.SecurityConstants;
import org.codelabor.system.security.authentication.account.dto.AccountDto;
import org.codelabor.system.security.authentication.account.manager.AccountManager;
import org.codelabor.system.security.propertyeditors.SimpleGrantedAuthoritiesPropertiesEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author SHIN Sang-jae
 *
 */
@Controller
@RequestMapping("/system/security/authentication/account/")
public class AccountController { // NOPMD by "SHIN Sang-jae"

	private final Logger logger = LoggerFactory.getLogger(AccountController.class);

	private static final String CREATE_VIEW_NAME = "system/security/authentication/account/create";
	private static final String UPDATE_VIEW_NAME = "system/security/authentication/account/update";
	private static final String LIST_VIEW_NAME = "system/security/authentication/account/list";
	private static final String LIST_URL = "/system/security/authentication/account/listAccount";
	private static final String READ_VIEW_NAME = "system/security/authentication/account/read";
	private static final String READ_URL = "/system/security/authentication/account/readAccount";
	private static final String EXPORT_VIEW_NAME = "accountListExcelView";

	@Autowired
	private AccountManager accountManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// @Autowired
	// private DeptService deptService;
	@Autowired
	private MessageSource messageSource;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(GrantedAuthority.class, new SimpleGrantedAuthoritiesPropertiesEditor());
	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public ModelAndView createAccount(@Valid AccountDto accountDto, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
		logger.debug("createAccount");

		List<String> successMessages = new ArrayList<String>();
		ModelAndView mav = new ModelAndView();

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				logger.error(error.getDefaultMessage());
			}
			mav.addObject("authoritiesMap", this.getAuthoritiesMap());
			mav.setViewName(CREATE_VIEW_NAME);
		} else {

			// encode password
			accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
			accountDto.setPasswordConfirm(null);

			// use default value or not
			if (accountDto.getAuthorities().isEmpty()) {
				Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority(SecurityConstants.DEFAULT_ROLE));
				accountDto.setAuthorities(authorities);
			}
			if (accountDto.getEnabled() == null) {
				accountDto.setEnabled(SecurityConstants.DEFAULT_ENABLED);
			}
			if (accountDto.getAccountNonExpired() == null) {
				accountDto.setAccountNonExpired(SecurityConstants.DEFAULT_ACCOUNT_NON_EXPIRED);
			}
			if (accountDto.getCredentialsNonExpired() == null) {
				accountDto.setCredentialsNonExpired(SecurityConstants.DEFAULT_CREDENTIALS_NON_EXPIRED);
			}
			if (accountDto.getAccountNonLocked() == null) {
				accountDto.setAccountNonLocked(SecurityConstants.DEFAULT_ACCOUNT_NON_LOCKED);
			}
			if (accountDto.getGraceLoginsRemaining() == null) {
				accountDto.setGraceLoginsRemaining(SecurityConstants.DEFAULT_GRACE_LOGINS_REMAINING);
			}

			// invoke service
			accountManager.createUser(accountDto);
			int affectedRowCount = 1;

			StringBuilder sb = new StringBuilder();
			sb.append("redirect:");
			sb.append(READ_URL).append("?username=").append(accountDto.getUsername());

			logger.debug("view name: {}", sb.toString());
			mav.setViewName(sb.toString());

			String message = messageSource.getMessage("success.create.completed.with.count", new Object[] { affectedRowCount }, locale);
			logger.debug("message: {}", message);
			successMessages.add(message);

			redirectAttributes.addFlashAttribute("successMessages", successMessages);
		}
		return mav;
	}

	// @RequestMapping(value = "/deleteEmpList", method = RequestMethod.POST)
	// public ModelAndView deleteEmpList(@Valid IntegerIdListDto
	// integerIdListDto,
	// BindingResult result, EmpSearchConditionDto empSearchConditionDto,
	// RedirectAttributes redirectAttributes, Locale locale) {
	// logger.debug("deleteAccount");
	// logger.debug("integerIdListDto: {}", integerIdListDto);
	// logger.debug("empSearchConditionDto: {}", empSearchConditionDto);
	// logger.debug("result: {}", result);
	//
	// List<String> successMessages = new ArrayList<String>();
	// ModelAndView mav = new ModelAndView();
	//
	// if (result.hasErrors()) {
	// for (ObjectError error : result.getAllErrors()) {
	// logger.error("error: {}", error.getDefaultMessage());
	// }
	//
	// Integer pageNo = empSearchConditionDto.getPageNo();
	//
	// List<EmpDto> empDtoList = null;
	// if ((pageNo != null) && (pageNo > 0)) {
	// empDtoList = empService
	// .selectEmpListByConditionWithPagination(empSearchConditionDto);
	// } else {
	// empDtoList = empService
	// .selectEmpListByCondition(empSearchConditionDto);
	// }
	// mav.addObject(empDtoList);
	// mav.addObject(empSearchConditionDto);
	// mav.setViewName(LIST_VIEW_NAME);
	// } else {
	// int affectedRowCount = empService.deleteEmpList(integerIdListDto
	// .getId());
	// StringBuilder sb = new StringBuilder();
	// sb.append("redirect:").append(LIST_URL);
	// logger.debug("view name: {}", sb.toString());
	// mav.setViewName(sb.toString());
	//
	// String message = messageSource.getMessage(
	// "success.delete.completed.with.count",
	// new Object[] { affectedRowCount }, locale);
	// logger.debug("message: {}", message);
	// successMessages.add(message);
	//
	// redirectAttributes.addFlashAttribute("successMessages",
	// successMessages);
	// redirectAttributes.addFlashAttribute(empSearchConditionDto);
	// }
	// return mav;
	// }
	//
	// // servlet 2.5
	// private List<EmpDto> fileToDtoList(MultipartFile file,
	// List<String> failureMessages) throws IllegalArgumentException,
	// InvalidFormatException, IOException { // NOPMD by "SHIN Sang-jae"
	//
	// Workbook wb = WorkbookFactory.create(file.getInputStream());
	// int numberOfSheets = wb.getNumberOfSheets();
	// logger.debug("numberOfSheets: {}", numberOfSheets);
	//
	// // prepare model
	// List<EmpDto> empDtoList = new ArrayList<EmpDto>();
	//
	// // set effective position
	// int effectiveFirstSheetIndex = 0;
	// int effectiveLastSheetIndex = numberOfSheets - 1;
	//
	// // traverse sheet
	// StringBuilder sb = new StringBuilder();
	// for (int i = effectiveFirstSheetIndex; i <= effectiveLastSheetIndex; i++)
	// {
	// Sheet sheet = wb.getSheetAt(i);
	// String sheetName = sheet.getSheetName();
	// logger.debug("sheetName: {}", sheetName);
	// int firstRowNum = sheet.getFirstRowNum();
	// int lastRowNum = sheet.getLastRowNum();
	// logger.debug("firstRowNum: {},  lastRowNum: {}", firstRowNum,
	// lastRowNum);
	//
	// // set effective position
	// int effectiveFirstRowIndex = 1; // header row: 0
	// int effectiveLastRowIndex = lastRowNum;
	//
	// // traverse row
	// for (int j = effectiveFirstRowIndex; j <= effectiveLastRowIndex; j++) {
	// // prepare model
	// EmpDto empDto = new EmpDto(); // NOPMD by "SHIN Sang-jae"
	//
	// Row row = sheet.getRow(j);
	// int rowNum = row.getRowNum();
	// int firstCellNum = row.getFirstCellNum();
	// int lastCellNum = row.getLastCellNum();
	// logger.debug("rowNum: {}, firstCellNum: {},  lastCellNum: {}",
	// rowNum, firstCellNum, lastCellNum);
	//
	// // set effective position
	// int effectiveFirstCellIndex = firstCellNum;
	// int effectiveLastCellIndex = lastCellNum - 1;
	//
	// // traverse cell
	// for (int k = effectiveFirstCellIndex; k <= effectiveLastCellIndex; k++) {
	// Cell cell = row.getCell(k);
	// if (cell != null) {
	// int rowIndex = cell.getRowIndex();
	// int columnIndex = cell.getColumnIndex();
	// CellReference cellRef = new CellReference(rowIndex,
	// columnIndex); // NOPMD by "SHIN Sang-jae"
	//
	// logger.debug(
	// "cellRef: {}, rowIndex: {}, columnIndex: {}",
	// cellRef, rowIndex, columnIndex);
	// // populate dto
	// switch (k) {
	// case 0: // EMPNO
	// empDto.setEmpNo(((Double) cell
	// .getNumericCellValue()).intValue());
	// break;
	// case 1: // ENAME
	// empDto.setEname(cell.getRichStringCellValue()
	// .toString());
	// break;
	// case 2: // JOB
	// empDto.setJob(cell.getRichStringCellValue()
	// .toString());
	// break;
	// case 3: // MGR
	// empDto.setMgr(((Double) cell.getNumericCellValue())
	// .intValue());
	// break;
	// case 4: // HIREDATE
	// empDto.setHireDate(cell.getDateCellValue());
	// break;
	// case 5: // SAL
	// //
	// http://stackoverflow.com/questions/12395281/convert-double-to-bigdecimal-and-set-bigdecimal-precision
	// empDto.setSal(BigDecimal.valueOf(cell
	// .getNumericCellValue()));
	// break;
	// case 6: // COMM
	// //
	// http://stackoverflow.com/questions/12395281/convert-double-to-bigdecimal-and-set-bigdecimal-precision
	// empDto.setComm(BigDecimal.valueOf(cell
	// .getNumericCellValue()));
	// break;
	// case 7: // DEPTNO
	// empDto.setDeptNo(((Double) cell
	// .getNumericCellValue()).intValue());
	// break;
	// default:
	// break;
	// }
	// }
	// }
	// logger.debug("empDto: {}", empDto);
	//
	// // validate
	// Validator validator = Validation.buildDefaultValidatorFactory()
	// .getValidator();
	// Set<ConstraintViolation<EmpDto>> violations = validator
	// .validate(empDto);
	//
	// if (violations.isEmpty()) {
	// // do all or nothing
	// empDtoList.add(empDto);
	// } else {
	// // add failure message
	// sb.setLength(0); // init StringBuilder for reuse
	// for (ConstraintViolation<EmpDto> violation : violations) {
	// String propertyPath = violation.getPropertyPath()
	// .toString();
	// String message = violation.getMessage();
	// sb.append(message);
	// sb.append(" (row: ").append(j).append(", property: ")
	// .append(propertyPath).append(')');
	// failureMessages.add(sb.toString());
	// logger.error(sb.toString());
	// sb.setLength(0);
	// }
	// }
	// }
	// }
	// return empDtoList;
	// }
	//
	// // servlet 3.0
	// private List<EmpDto> fileToDtoList(Part file, List<String>
	// failureMessages)
	// throws IllegalArgumentException, InvalidFormatException,
	// IOException { // NOPMD by "SHIN Sang-jae"
	//
	// Workbook wb = WorkbookFactory.create(file.getInputStream());
	// int numberOfSheets = wb.getNumberOfSheets();
	// logger.debug("numberOfSheets: {}", numberOfSheets);
	//
	// // prepare model
	// List<EmpDto> empDtoList = new ArrayList<EmpDto>();
	//
	// // set effective position
	// int effectiveFirstSheetIndex = 0;
	// int effectiveLastSheetIndex = numberOfSheets - 1;
	//
	// // traverse sheet
	// StringBuilder sb = new StringBuilder();
	// for (int i = effectiveFirstSheetIndex; i <= effectiveLastSheetIndex; i++)
	// {
	// Sheet sheet = wb.getSheetAt(i);
	// String sheetName = sheet.getSheetName();
	// logger.debug("sheetName: {}", sheetName);
	// int firstRowNum = sheet.getFirstRowNum();
	// int lastRowNum = sheet.getLastRowNum();
	// logger.debug("firstRowNum: {},  lastRowNum: {}", firstRowNum,
	// lastRowNum);
	//
	// // set effective position
	// int effectiveFirstRowIndex = 1; // header row: 0
	// int effectiveLastRowIndex = lastRowNum;
	//
	// // traverse row
	// for (int j = effectiveFirstRowIndex; j <= effectiveLastRowIndex; j++) {
	// // prepare model
	// EmpDto empDto = new EmpDto(); // NOPMD by "SHIN Sang-jae"
	//
	// Row row = sheet.getRow(j);
	// int rowNum = row.getRowNum();
	// int firstCellNum = row.getFirstCellNum();
	// int lastCellNum = row.getLastCellNum();
	// logger.debug("rowNum: {}, firstCellNum: {},  lastCellNum: {}",
	// rowNum, firstCellNum, lastCellNum);
	//
	// // set effective position
	// int effectiveFirstCellIndex = firstCellNum;
	// int effectiveLastCellIndex = lastCellNum - 1;
	//
	// // traverse cell
	// for (int k = effectiveFirstCellIndex; k <= effectiveLastCellIndex; k++) {
	// Cell cell = row.getCell(k);
	// if (cell != null) {
	// int rowIndex = cell.getRowIndex();
	// int columnIndex = cell.getColumnIndex();
	// CellReference cellRef = new CellReference(rowIndex,
	// columnIndex); // NOPMD by "SHIN Sang-jae"
	//
	// logger.debug(
	// "cellRef: {}, rowIndex: {}, columnIndex: {}",
	// cellRef, rowIndex, columnIndex);
	// // populate dto
	// switch (k) {
	// case 0: // EMPNO
	// empDto.setEmpNo(((Double) cell
	// .getNumericCellValue()).intValue());
	// break;
	// case 1: // ENAME
	// empDto.setEname(cell.getRichStringCellValue()
	// .toString());
	// break;
	// case 2: // JOB
	// empDto.setJob(cell.getRichStringCellValue()
	// .toString());
	// break;
	// case 3: // MGR
	// empDto.setMgr(((Double) cell.getNumericCellValue())
	// .intValue());
	// break;
	// case 4: // HIREDATE
	// empDto.setHireDate(cell.getDateCellValue());
	// break;
	// case 5: // SAL
	// //
	// http://stackoverflow.com/questions/12395281/convert-double-to-bigdecimal-and-set-bigdecimal-precision
	// empDto.setSal(BigDecimal.valueOf(cell
	// .getNumericCellValue()));
	// break;
	// case 6: // COMM
	// //
	// http://stackoverflow.com/questions/12395281/convert-double-to-bigdecimal-and-set-bigdecimal-precision
	// empDto.setComm(BigDecimal.valueOf(cell
	// .getNumericCellValue()));
	// break;
	// case 7: // DEPTNO
	// empDto.setDeptNo(((Double) cell
	// .getNumericCellValue()).intValue());
	// break;
	// default:
	// break;
	// }
	// }
	// }
	// logger.debug("empDto: {}", empDto);
	//
	// // validate
	// Validator validator = Validation.buildDefaultValidatorFactory()
	// .getValidator();
	// Set<ConstraintViolation<EmpDto>> violations = validator
	// .validate(empDto);
	//
	// if (violations.isEmpty()) {
	// // do all or nothing
	// empDtoList.add(empDto);
	// } else {
	// // add failure message
	// sb.setLength(0); // init StringBuilder for reuse
	// for (ConstraintViolation<EmpDto> violation : violations) {
	// String propertyPath = violation.getPropertyPath()
	// .toString();
	// String message = violation.getMessage();
	// sb.append(message);
	// sb.append(" (row: ").append(j).append(", property: ")
	// .append(propertyPath).append(')');
	// failureMessages.add(sb.toString());
	// logger.error(sb.toString());
	// sb.setLength(0);
	// }
	// }
	// }
	// }
	// return empDtoList;
	// }

	private Map<String, String> getAuthoritiesMap() {
		Map<String, String> authoritiesMap = new LinkedHashMap<String, String>();
		authoritiesMap.put(SecurityConstants.ROLE_ADMINISTRATOR, messageSource.getMessage("label.role.administrator", null, LocaleContextHolder.getLocale()));
		authoritiesMap.put(SecurityConstants.ROLE_MANAGER, messageSource.getMessage("label.role.manager", null, LocaleContextHolder.getLocale()));
		authoritiesMap.put(SecurityConstants.ROLE_USER, messageSource.getMessage("label.role.user", null, LocaleContextHolder.getLocale()));
		logger.debug("authoritiesMap: {}", authoritiesMap);
		return authoritiesMap;
	}

	// servlet 2.5
	// @RequestMapping(value = "/importEmpList", method = RequestMethod.POST)
	// public ModelAndView importEmpListMultipartFile(MultipartFile file,
	// RedirectAttributes redirectAttributes, Locale locale) {
	// logger.debug("importAccount");
	//
	// List<String> successMessages = new ArrayList<String>();
	// List<String> failureMessages = new ArrayList<String>();
	// ModelAndView mav = new ModelAndView();
	// int affectedRowCount = 0;
	//
	// if ((file == null) || file.isEmpty()) {
	// String message = messageSource.getMessage(
	// "errors.file.does.not.exist",
	// new Object[] { affectedRowCount }, locale);
	// logger.debug("message: {}", message);
	// failureMessages.add(message);
	//
	// // log error
	// logger.error(message);
	// } else {
	// logger.debug("contentType: {}", file.getContentType());
	// logger.debug("name: {}", file.getName());
	// logger.debug("originalFileName: {}", file.getOriginalFilename());
	// logger.debug("size: {}", file.getSize());
	//
	// List<EmpDto> empDtoList = null;
	//
	// // convert excel file to dto list
	// try {
	// empDtoList = fileToDtoList(file, failureMessages);
	// logger.debug("empDtoList: {}", empDtoList);
	// } catch (InvalidFormatException e) {
	// // set errror message
	// String message = messageSource.getMessage(
	// "errors.file.format.invalid",
	// new Object[] { affectedRowCount }, locale);
	// logger.debug("message: {}", message);
	// failureMessages.add(message);
	//
	// // log error
	// logger.error(message);
	// } catch (IOException e) {
	// // set error message
	// String message = messageSource.getMessage(
	// "errors.file.io.failed",
	// new Object[] { affectedRowCount }, locale);
	// logger.debug("message: {}", message);
	// failureMessages.add(message);
	//
	// // log error
	// logger.error(message);
	// } catch (IllegalArgumentException e) {
	// // set error message
	// String message = messageSource.getMessage(
	// "errors.file.type.unsupported",
	// new Object[] { affectedRowCount }, locale);
	// logger.debug("message: {}", message);
	// failureMessages.add(message);
	//
	// // log error
	// logger.error(message);
	// }
	//
	// if (failureMessages.isEmpty()) {
	// // insert dto list
	// try {
	// affectedRowCount = empService.insertEmpList(empDtoList);
	// // set success message
	// String message = messageSource.getMessage(
	// "success.import.completed.with.count",
	// new Object[] { affectedRowCount }, locale);
	// successMessages.add(message);
	//
	// // log info
	// logger.info("message: {}", message);
	// } catch (DuplicateKeyException e) {
	// // set error message
	// String message = messageSource.getMessage(
	// "errors.data.duplicated",
	// new Object[] { affectedRowCount }, locale);
	// logger.debug("message: {}", message);
	// failureMessages.add(message);
	//
	// // log error
	// logger.error(message);
	// }
	// }
	// }
	//
	// // set messages
	// redirectAttributes
	// .addFlashAttribute("successMessages", successMessages);
	// redirectAttributes
	// .addFlashAttribute("failureMessages", failureMessages);
	//
	// // redirect
	// StringBuilder sb = new StringBuilder();
	// sb.append("redirect:").append(LIST_URL);
	// logger.debug("view name: {}", sb.toString());
	// mav.setViewName(sb.toString());
	// return mav;
	// }
	// servlet 3.0
	// @RequestMapping(value = "/importEmpList2", method = RequestMethod.POST)
	// public ModelAndView importEmpListMultipartFile2(Part file,
	// RedirectAttributes redirectAttributes, Locale locale) {
	// logger.debug("importAccount");
	//
	// List<String> successMessages = new ArrayList<String>();
	// List<String> failureMessages = new ArrayList<String>();
	// ModelAndView mav = new ModelAndView();
	// int affectedRowCount = 0;
	//
	// if ((file == null) || (file.getSize() <= 0)) {
	// String message = messageSource.getMessage(
	// "errors.file.does.not.exist",
	// new Object[] { affectedRowCount }, locale);
	// logger.debug("message: {}", message);
	// failureMessages.add(message);
	//
	// // log error
	// logger.error(message);
	// } else {
	// logger.debug("contentType: {}", file.getContentType());
	// logger.debug("name: {}", file.getName());
	// logger.debug("size: {}", file.getSize());
	//
	// Collection<String> names = file.getHeaderNames();
	// Iterator<String> iter = names.iterator();
	// while (iter.hasNext()) {
	// String headerName = iter.next();
	// logger.debug("header name: {}, value: {}", headerName,
	// file.getHeader(headerName));
	// }
	//
	// List<EmpDto> empDtoList = null;
	//
	// // convert excel file to dto list
	// try {
	// empDtoList = fileToDtoList(file, failureMessages);
	// logger.debug("empDtoList: {}", empDtoList);
	// } catch (InvalidFormatException e) {
	// // set errror message
	// String message = messageSource.getMessage(
	// "errors.file.format.invalid",
	// new Object[] { affectedRowCount }, locale);
	// logger.debug("message: {}", message);
	// failureMessages.add(message);
	//
	// // log error
	// logger.error(message);
	// } catch (IOException e) {
	// // set error message
	// String message = messageSource.getMessage(
	// "errors.file.io.failed",
	// new Object[] { affectedRowCount }, locale);
	// logger.debug("message: {}", message);
	// failureMessages.add(message);
	//
	// // log error
	// logger.error(message);
	// } catch (IllegalArgumentException e) {
	// // set error message
	// String message = messageSource.getMessage(
	// "errors.file.type.unsupported",
	// new Object[] { affectedRowCount }, locale);
	// logger.debug("message: {}", message);
	// failureMessages.add(message);
	//
	// // log error
	// logger.error(message);
	// }
	//
	// if (failureMessages.isEmpty()) {
	// // insert dto list
	// try {
	// affectedRowCount = empService.insertEmpList(empDtoList);
	// // set success message
	// String message = messageSource.getMessage(
	// "success.import.completed.with.count",
	// new Object[] { affectedRowCount }, locale);
	// successMessages.add(message);
	//
	// // log info
	// logger.info("message: {}", message);
	// } catch (DuplicateKeyException e) {
	// // set error message
	// String message = messageSource.getMessage(
	// "errors.data.duplicated",
	// new Object[] { affectedRowCount }, locale);
	// logger.debug("message: {}", message);
	// failureMessages.add(message);
	//
	// // log error
	// logger.error(message);
	// }
	// }
	// }
	//
	// // set messages
	// redirectAttributes
	// .addFlashAttribute("successMessages", successMessages);
	// redirectAttributes
	// .addFlashAttribute("failureMessages", failureMessages);
	//
	// // redirect
	// StringBuilder sb = new StringBuilder();
	// sb.append("redirect:").append(LIST_URL);
	// logger.debug("view name: {}", sb.toString());
	// mav.setViewName(sb.toString());
	// return mav;
	// }
	// @RequestMapping(value = { "/listAccount", "/exportEmpList",
	// "/exportEmpListOnCurrentPage" }, method = RequestMethod.GET)
	// public ModelAndView listEmp(
	// @Valid EmpSearchConditionDto empSearchConditionDto,
	// BindingResult result,
	// HttpServletRequest request,
	// @ModelAttribute("successMessages") ArrayList<String> successMessages,
	// RedirectAttributes redirectAttributes, Locale locale) { // NOPMD by
	// // "SHIN Sang-jae"
	// logger.debug("listAccount");
	// String pathWithinHandlerMapping = (String) request
	// .getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	// logger.debug("pathWithinHandlerMapping: {}", pathWithinHandlerMapping);
	//
	// ModelAndView mav = new ModelAndView();
	//
	// if (result.hasErrors()) {
	// for (ObjectError error : result.getAllErrors()) {
	// logger.error(error.getDefaultMessage());
	// }
	//
	// Integer numberOfRow = 0;
	//
	// // set message
	// String message = messageSource.getMessage(
	// "success.search.completed.with.count",
	// new Object[] { numberOfRow }, locale);
	// logger.debug("message: {}", message);
	//
	// // add message
	// successMessages.add(message);
	// mav.setViewName(LIST_VIEW_NAME);
	// } else {
	//
	// // export all data
	// // set up pageNo = 0 (user input cannot set pageNo = 0)
	// if (pathWithinHandlerMapping.endsWith("exportEmpList")) {
	// empSearchConditionDto.setPageNo(0);
	// }
	//
	// logger.debug("empSearchConditionDto: {}", empSearchConditionDto);
	// Integer pageNo = empSearchConditionDto.getPageNo();
	//
	// List<EmpDto> empDtoList = null;
	// if ((pageNo != null) && (pageNo > 0)) {
	// empDtoList = empService
	// .selectEmpListByConditionWithPagination(empSearchConditionDto);
	// } else {
	// empDtoList = empService
	// .selectEmpListByCondition(empSearchConditionDto);
	// }
	//
	// Integer numberOfRow = empService
	// .getNumberOfRow(empSearchConditionDto);
	//
	// // set message
	// String message = messageSource.getMessage(
	// "success.search.completed.with.count",
	// new Object[] { numberOfRow }, locale);
	// logger.debug("message: {}", message);
	//
	// // add message
	// successMessages.add(message);
	//
	// mav.addObject(empDtoList);
	// if (pathWithinHandlerMapping.equals(LIST_URL)) {
	// mav.addObject(new IntegerIdListDto());
	// mav.addObject("numberOfRow", numberOfRow);
	// mav.addObject("successMessages", successMessages);
	// mav.setViewName(LIST_VIEW_NAME);
	// } else {
	// mav.addObject("filename", "emp-list.xls");
	// mav.setViewName(EXPORT_VIEW_NAME);
	// }
	// }
	// return mav;
	// }

	@RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	public ModelAndView prepareCreateAccount() {
		logger.debug("prepareCreateAccount");

		// set default value
		AccountDto accountDto = new AccountDto();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(SecurityConstants.DEFAULT_ROLE));
		accountDto.setAuthorities(authorities);
		accountDto.setEnabled(SecurityConstants.DEFAULT_ENABLED);
		accountDto.setAccountNonExpired(SecurityConstants.DEFAULT_ACCOUNT_NON_EXPIRED);
		accountDto.setCredentialsNonExpired(SecurityConstants.DEFAULT_CREDENTIALS_NON_EXPIRED);
		accountDto.setAccountNonLocked(SecurityConstants.DEFAULT_ACCOUNT_NON_LOCKED);
		accountDto.setGraceLoginsRemaining(SecurityConstants.DEFAULT_GRACE_LOGINS_REMAINING);

		ModelAndView mav = new ModelAndView();
		mav.addObject(accountDto);
		mav.addObject("authoritiesMap", this.getAuthoritiesMap());
		mav.setViewName(CREATE_VIEW_NAME);
		return mav;
	}

	@RequestMapping(value = "/updateAccount", method = RequestMethod.GET)
	public ModelAndView prepareUpdateAccount(String username) {
		logger.debug("prepareUpdateAccount");
		logger.debug("username: {}", username);
		ModelAndView mav = new ModelAndView();

		AccountDto accountDto = (AccountDto) accountManager.loadUserByUsername(username);

		mav.addObject(accountDto);
		mav.addObject("authoritiesMap", this.getAuthoritiesMap());
		mav.setViewName(UPDATE_VIEW_NAME);
		return mav;
	}

	@RequestMapping(value = "/readAccount", method = RequestMethod.GET)
	public ModelAndView readAccount(String username) {
		logger.debug("readAccount");
		logger.debug("username: {}", username);
		AccountDto accountDto = (AccountDto) accountManager.loadUserByUsername(username);
		logger.debug("accountDto: {}", accountDto);

		ModelAndView mav = new ModelAndView();
		mav.addObject(accountDto);
		mav.setViewName(READ_VIEW_NAME);
		return mav;
	}

	@RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
	public ModelAndView updateAccount(@Valid AccountDto accountDto, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
		logger.debug("updateAccount");

		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				logger.error(error.getDefaultMessage());
			}
			mav.setViewName(UPDATE_VIEW_NAME);
		} else {
			accountManager.createUser(accountDto);
			int affectedRowCount = 1;

			StringBuilder sb = new StringBuilder();
			sb.append("redirect:");
			sb.append(READ_URL).append("?username=").append(accountDto.getUsername());
			logger.debug("view name: {}", sb.toString());
			mav.setViewName(sb.toString());

			// set message
			String message = messageSource.getMessage("success.update.completed.with.count", new Object[] { affectedRowCount }, locale);
			logger.debug("message: {}", message);

			List<String> successMessages = new ArrayList<String>();
			successMessages.add(message);

			redirectAttributes.addFlashAttribute("successMessages", successMessages);
		}
		return mav;
	}
}
