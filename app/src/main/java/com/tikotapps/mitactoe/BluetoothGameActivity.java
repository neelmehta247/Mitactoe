package com.tikotapps.mitactoe;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

import io.palaima.smoothbluetooth.Device;
import io.palaima.smoothbluetooth.SmoothBluetooth;

/**
 * Created by neel on 23/01/16 at 3:41 PM.
 */
public class BluetoothGameActivity extends AppCompatActivity implements View.OnClickListener {

    private SmoothBluetooth mSmoothBluetooth;
    private final int REQUEST_ENABLE_BT = 50;
    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bluetooth_activity);

        findViewById(R.id.createGameButton).setOnClickListener(this);

        mSmoothBluetooth = new SmoothBluetooth(this, SmoothBluetooth.ConnectionTo.ANDROID_DEVICE, SmoothBluetooth.Connection.SECURE, mSmoothBluetoothListener);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createGameButton:
                if (!mSmoothBluetooth.isConnected())
                    mSmoothBluetooth.tryConnection();
                break;
        }
    }

    private SmoothBluetooth.Listener mSmoothBluetoothListener = new SmoothBluetooth.Listener() {
        @Override
        public void onBluetoothNotSupported() {
            new AlertDialog.Builder(BluetoothGameActivity.this)
                    .setTitle("Bluetooth not supported")
                    .setMessage("This device is not bluetooth compatible, and hence this feature is not available.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(BluetoothGameActivity.this, LaunchActivity.class));
                        }
                    })
                    .setCancelable(false).show();
        }

        @Override
        public void onBluetoothNotEnabled() {
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_ENABLE_BT);
        }

        @Override
        public void onConnecting(Device device) {

        }

        @Override
        public void onConnected(Device device) {

        }

        @Override
        public void onDisconnected() {

        }

        @Override
        public void onConnectionFailed(Device device) {
            Toast.makeText(BluetoothGameActivity.this, "Connection failed", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onDiscoveryStarted() {

        }

        @Override
        public void onDiscoveryFinished() {

        }

        @Override
        public void onNoDevicesFound() {
            Toast.makeText(BluetoothGameActivity.this, "No devices found", Toast.LENGTH_LONG).show();

            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            } else {
                mSmoothBluetooth.doDiscovery();
                dialog = new ProgressDialog(BluetoothGameActivity.this);
                dialog.setTitle("Scanning");
                dialog.setMessage("Scanning for more devices");
                dialog.setCancelable(false);
                dialog.show();
            }
        }

        @Override
        public void onDevicesFound(final List<Device> list, final SmoothBluetooth.ConnectionCallback connectionCallback) {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(BluetoothGameActivity.this,
                    android.R.layout.select_dialog_singlechoice);
            for (int i = 0; i < list.size(); i++) {
                arrayAdapter.add(list.get(i).getName());
            }

            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }

            new AlertDialog.Builder(BluetoothGameActivity.this)
                    .setTitle("Select a device")
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("Scan for different devices", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mSmoothBluetooth.doDiscovery();
                            dialog = new ProgressDialog(BluetoothGameActivity.this);
                            dialog.setTitle("Scanning");
                            dialog.setMessage("Scanning for more devices");
                            dialog.setCancelable(false);
                            dialog.show();
                        }
                    })
                    .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            connectionCallback.connectTo(list.get(which));
                        }
                    })
                    .setCancelable(false).show();
        }

        @Override
        public void onDataReceived(int i) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                mSmoothBluetooth.doDiscovery();
            } else {
                startActivity(new Intent(this, LaunchActivity.class));
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSmoothBluetooth.stop();
    }
}
