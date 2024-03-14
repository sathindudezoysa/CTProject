package com.example.ctproject;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class ConnectedThread extends Thread {
    private  final BluetoothSocket mmSocket;
    private final InputStream mmInStream;

    private Context context;

    public ConnectedThread(BluetoothSocket socket,  Context context) {
        mmSocket = socket;
        this.context = context;
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

//                    if(readMessage.equals("detected"))
//                    {
//                        Log.e("Arduino Message", "found");
//                        Thread.sleep(1000);
//                        Toast.makeText(context, "Driver is Drowsy", Toast.LENGTH_LONG).show();
//                        Thread.sleep(1000);
//                    }
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

