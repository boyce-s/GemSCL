package boycese.cs.simmons.edu.gemscl;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class List extends ListActivity {


    private GemDbHelper mDbHelper;
    private ListAdapter gems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mDbHelper = new GemDbHelper(this);
        mDbHelper.open();
        fillData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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

    private void fillData() {
        // Get all of the notes from the database and create the item list
        Cursor c = mDbHelper.fetchAllGems();
        startManagingCursor(c); // deprecated

        String[] from = new String[]{GemDbHelper.KEY_NAME};
        int[] to = new int[]{R.id.text1};

        // Now create an array adapter and set it to display using our row
        gems = // one command that loops through everything in the cursor and does whatever you want it to as defined in
                new SimpleCursorAdapter(this, R.layout.gems_rows, c, from, to, 0);//first is view (basically always "this"), second is name of XML file we want the layout to be, then the cursor itself, then what you're taking, then the XML element you're putting it into to display the info
        setListAdapter(gems);
    }


    public void onListItemClick(ListView parent, View view, int position, long id) {
        Intent intent = new Intent(this, ListGemDetail.class);
        Cursor cursor = (Cursor) gems.getItem(position);
        intent.putExtra("GEM_ID", cursor.getInt(cursor.getColumnIndex("_id")));
        startActivity(intent);
    }

    public void returnToPrev(View view) {
        this.finish();
    }
}
