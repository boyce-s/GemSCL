package boycese.cs.simmons.edu.gemscl;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.ContentValues;
import android.content.Context;


public class AddGem extends ActionBarActivity {

    GemDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gem);

        mDbHelper = new GemDbHelper(this);
        mDbHelper.open();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_gem, menu);
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

    public void insertGem(View view) {
        EditText name = (EditText) findViewById(R.id.gemNameEntry);
        String num1 = name.getText().toString();
        EditText color = (EditText) findViewById(R.id.gemColorEntry);
        String num2 = color.getText().toString();
        EditText weight = (EditText) findViewById(R.id.gemWeightEntry);
        String num3 = weight.getText().toString();
        mDbHelper.createGem(num1, num2, num3);
        this.finish();
    }
}
