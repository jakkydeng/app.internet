package nf.framework.core.util.io;

import nf.framework.core.exception.LogUtil;
import nf.framework.core.exception.NFRuntimeException;
import android.os.Environment;
import android.os.StatFs;

/**
 * Sd卡使用 
 * @author win7
 *
 */
public class SdcardUtil {

	/**
	 * 返回sd卡的路径
	 * @return
	 */
	 public String getSDCardPath() {
	       return Environment.getExternalStorageDirectory().getPath();
	    }
	/**
	 * 检测Sd卡是否存在
	 * @return
	 */
	 public  boolean checkSDCard() {
	        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
	            return true;
	        else
	            return false;
	    }
 /**
  * 检查sd卡是否可写
  * @return
  */
   public  boolean isSdCardWrittenable() {

        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
   /**
    * 获取sd卡的可用存储大小 剩下的空间
    * @return
    */
  public  long getAvailableStorage() {

      String storageDirectory = null;
      storageDirectory = Environment.getExternalStorageDirectory().toString();
      try {
          StatFs stat = new StatFs(storageDirectory);
          long avaliableSize = ((long) stat.getAvailableBlocks() * (long) stat.getBlockSize());
          return avaliableSize;
      } catch (NFRuntimeException ex) {
    	  LogUtil.writeExceptionLog(ex);
          return 0;
      }
  }
  /**
   * 判断当前的sd空间是否可保存该文件
   * @param currentFileSize
   * @return
   */
  public boolean isAvailableStorage(long currentFileSize){
	  /// 检测Sd卡是否存在
	  if(!checkSDCard()){
			throw new NFRuntimeException("Sd卡不存在");
	  }
	  //检查sd卡是否可读
	  if(!isSdCardWrittenable()){
		  throw new NFRuntimeException("Sd卡不能执行写入操作");
	  }
	 long avaliableSize= getAvailableStorage();
	if(Float.compare(avaliableSize, currentFileSize)==1){//avaliableSize>currentFileSize
		return true;
	}
	  return false;
  }
}
