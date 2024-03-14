package com.example.ctproject;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;

import java.util.*;

public class BDevices extends Fragment {

    private BluetoothAdapter bluetoothAdapter;
    ListView listview;
    String[] ListElements = new String[]{
            "Device List"
    };
    public BDevices() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_b_devices, container, false);

        listview = view.findViewById(R.id.list_item);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        final List<String> ListElementsArrayList = new ArrayList<>(Arrays.asList(ListElements));
        final Dictionary<String, String> dict = new Hashtable<String, String>();

        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (getContext(), android.R.layout.simple_list_item_1, ListElementsArrayList);
        listview.setAdapter(adapter);
//

        if (ContextCompat.checkSelfPermission( getContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED )
        {
        }

        Set<BluetoothDevice> pariedDevices = bluetoothAdapter.getBondedDevices();
        if (pariedDevices.size() > 0){
            for (BluetoothDevice device: pariedDevices){
                String deviceName = device.getName();
                dict.put(deviceName, device.getAddress());
                ListElementsArrayList.add(deviceName);
            }
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String device_address= dict.get(adapterView.getItemAtPosition(position));
                Toast.makeText(getContext(), "Connecting to Bluetooth device", Toast.LENGTH_LONG).show();
                CreateConncect createConnect = new CreateConncect(getContext(),bluetoothAdapter,device_address);
                createConnect.start();


                try {
                    createConnect.join();
                    BluetoothSocket mmsocket = CreateConncect.getMmSocket();

                    if (mmsocket.isConnected())
                    {
                        ConnectedThread connectedThread = new ConnectedThread(mmsocket, getContext());
                        connectedThread.start();
                        Toast.makeText(getContext(), "Device Connected", Toast.LENGTH_LONG);

                        Navigation.findNavController(view).navigate(R.id.action_BDevices_to_home2);
                    }else {
                        Toast.makeText(getContext(), "Can not connect to the device", Toast.LENGTH_LONG);
                    }



                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        return view;
    }
}