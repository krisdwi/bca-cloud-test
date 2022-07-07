package bca.pricing.utility;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.springframework.stereotype.Component;

@Component
public class Encryptor {

	private SecretKey desKey;
	private final static String HEX_DIGITS = "0123456789ABCDEF";
	
	public Encryptor() {
		createCipher("jfghbal294mnblakil02qhx9".getBytes());
		// createCipher(ToBeLoadedProperties.appHashKey.getBytes());
	}

	public Encryptor(byte[] key) {
		createCipher(key);
	}
	
	public void createCipher(byte[] desKeyData) {
		try {
			if (!(desKeyData.length == 16 || desKeyData.length == 24)) {
				return;
			}
			byte[] key = new byte[24];
			if (desKeyData.length == 16) {
				for (int za = 0; za < 16; za++) {
					key[za] = desKeyData[za];
				}
				for (int za = 0; za < 8; za++) {
					key[za + 16] = desKeyData[za];
				}
			}
			if (desKeyData.length == 24) {
				for (int za = 0; za < 24; za++) {
					key[za] = desKeyData[za];
				}
			}

			DESedeKeySpec desKeySpec = new DESedeKeySpec(key);
			SecretKeyFactory keyFactory = null;
			keyFactory = SecretKeyFactory.getInstance("DESede");
			desKey = keyFactory.generateSecret(desKeySpec);
		} catch (NoSuchAlgorithmException ex1) {
		} catch (InvalidKeyException ex2) {
		} catch (InvalidKeySpecException ex3) {
		}
	}
	public byte[] encryptECB(byte[] cleartext) {
		byte[] ciphertext = null;
		try {
			Cipher desCipher;
			desCipher = Cipher.getInstance("DESede/ECB/NoPadding");
			desCipher.init(Cipher.ENCRYPT_MODE, desKey);
			ciphertext = desCipher.doFinal(cleartext);
		} catch (NoSuchAlgorithmException ex1) {
		} catch (InvalidKeyException ex2) {
		} catch (NoSuchPaddingException ex3) {
		} catch (BadPaddingException ex4) {
		} catch (IllegalBlockSizeException ex5) {
		} catch (IllegalStateException ex6) {
		}
		return ciphertext;
	}

	public byte[] decryptECB(byte[] ciphertext) {
		byte[] cleartext = null;
		try {
			Cipher desCipher;
			desCipher = Cipher.getInstance("DESede/ECB/NoPadding");
			desCipher.init(Cipher.DECRYPT_MODE, desKey);
			cleartext = desCipher.doFinal(ciphertext);
		} catch (NoSuchAlgorithmException ex1) {
		} catch (InvalidKeyException ex2) {
		} catch (NoSuchPaddingException ex3) {
		} catch (BadPaddingException ex4) {
		} catch (IllegalBlockSizeException ex5) {
		} catch (IllegalStateException ex6) {
		}
		return cleartext;
	}
	
	public String paddData(String orig) {
		StringBuffer buff = new StringBuffer();
		buff.append(orig);
		int paddSize = 0;
		if (orig.length() % 8 != 0) {
			paddSize = 8 - (orig.length() % 8);
		}
		for (int za = 0; za < paddSize; za++) {
			buff.append((char) 0x00);
		}
		return buff.toString();
	}
	
	public String encrypt(String data) {
		String retval = "";
		byte[] encrypted = encryptECB(paddData(data).getBytes());
		retval = toHexString(encrypted);
		return retval;
	}

	public String decrypt(String data) {
		String retval = "";
		byte[] decrypted = decryptECB(fromHexString(data));
		retval = cleanData(new String(decrypted));
		return retval;
	}
	
	public String toHexString(byte b[]) {
		if ((b == null) || (b.length == 0)) {
			return "";
		} else {
			return toHexString(b, 0, b.length);
		}
	}
	
	public String cleanData(String orig) {
		StringBuffer buff = new StringBuffer();
		char[] chars = orig.toCharArray();
		for (int za = 0; za < chars.length; za++) {
			char tmp = chars[za];
			if (!(tmp == 0x00)) {
				buff.append(tmp);
			}
		}
		return buff.toString();
	}
	
	public byte[] fromHexString(String s) {
		byte bytes[] = new byte[s.length() / 2];
		for (int i = 0; i < s.length() / 2; i++) {
			bytes[i] = (byte) (Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16));
		}
		return bytes;
	}
	
	/** Convert bytes to HEX string */
	private String toHexString(byte b[], int off, int len) {
		StringBuffer s = new StringBuffer();
		for (int i = off; i < off + len; i++) {
			s.append(HEX_DIGITS.charAt((b[i] & 0xff) >> 4));
			s.append(HEX_DIGITS.charAt(b[i] & 0xf));
		}
		return s.toString();
	}
}
