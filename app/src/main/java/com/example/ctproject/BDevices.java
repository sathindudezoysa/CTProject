package com.example.ctproject;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (getContext(), android.R.layout.simple_list_item_1, ListElementsArrayList);
        listview.setAdapter(adapter);
//

        if (ContextCompat.checkSelfPermission( getContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED )
        {
        }
        Toast.makeText(getContext(), "Done", Toast.LENGTH_LONG).show();

        Set<BluetoothDevice> pariedDevices = bluetoothAdapter.getBondedDevices();
        if (pariedDevices.size() > 0){
            for (BluetoothDevice device: pariedDevices){
                String deviceName = device.getName();
                String deviceHarwareAddress = device.getAddress();
                ListElementsArrayList.add(deviceName);
            }
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });

        return view;
    }
}