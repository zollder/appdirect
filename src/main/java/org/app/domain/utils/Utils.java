package org.app.domain.utils;


import org.apache.commons.lang3.StringUtils;

public final class Utils
{
	//--------------------------------------------------------------------------------------------------
	public static String extractOpenId(String url)
	{
		return StringUtils.substringAfterLast(url, "/");
	}
}
