package util;

import java.util.*;
import java.util.regex.*;
import java.security.*;

public class Tools 
{
  public static String toStr(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return String.format("%02d-%02d-%4d", cal.get(Calendar.DATE), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
  }

  public static String toStr(Calendar cal)
  {
    return String.format("%02d-%02d-%4d", cal.get(Calendar.DATE), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
  }

  public static String encrypt(char[] passwd) throws Exception
  {
    byte[] bytes = new String(passwd).getBytes("UTF-8");  
    MessageDigest alg = MessageDigest.getInstance("SHA-512");
    alg.reset();
    alg.update(bytes);
    return toHex(alg.digest());
  }
  
  public static boolean emailOk(String mail) 
  {
    String pattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(mail);
    return m.matches();
  }
  
  public static boolean isbnOk(String isbn)
  {
    if (isbn.length() == 13) return isbn10Ok(isbn);
    if (isbn.length() == 17) return isbn13Ok(isbn);
    return false;
  }
  
  public static boolean isbn10Ok(String isbn)
  {
    String[] tokens = split(isbn);
    if (tokens.length != 4 || tokens[3].length() != 1) return false;
    isbn = tokens[0] + tokens[1] + tokens[2];
    tokens[3] = tokens[3].toUpperCase();
    if (!numberOk(isbn) || isbn.length() != 9 || !checkOk(tokens[3])) return false;
    int sum = 0;
    for (int i = isbn.length() - 1, weight = 2; i >= 0; --i, ++weight) sum += (isbn.charAt(i) - '0') * weight;
    int rest = sum % 11;
    char c = rest == 0 ? '0' : rest == 1 ? 'X' : (char)(11 - rest + '0');
    return tokens[3].charAt(0) == c;
  }

  // Validerer ISBN13
  public static boolean isbn13Ok(String isbn)
  {
    String[] tokens = split(isbn);
    if (tokens.length != 5 || tokens[0].length() != 3 || tokens[4].length() != 1) return false;
    isbn = tokens[0] + tokens[1] + tokens[2] + tokens[3] + tokens[4];
    if (!numberOk(isbn) || isbn.length() != 13) return false;
    int sum = 0;
    for (int i = 0, weight = 1; i < isbn.length() - 1; ++i, weight = weight == 1 ? 3 : 1) sum += (isbn.charAt(i) - '0') * weight;
    int rest = sum % 10;
    char c = rest == 0 ? '0' : (char)(10 - rest + '0');
    return tokens[4].charAt(0) == c;
  }

  private static String[] split(String isbn)
  {
    StringTokenizer tk = new StringTokenizer(isbn, "-");
    String[] arr = new String[tk.countTokens()];
    for (int i = 0; i < arr.length; ++i) arr[i] = tk.nextToken();
    return arr;
  }

  private static boolean checkOk(String check)
  {
    if (check.length() != 1) return false;
    char c = check.charAt(0);
    return (c >= '0' && c <= '9') || c == 'X';
  }

  private static boolean numberOk(String elem)
  {
    for (int i = 0; i < elem.length(); ++i) if (elem.charAt(i) < '0' || elem.charAt(i) > '9') return false;
    return true;
  }
  
  private static String toHex (byte hash[]) 
  {
    StringBuilder buffer = new StringBuilder(hash.length * 2);
    for (int i = 0; i < hash.length; ++i) 
    {
      if (((int) hash[i] & 0xff) < 0x10) buffer.append("0");
      buffer.append(Long.toString((int) hash[i] & 0xff, 16));
    }
    return buffer.toString();
  }
}
