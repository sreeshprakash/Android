package com.example.cloudpress;

/*package com.example.cloudpress;


 

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.util.Iterator;

 

import javax.imageio.IIOImage;

import javax.imageio.ImageIO;

import javax.imageio.ImageWriteParam;

import javax.imageio.ImageWriter;

import javax.imageio.stream.ImageOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

 

public class CompressJPEGFile {

 

    public static void main(String Source, String Target) throws IOException {

 

        File imageFile = new File(Source);

        File compressedImageFile = new File(Target);

 

        InputStream is = new FileInputStream(imageFile);

        OutputStream os = new FileOutputStream(compressedImageFile);

 

        float quality = 0.1f;

 

        // create a BufferedImage as the result of decoding the supplied InputStream

        Bitmap image = BitmapFactory.decodeStream(is);

 

        // get all image writers for JPG format

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");

 

       if (!writers.hasNext()) {
		extracted();
	}

 

        ImageWriter writer = (ImageWriter) writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);

        writer.setOutput(ios);

 

        ImageWriteParam param = writer.getDefaultWriteParam();

 

        // compress to a given quality

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

        param.setCompressionQuality(quality);



        // appends a complete image stream containing a single image and

        //associated stream and image metadata and thumbnails to the output

        writer.write(null, new IIOImage(image, null, null), param);

 

        // close all streams

        is.close();

        os.close();

        ios.close();

        writer.dispose();

 

    }

	private static void extracted() {
		throw new IllegalStateException("No writers found");
	}

 

}
*/

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.util.Iterator;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Debug;

public class CompressJPEGFile {

protected static void main(final Bitmap pBitmap, final String pFilePath) throws FileNotFoundException {
	FileOutputStream out = null;
	try {
		out = new FileOutputStream(pFilePath);
		pBitmap.compress(CompressFormat.JPEG, 100, out);
	} catch (final FileNotFoundException f) {
		//StreamUtils.flushCloseStream(out);
		//Debug.f("Error saving file to: " + pFilePath, f);
		throw f;
	}
}
}