package isport.com.mediatest;

import android.content.Context;
import android.os.Process;

/**
 * Created by Administrator on 2017/8/17.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler instance = new CrashHandler();
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    private CrashHandler(){

    }

    public static CrashHandler getInstance(){
        return instance;
    }

    public void init(Context context){
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.mContext = context.getApplicationContext();

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            dumpExceptionToSDCard(e);
            uploadExceptionToServer(e);
        }catch (Exception e1) {
            e1.printStackTrace();
        }
        e.printStackTrace();
        ///if has default crach handler
        if(mDefaultExceptionHandler != null) {
            mDefaultExceptionHandler.uncaughtException(t, e);
        }else {
            Process.killProcess(Process.myPid());
        }
    }

    public void dumpExceptionToSDCard(Throwable ex) {

    }

    public void uploadExceptionToServer(Throwable ex) {

    }
}
