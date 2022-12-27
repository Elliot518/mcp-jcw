package com.mcp.infrastructure.common.domain.pagination;


public class PageView extends BaseView
{

	/**
	 *
	 */
	private static final long serialVersionUID = -7313781053027889222L;

	private int pageIndex;

	private int pageSize = 20;

	private int totalRecords;

	private Object objList;

	public Object getObjList()
	{

		return objList;
	}

	public void setObjList(Object objList)
	{

		this.objList = objList;
	}

	public int getPageIndex()
	{

		return pageIndex;
	}

	public void setPageIndex(int pageIndex)
	{

		this.pageIndex = pageIndex;
	}

	public int getPageSize()
	{

		return pageSize;
	}

	public void setPageSize(int pageSize)
	{

		this.pageSize = pageSize;
	}

	public int getTotalRecords()
	{

		return totalRecords;
	}

	public void setTotalRecords(int totalRecords)
	{

		this.totalRecords = totalRecords;
	}

	public int getRecordStart()
	{

		int recordStart = (pageIndex - 1) * pageSize;
		if (recordStart < 0)
		{
			recordStart = 0;
		}

		return recordStart;
	}

	public int getRecordEnd()
	{

		return pageIndex * pageSize;
	}

	public int getTotalPage()
	{

		return (int) Math.ceil(totalRecords * 1.0 / pageSize);
	}
}
