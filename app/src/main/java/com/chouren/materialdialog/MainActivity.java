package com.chouren.materialdialog;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;

import com.chouren.library.fragment.ListMaterialDialog;
import com.chouren.library.fragment.ProgressMaterialDialog;
import com.chouren.library.fragment.SimpleMaterialDialog;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button)findViewById(R.id.button1);
        Button b2 = (Button)findViewById(R.id.button2);
        Button b3 = (Button)findViewById(R.id.button3);
        Button b4 = (Button)findViewById(R.id.button4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleMaterialDialog.getCreater(MainActivity.this, getSupportFragmentManager())
                        .setTitle("Title")
                        .setMessage("dsjkflllllllllllllllllllllllllllllllllllllllllllllllllll")
                        .setPositiveText("Confirm")
                        .setNegativeText("Cancel")
                        .setCancelable(true)
                        .setCancelOnTouchOutside(true)
                        .show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressMaterialDialog.getCreater(MainActivity.this, getSupportFragmentManager())
                        .setTitle("Title")
                        .setMessage("sssssssssssssssssssssssssssssssssssssssssssssssssss")
                        .setCancelable(true)
                        .setCancelOnTouchOutside(true)
                        .show();
            }
        });

        final String[] items = new String[] {"item1", "item1", "item1", "item1", "item1", "item1"};
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListMaterialDialog.create(MainActivity.this, getSupportFragmentManager())
                        .setTitle("Title")
                        .setItems(items)
                        .setMode(AbsListView.CHOICE_MODE_SINGLE)
                        .setCheckedId(1)
                        .setPositiveText("Ok")
                        .setNegativeText("CANCEL")
                        .show();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListMaterialDialog.create(MainActivity.this, getSupportFragmentManager())
                        .setTitle("Title")
                        .setItems(items)
                        .setMode(AbsListView.CHOICE_MODE_MULTIPLE)
                        .setItemsChecked(new int[] {1, 2})
                        .setPositiveText("Ok")
                        .setNegativeText("CANCEL")
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
