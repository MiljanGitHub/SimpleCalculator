package com.hfad.simplecalculator;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.content.res.Configuration;

public class MainActivity extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;
    private String[] calculators;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int currentPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculators = getResources().getStringArray(R.array.calculators);

        //Get hold of entire View Drawer Layout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Get hold of part of Drawer Layout, that is ''drawer'', ListView part
        drawerList = (ListView) findViewById(R.id.drawer);

        //Initialize the ListView's adapter and fill ListView (drawer)
        drawerList.setAdapter(createNewAdapter());

        //set Item Listener on drawer, reacting on clicks in List View part of Drawer Layout
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        //setting title of appropriate action bar title, depending what has happened with the activity
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("position");
            setActionBarTitle(currentPosition);
        } else {
            selectItem(0);
        }

        //Create the ActionBarDrawerToggle
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open_drawer, R.string.close_drawer) {
            //Called when a drawer has settled in a completely closed state
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
            //Called when a drawer has settled in a completely open state.
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        FragmentManager fragMan = getFragmentManager();
                        Fragment fragment = fragMan.findFragmentByTag("visible_fragment");
                        if (fragment instanceof TopFragment) {
                            currentPosition = 0;
                        }
                        if (fragment instanceof StandardFragment) {
                            currentPosition = 1;
                        }
                        if (fragment instanceof ScientificFragment) {
                            currentPosition = 2;
                        }
                        if (fragment instanceof ProgrammerFragment) {
                            currentPosition = 3;
                        }
                        if (fragment instanceof ConverterFragment) {
                            currentPosition = 4;
                        }
                        setActionBarTitle(currentPosition);
                        drawerList.setItemChecked(currentPosition, true);
                    }
                }
        );


    }

    private ArrayAdapter createNewAdapter(){
        return new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, calculators);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            //Code to run when the item gets clicked
            selectItem(position);
        }
    };


    private void selectItem(int position){
        Fragment fragment;
        currentPosition = position;
        switch (position){

            case 1:
                fragment = new StandardFragment();
                break;
            case 2:
                fragment = new ScientificFragment();
                break;
            case 3:
                fragment = new ProgrammerFragment();
                break;
            case 4:
                fragment = new ConverterFragment();
                break;
            default:
                fragment = new TopFragment();

        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, "visible_fragment"); //sad dodatoo
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        setActionBarTitle(position);
        //setActionBarTitle(position);
        //DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(drawerList);
    }

    private void setActionBarTitle(int position) {
        String title;
        if (position == 0 ){
            title = getResources().getString(R.string.app_name);
        } else {
            title = calculators[position];
        }
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        //Ovde mogu dodati poslednji uspešan izraz i njegovog rezultat
        setIntent("samo text saljem preko implicitnog intenta...");

        return super.onCreateOptionsMenu(menu);
    }

    private void setIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    // If the drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        menu.findItem(R.id.action_share).setVisible(!drawerOpen);
        FragmentManager fragMan = getFragmentManager();
        Fragment fragment = fragMan.findFragmentByTag("visible_fragment");
        if (fragment instanceof TopFragment) {
            return false;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    };

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", currentPosition);
    }
}
