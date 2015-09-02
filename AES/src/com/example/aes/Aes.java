package com.example.aes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Aes extends Activity{
	
	byte[] incrept;
	byte[] decrpt;
	String KEY;
	
	
	
	Context ctx = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aes);
		Button Dec = (Button)findViewById(R.id.button2);
		Button Inc = (Button)findViewById(R.id.button1);
		Inc.setOnClickListener(btnInListner);
		Dec.setOnClickListener(btnDecListner);		
	}
	
	public OnClickListener btnInListner = new OnClickListener() {
		

		@Override
        public void onClick(View v) {
            CryptClass simpleCrypto = new CryptClass();
             System.out.println("Start Encrypting");
            try {
                // encrypt audio file send as second argument and corresponding key in first argument.
                  incrept = simpleCrypto.encrypt(KEY, getImageFile());

                  //Store encrypted file in SD card of your mobile with name vincent.mp3.
                FileOutputStream fos = new FileOutputStream(new File("/sdcard/Scenary.jpg"));
                   fos.write(incrept);
                   fos.close();

            } catch (Exception e) {  
                e.printStackTrace();
            }
        }
    };
    
    public OnClickListener btnDecListner = new OnClickListener() {

        public void onClick(View v) {
            CryptClass simpleCrypto = new CryptClass();

            try {

                // decrypt the file here first argument is key and second is encrypted file which we get from SD card.
                decrpt = simpleCrypto.decrypt(KEY, getImageFileFromSdCard());

                //play decrypted audio file.
                ///playMp3(decrpt);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
    };
    
    public byte[]   getImageFile() throws FileNotFoundException
    {
      byte[] Image_data = null;
      byte[] inarry = null;

       AssetManager am = ctx.getAssets();
        try {
            InputStream is = am.open("Scenary.jpg "); // use recorded file instead of getting file from assets folder.
            int length = is.available();
            Image_data = new byte[length];

            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while ((bytesRead = is.read(Image_data)) != -1)
            {
                output.write(Image_data, 0, bytesRead);
            }
          inarry = output.toByteArray();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    return inarry;
    }
    
    public byte[]   getImageFileFromSdCard() throws FileNotFoundException
    {

      byte[] inarry = null;

        try {
            //getting root path where encrypted file is stored.
            File sdcard  = Environment.getExternalStorageDirectory();
            File file = new File(sdcard,"Scenary.jpg"); //Creating file object

            //Convert file into array of bytes.
            FileInputStream fileInputStream=null;
            byte[] bFile = new byte[(int) file.length()]; 
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            inarry = bFile;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    return inarry;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.aes, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
public class CryptClass {
public  byte[] encrypt(String seed, byte[] cleartext) throws Exception {

    byte[] rawKey = getRawKey(seed.getBytes());
        byte[] result = encrypt(rawKey, cleartext);
      //  return toHex(result);
        return result;
}

public  byte[] decrypt(String seed, byte[] encrypted) throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] enc = encrypted;
        byte[] result = decrypt(rawKey, enc);

        return result;
}

//done
private  byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
    kgen.init(128, sr); 
    SecretKey skey = kgen.generateKey();
    byte[] raw = skey.getEncoded();
    return raw;
} 


private  byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
    byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
}

private  byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, skeySpec);
    byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
}
}

}
