package org.app.domain.utils;


import org.apache.commons.lang3.StringUtils;
import org.app.domain.enums.ErrorCodeEnum;
import org.app.domain.exceptions.PayloadDataException;

public final class Utils
{
	//--------------------------------------------------------------------------------------------------
	public static String extractOpenId(String url) throws PayloadDataException
	{
		String openId = StringUtils.substringAfterLast(url, "/");
		if (StringUtils.isBlank(openId))
			throw new PayloadDataException(ErrorCodeEnum.UNKNOWN_ERROR.name(), "OpenId is null or empty.");

		return openId;
	}
}
