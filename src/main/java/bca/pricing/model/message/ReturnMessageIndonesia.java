package bca.pricing.model.message;

import java.util.HashMap;

public class ReturnMessageIndonesia {

	private static HashMap<String, String> messageMap;
	
	static {
		messageMap = new HashMap<String, String>();
		messageMap.put(ReturnCode.SERVICE_SUCCESS, "Transaksi Berhasil");
		messageMap.put(ReturnCode.SERVICE_FORBIDDEN, "Akses tidak diperbolehkan");
		messageMap.put(ReturnCode.SERVICE_GENERAL_ERROR, "Terjadi kesalahan dalam proses");
	}
	
	public static String getMessage(String returnMessageCode) {
		return messageMap.get(returnMessageCode);
	}
}
