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
package org.codelabor.example.validation.hibernate.safehtml.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

/**
 * @author Shin Sang-Jae
 *
 */
public class SafeHtmlWhitelistBasicWithImagesDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3516748756315448571L;
	/**
	 *
	 */
	@SafeHtml(whitelistType=WhiteListType.BASIC_WITH_IMAGES)
	private String stringData1;

	public String getStringData1() {
		return stringData1;
	}

	public void setStringData1(String stringData1) {
		this.stringData1 = stringData1;
	}

}
