package gamepage.page.Hash;

import java.security.MessageDigest;

public class Hash {

  public Hash() {
  }
  public String getHash(String text) throws Exception{
    String hash ="";
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] hashBytes= md.digest(text.getBytes());
    hash = bytesToHex(hashBytes);
    System.out.println("New password:" + text);
    System.out.println("Password hased: " + hashBytes.toString());
    System.out.println("Hex password:" + hash);
    return hash;
  }
  private String bytesToHex(byte[] bytes_){
    String hex = "";
    for(Byte byte_ : bytes_){
      hex += Integer.toHexString(byte_.intValue());
    }
    return hex;
  }
}