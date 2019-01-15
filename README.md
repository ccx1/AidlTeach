### aidl例子

[Android-aidl初窥](https://blog.csdn.net/ci250454344/article/details/86492005)

核心主要是

    <service
        android:name=".MyService"
        android:enabled="true"
        android:exported="true">
        <intent-filter>
            <action android:name="com.ccx.aidlservice.MyService" />
        </intent-filter>
    </service>

其中的action过滤。
其次则是其中的aidl文件的创建


       package com.ccx.aidlservice;

       interface IMyAidlInterface {
           String getString();
       }

通过ServiceConnection来获取跨进程拿到的数据

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
