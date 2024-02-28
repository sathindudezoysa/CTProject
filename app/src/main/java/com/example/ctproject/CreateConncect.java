package com.example.ctproject;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class CreateConncect extends Thread{
    private static Context getcontext;
    public static BluetoothSocket mmSocket;


    public CreateConncect(Context context, BluetoothAdapter bluetoothAdapter, String address) {
        getcontext = context;
        BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(address);
        BluetoothSocket tmp = null;
        if (ActivityCompat.checkSelfPermission(getcontext, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            return ;
        }
        UUID uuid = bluetoothDevice.getUuids()[0].getUuid();
        try {
            tmp = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) {
            Toast.makeText(getcontext, "Eroor:" + e, Toast.LENGTH_LONG).show();
        }

        mmSocket = tmp;
    }

    public static BluetoothSocket getMmSocket() {
        return mmSocket;
    }

    public void run() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (ActivityCompat.checkSelfPermission(getcontext, android.Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        bluetoothAdapter.cancelDiscovery();
        try{
            mmSocket.connect();
            Log.e("Status", "Device Connected");
        }catch (IOException ce){
            try {
                mmSocket.close();
                Log.e("Status", "Cannot connect to device");

            }catch (IOException e){
                Log.e(TAG, "Could not close the client socket", e);
            }
        }


    }
}
