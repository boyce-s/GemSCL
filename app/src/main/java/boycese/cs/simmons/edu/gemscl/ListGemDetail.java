package boycese.cs.simmons.edu.gemscl;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.database.Cursor;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.app.ListActivity;
import android.widget.TextView;


public class ListGemDetail extends ActionBarActivity {

    private GemDbHelper mDbHelper;
    private int gemID;
    private ListAdapter gems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gem_detail);

        mDbHelper = new GemDbHelper(this);
        mDbHelper.open();

        gemID = getIntent().getIntExtra("GEM_ID", 0);
        System.out.println(gemID);
        // SELECT _id gemName, gemColor, gemWeight FROM gems WHERE _id = gemID

        String sqlCommand = "SELECT _id, gemName, gemColor, gemWeight FROM gems WHERE _id = ?";

        Cursor c = mDbHelper.fetchGem(sqlCommand, gemID);

        c.moveToFirst();
        TextView gemNameField = (TextView) findViewById(R.id.t_gem_name);
        gemNameField.setText(c.getString(c.getColumnIndex("gemName")));
        TextView gemColorField = (TextView) findViewById(R.id.t_gem_color);
        gemColorField.setText(c.getString(c.getColumnIndex("gemColor")));
        TextView gemWeightField = (TextView) findViewById(R.id.t_gem_weight);
        gemWeightField.setText(c.getString(c.getColumnIndex("gemWeight")));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_gem_detail, menu);
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
