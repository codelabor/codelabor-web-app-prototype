package org.codelabor.system.data.pagination.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

import org.codelabor.system.web.taglib.PaginationConstants;

@XmlRootElement(name = "paginationSearchCondition")
public class PaginationSearchConditionDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5671574976280236138L;

	@Min(1)
	protected Integer pageNo = 1;

	@Min(1)
	protected Integer maxRowPerPage = PaginationConstants.MAX_ROW_PER_PAGE;

	protected Integer upperBound = PaginationConstants.MAX_ROW_PER_PAGE;

	protected Integer lowerBound = 1;

	/**
	 *
	 */
	public PaginationSearchConditionDto() {
		super();
	}

	/**
	 * @param pageNo
	 * @param maxRowPerPage
	 */
	public PaginationSearchConditionDto(Integer pageNo, Integer maxRowPerPage) {
		super();
		this.pageNo = pageNo;
		this.maxRowPerPage = maxRowPerPage;
	}

	/**
	 * @return the lowerBound
	 */
	public Integer getLowerBound() {
		return (pageNo - 1) * maxRowPerPage;
	}

	/**
	 * @return the maxRowPerPage
	 */
	public Integer getMaxRowPerPage() {
		return maxRowPerPage;
	}

	/**
	 * @return the pageNo
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * @return the upperBound
	 */
	public Integer getUpperBound() {
		return pageNo * maxRowPerPage;
	}

	/**
	 * @param maxRowPerPage
	 *            the maxRowPerPage to set
	 */
	public void setMaxRowPerPage(Integer maxRowPerPage) {
		this.maxRowPerPage = maxRowPerPage;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param upperBound
	 *            the upperBound to set
	 */
	public void setUpperBound(Integer upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * @param lowerBound
	 *            the lowerBound to set
	 */
	public void setLowerBound(Integer lowerBound) {
		this.lowerBound = lowerBound;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lowerBound == null) ? 0 : lowerBound.hashCode());
		result = prime * result + ((maxRowPerPage == null) ? 0 : maxRowPerPage.hashCode());
		result = prime * result + ((pageNo == null) ? 0 : pageNo.hashCode());
		result = prime * result + ((upperBound == null) ? 0 : upperBound.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PaginationSearchConditionDto)) {
			return false;
		}
		PaginationSearchConditionDto other = (PaginationSearchConditionDto) obj;
		if (lowerBound == null) {
			if (other.lowerBound != null) {
				return false;
			}
		} else if (!lowerBound.equals(other.lowerBound)) {
			return false;
		}
		if (maxRowPerPage == null) {
			if (other.maxRowPerPage != null) {
				return false;
			}
		} else if (!maxRowPerPage.equals(other.maxRowPerPage)) {
			return false;
		}
		if (pageNo == null) {
			if (other.pageNo != null) {
				return false;
			}
		} else if (!pageNo.equals(other.pageNo)) {
			return false;
		}
		if (upperBound == null) {
			if (other.upperBound != null) {
				return false;
			}
		} else if (!upperBound.equals(other.upperBound)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaginationSearchConditionDto [pageNo=");
		builder.append(pageNo);
		builder.append(", maxRowPerPage=");
		builder.append(maxRowPerPage);
		builder.append(", upperBound=");
		builder.append(upperBound);
		builder.append(", lowerBound=");
		builder.append(lowerBound);
		builder.append("]");
		return builder.toString();
	}

}
