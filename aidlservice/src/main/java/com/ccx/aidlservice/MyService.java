package com.ccx.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    public class MyBind extends IMyAidlInterface.Stub {

        @Override
        public String getString() throws RemoteException {
            return "aadskjfi";
        }
    }
}
