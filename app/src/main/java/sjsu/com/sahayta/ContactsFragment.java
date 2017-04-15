package sjsu.com.sahayta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.*;
import android.provider.ContactsContract.*;
import android.provider.ContactsContract.Contacts;
import android.provider.Settings;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.ViewGroup;
import 	android.view.LayoutInflater;

public class ContactsFragment extends ListFragment implements LoaderCallbacks<Cursor>,AdapterView.OnItemClickListener {

    private CursorAdapter mAdapter;
    ListView mContactsList;
    private CursorAdapter mCursorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        return inflater.inflate(R.layout.contacts_list_item, container, false);

    }

    // create adapter once

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.contacts_list_item,
                null,
                FROM, TO,
                0);
        // create adapter once

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(mCursorAdapter);
        getListView().setOnItemClickListener(this);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    // columns requested from the database
    private static final String[] PROJECTION = {
            Contacts._ID, // _ID is always required
            Contacts.DISPLAY_NAME_PRIMARY,

            // that's what we want to display
    };

    // and name should be displayed in the text1 textview in item layout
    private static final String[] FROM = { Contacts.DISPLAY_NAME_PRIMARY };
    private static final int[] TO = { android.R.id.text1 };

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        // load from the "Contacts table"
        Uri contentUri = Contacts.CONTENT_URI;

        // no sub-selection, no sort order, simply every row
        // projection says we want just the _id and the name column
        return new CursorLoader(getActivity(),
                contentUri,
                PROJECTION,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Once cursor is loaded, give it to adapter
       // mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // on reset take any old cursor away
        //mAdapter.swapCursor(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("Inside item clcik");

    }
}