package com.example.ctproject;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class ConnectedThread extends Thread {
    private  final BluetoothSocket mmSocket;
    private final InputStream mmInStream;

    public ConnectedThread(BluetoothSocket socket) {
        mmSocket = socket;
        InputStream tmpIn = null;
        try{
            tmpIn = socket.getInputStream();
        }catch (IOException e){}

        mmInStream = tmpIn;
    }

    public void run() {

        byte[] buffer = new byte[1024];
        int bytes = 0;
        while (true){
            try{
                buffer[bytes] = (byte) mmInStream.read();
                String readMessage;
                if (buffer[bytes] == '\n'){
                    readMessage = new String(buffer, 0, bytes);
                    Log.e("Arduino Message", readMessage);
                    bytes = 0;
                }else{
                    bytes ++;
                }

            }catch (IOException e){
                Log.e("error",e.getMessage());

            }


        }
    }

}

