package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;
import java.util.List;

import com.sterp.multitenant.tenant.settings.template.entity.dto.DefaultSortDto;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

public class CommonPagingResponse<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6786342624959607682L;
	private List<T> content;
	private int numberOfElements;
	private int size;
	private long totalElements;
	private long totalPages;
	private List<ScreenFilterDTO> filter;
	private List<DefaultSortDto> defaultSort;

	public CommonPagingResponse(List<T> content, int numberOfElements, int size, long totalElements, long totalPages,
			List<ScreenFilterDTO> filter, List<DefaultSortDto> defaultSort) {
		super();
		this.content = content;
		this.numberOfElements = numberOfElements;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.filter = filter;
		this.defaultSort = defaultSort;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public List<ScreenFilterDTO> getFilter() {
		return filter;
	}

	public void setFilter(List<ScreenFilterDTO> filter) {
		this.filter = filter;
	}

	public List<DefaultSortDto> getDefaultSort() {
		return defaultSort;
	}

	public void setDefaultSort(List<DefaultSortDto> defaultSort) {
		this.defaultSort = defaultSort;
	}

	@Override
	public String toString() {
		return "CommonPagingResponse [content=" + content + ", numberOfElements=" + numberOfElements + ", size=" + size
				+ ", totalElements=" + totalElements + ", totalPages=" + totalPages + ", filter=" + filter
				+ ", defaultSort=" + defaultSort + "]";
	}

}
