package bca.pricing.properties;

import org.apache.commons.lang.StringUtils;

public abstract class BasePropertiesConstants {
	protected static String arrayDelimiter;

	protected static String[] convertToStringArray(String data) {
		String[] strArr;
		if (StringUtils.isBlank(data))
			return null;
		if (!data.contains(","))
			return new String[] { data };
		data = data.substring(1, data.length() - 1);
		strArr = data.split(arrayDelimiter);
		for (int i = 0; i < strArr.length; i++)
			strArr[i] = strArr[i].trim();
		return strArr;

	}

}
