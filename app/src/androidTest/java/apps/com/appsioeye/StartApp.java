package apps.com.appsioeye;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.logging.Logger;

/**
 * author:jianbin.zhong
 * time:2017/8/22  20:02
 * description:This is StartApp,to test the env is ok
 */

@RunWith(AndroidJUnit4.class)
public class StartApp {
    private Logger logger= Logger.getLogger(StartApp.class.getName());
    private UiDevice myDevice = null;
    private Context myContext = null;
    private static String APP = "cn.sioeye.sioeyeapp";
    private static String ID_ME = "cn.sioeye.sioeyeapp:id/table_me";
    private static String ID_FOLLOWERS = "cn.sioeye.sioeyeapp:id/user_followers_count";


    @Before
    public void setUp() throws RemoteException {
        logger.info("init app");
        myDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        myContext = InstrumentationRegistry.getContext();
        if (!myDevice.isScreenOn()){
            myDevice.wakeUp();
        }
        myDevice.pressHome();
    }

    @Test
    public void testClickMe() throws UiObjectNotFoundException{
        Intent myIntent = myContext.getPackageManager().getLaunchIntentForPackage(APP);//启动app
        myContext.startActivity(myIntent);
        myDevice.waitForWindowUpdate(APP, 5 * 2000);
        UiObject me = myDevice.findObject(new UiSelector().resourceId(ID_ME));
        me.click();
        if (myDevice.findObject(new UiSelector().resourceId(ID_FOLLOWERS)).exists()){
            Assert.assertTrue(true);
            logger.info("test pass");
        }
        else {
            Assert.assertTrue(false);
            logger.info("test fail");
        }
    }
    @After
    public void tearDown(){
        myDevice.pressHome();//回到主界面
    }




















}
