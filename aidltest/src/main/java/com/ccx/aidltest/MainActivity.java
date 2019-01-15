package com.ccx.aidltest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ccx.aidlservice.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                String string = mIMyAidlInterface.getString();
                System.out.println(string);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private IMyAidlInterface  mIMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        Intent intent = new Intent("com.ccx.aidlservice.MyService");
        intent.setPackage("com.ccx.aidlservice");
        bindService(intent, conn, BIND_AUTO_CREATE);
    /*    60                 Intent intent = new Intent();
        61                 intent.setAction("com.example.service.MyService");
        62                 //从 Android 5.0开始 隐式Intent绑定服务的方式已不能使用,所以这里需要设置Service所在服务端的包名
        63                 intent.setPackage("com.example.service");
        64                 bindService(intent, connection, BIND_AUTO_CREATE);*/
    }
}
