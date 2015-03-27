package net.infobosccoma.multimedia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import net.infobosccoma.multimedia.Adapter.CategorylistViewAdapter;
import net.infobosccoma.multimedia.Model.Category;
import net.infobosccoma.multimedia.Model.User;


public class DrawerMenuActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private static Images images;
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_menu);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        //Bundle bnd = this.getIntent().getSerializableExtra("");
        User info = (User) this.getIntent().getSerializableExtra("user");//bnd.getSerializable("user");
        //Log.i("User info", info.getName());


        EditText nomEditText = (EditText) findViewById(R.id.editText_NomUser);
        EditText cognomEditText = (EditText) findViewById(R.id.editText2_CognomUser);
        ImageView iv = (ImageView) findViewById(R.id.imageView_FotoUsuari);
        Bitmap prev = info.makeBitmap();

        //Bitmap bitmap = Bitmap.createScaledBitmap(prev,iv.getWidth(),iv.getHeight(),true);
        iv.setImageBitmap(prev);

        nomEditText.setText(info.getName());
        cognomEditText.setText(info.getSurName());

        images = new Images();
        images.download();
        /*
        webview = (WebView) findViewById(R.id.webView);
        //webview.getSettings().setJavaScriptEnabled(true);
        //webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


        webview.loadUrl("http://www.katyperry.com/");
        */

        //mNavigationDrawerFragment.setArguments(savedInstanceState);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        mTitle = getString(R.string.title_section);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.drawer_menu, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Log.d("Missatge:","Gravador de so");


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private ListView categoryList = null;
        private Category [] dades = null;


        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_drawer_menu, container, false);

            dades = new Category[3];
            dades[0] = new Category("Imatges",R.drawable.flower);
            dades[1] = new Category("Videos",R.drawable.video_image_section);

            final Activity activity = getActivity();
            ListView categoryList = (ListView) rootView.findViewById(R.id.listViewSections);

            final CategorylistViewAdapter adapter = new CategorylistViewAdapter(activity,dades);
            categoryList.setAdapter(adapter);

            categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Intent imageIntent = new Intent(parent.getContext(),image_gallery.class);
                    imageIntent.putStringArrayListExtra("images",images.getList());
                    startActivity(imageIntent);
                    //Intent homo = new Intent(parent.getContext(),image_gallery.class);

                    //startActivity(homo);
                    Toast.makeText(getActivity(),"Aixo es una secció",Toast.LENGTH_LONG);
                }
            });

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((DrawerMenuActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    private Bitmap makeBitmap(byte[] byteArray){

        Bitmap bmp = null;
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        return bmp;
    }

}
