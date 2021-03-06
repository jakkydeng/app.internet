package nf.framework.act.pic;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import nf.framework.core.util.io.ImageUtil;
import nf.framework.core.util.io.SdcardUtil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;


public class ImageBrowseUtil {
	public static final String ImageNameSuffix=".cach";
	   /**
     * 获取bitmap 先本地后网络
     * @param imageStorePath
     * @param imageUrl
     * @param netLimit true 为限制
     * @return
     * @throws Exception 
     */
   public static  Bitmap getBitmapFromLocalFile(Context mcontext,String imageUrl) throws Exception{
	   Bitmap bitmap=null;
	   String  imageName =  getTempBitmapFileName(mcontext,imageUrl);
       boolean isPicExist=isPicExist(mcontext, imageName);//判断是否存在该图片
	   if(isPicExist){//存在
		   String imageFilePath=getTempBitmapFilePath(mcontext, imageName);
		   DisplayMetrics dm = new DisplayMetrics();
		   ((Activity)mcontext).getWindowManager().getDefaultDisplay().getMetrics(dm);
		   bitmap =ImageUtil.decodeBitmapFromFile(imageFilePath,dm.widthPixels/2, dm.heightPixels/2);
	   }
	return bitmap;
	   
   }
   /** 
	 * @param packageName
	 * @param imageName
	 * @return
	 * @param niufei
	 * @param 2014-3-31 下午12:19:06
	 * @return boolean
	 * @throws 
	 */
	private static boolean isPicExist(Context mcontext,String imageName) {
		// TODO Auto-generated method stub
		File file = new File(getTempBitmapFilePath(mcontext, imageName));
		return file.exists();
	}
	public static String getTempBitmapFilePath(Context mcontext,String fileName){
		return new SdcardUtil().getSDCardPath()+ File.separator + mcontext.getPackageName() +File.separator + fileName;
	}
   /**
    * 本地大图保存路径
    * @param imageUrl
    * @return
    */
   public static String getTempBitmapFileName(Context mcontext,String imageUrl){
	   return imageUrl.substring(imageUrl.lastIndexOf(File.separator)+1,imageUrl.length())+ImageNameSuffix;//获取图片名称
   }
   
   /** *//**
    * 把字节数组保存为图片文件
    * @Author Sean.guo
    * @EditTime 2007-8-13 上午11:45:56
    */
   public static File getFileFromBytes(byte[] b,Context mcontext,String imageUrl) {
       BufferedOutputStream stream = null;
       File file = null;
       try {
    	  		String outputFilePath=getTempBitmapFilePath(mcontext, getTempBitmapFileName(mcontext, imageUrl));
    	  		if(outputFilePath==null){
    	  			return null;
    	  		}
           file = new File(outputFilePath);
           FileOutputStream fstream = new FileOutputStream(file);
           stream = new BufferedOutputStream(fstream);
           stream.write(b);
       } catch (Exception e) {
           
    	   Log.e("IamgeBrowseUtil","------"+e.getStackTrace().toString());
       } finally {
           if (stream != null) {
               try {
                   stream.close();
               } catch (IOException e1) {
            	   Log.e("IamgeBrowseUtil","------"+e1.getStackTrace().toString());
               }
           }
       }
       return file;
   }
}
