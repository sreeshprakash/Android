package com.example.takefromcamera;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Encrypt extends Activity {
	static Bitmap bitmap;
	byte[] encryptedData;
       @Override
       protected void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.activity_main);
                           try {
                    Log.d("I'm inside encryption", "Inside Encryption");
                     bitmap  = this.getIntent().getExtras().getParcelable("name");
                    Log.d("Encrypt Activity image chosen is",bitmap.toString());
                               //ImageView view = (ImageView) findViewById(R.id.imageView1);
                               //view.setImageBitmap(bitmap);
                        	   
                        	   //LATEST MyV CODE STARTS HERE..!!!
                       ByteArrayOutputStream baos = new ByteArrayOutputStream();  
                      bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);    
                       byte[] b = baos.toByteArray();  
                        byte[] keyStart = "this is a key".getBytes();
                        KeyGenerator kgen = KeyGenerator.getInstance("AES");
                         SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
                         sr.setSeed(keyStart);
                         kgen.init(128, sr); // 192 and 256 bits may not be available
                    	   SecretKey skey = kgen.generateKey();
                        	   byte[] key = skey.getEncoded();    
                        	   // encrypt
                        	   
							try {
								encryptedData = encrypt(key,b);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        	   // decrypt
                        	   try {
								byte[] decryptedData = decrypt(key,encryptedData);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        	   
                        	   
                                  //encrypt();
                           } catch (NoSuchAlgorithmException e) {
                                  // TODO Auto-generated catch block
                                  e.printStackTrace();
                           }
       }    
       private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
           SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
           Cipher cipher = Cipher.getInstance("AES");
           cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
           byte[] encrypted = cipher.doFinal(clear);
           return encrypted;
       }

       private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
           SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
           Cipher cipher = Cipher.getInstance("AES");
           cipher.init(Cipher.DECRYPT_MODE, skeySpec);
           byte[] decrypted = cipher.doFinal(encrypted);
           return decrypted;
       }
} 
