package devandroid.paulo.appvvspdvmobile.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import devandroid.paulo.appvvspdvmobile.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;

    NavigationView navigationView;

    Menu menu;
    MenuItem nav_preto;
    MenuItem nav_vermelho;
    MenuItem nav_azul;
    MenuItem nav_verde;


    TextView txtTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =   findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Action Button Clicado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        txtTitulo = findViewById(R.id.txtTitulo);

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.content_fragment, new ModeloVerdeFragment()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // TODO: opter ID para a opção selecionada no MENU DRAWER
        if (id == R.id.nav_preto) {

            menu = navigationView.getMenu();

            nav_preto = menu.findItem(R.id.nav_preto);
            nav_preto.setTitle("Fragmento Preto Ativado");

            nav_vermelho = menu.findItem(R.id.nav_vermelho);
            nav_vermelho.setTitle("API Tabela Fipe");

            nav_azul = menu.findItem(R.id.nav_azul);
            nav_azul.setTitle("API Busca CEP");

            nav_verde = menu.findItem(R.id.nav_verde);
            nav_verde.setTitle("Gaseta");

            //navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));
            navigationView.setItemTextAppearance(R.style.menu_item);

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ModeloPretoFragment()).commit();

        } else if (id == R.id.nav_vermelho) {

            menu = navigationView.getMenu();

            nav_preto = menu.findItem(R.id.nav_preto);
            nav_preto.setTitle("Fragmento Preto");

            nav_vermelho = menu.findItem(R.id.nav_vermelho);
            nav_vermelho.setTitle("API Tabela Fipe Ativado");

            nav_azul = menu.findItem(R.id.nav_azul);
            nav_azul.setTitle("API Busca CEP");

            nav_verde = menu.findItem(R.id.nav_verde);
            nav_verde.setTitle("Gaseta");

            // TODO: Mudar a cor de todos os itens do menu programaticamente
            //navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));
            navigationView.setItemTextAppearance(R.style.menu_item);

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ApiTableFipeFragment()).commit();

        } else if (id == R.id.nav_azul) {

            menu = navigationView.getMenu();

            nav_preto = menu.findItem(R.id.nav_preto);
            nav_preto.setTitle("Fragmento Preto");

            nav_vermelho = menu.findItem(R.id.nav_vermelho);
            nav_vermelho.setTitle("API Tabela Fipe");

            nav_azul = menu.findItem(R.id.nav_azul);
            nav_azul.setTitle("API Busca CEP Ativado");

            nav_verde = menu.findItem(R.id.nav_verde);
            nav_verde.setTitle("Gaseta");

            //navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));
            navigationView.setItemTextAppearance(R.style.menu_item);

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ApiCepFragment()).commit();

        }else if (id == R.id.nav_verde) {

            menu = navigationView.getMenu();

            nav_preto = menu.findItem(R.id.nav_preto);
            nav_preto.setTitle("Fragmento Preto");

            nav_vermelho = menu.findItem(R.id.nav_vermelho);
            nav_vermelho.setTitle("API Tabela Fipe");

            nav_azul = menu.findItem(R.id.nav_azul);
            nav_azul.setTitle("API Busca CEP");

            nav_verde = menu.findItem(R.id.nav_verde);
            nav_verde.setTitle("Gaseta Ativado");

            //navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));
            navigationView.setItemTextAppearance(R.style.menu_item);

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ModeloVerdeFragment()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
