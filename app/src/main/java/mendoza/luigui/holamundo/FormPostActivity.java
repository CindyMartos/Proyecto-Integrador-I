package mendoza.luigui.holamundo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import database.DatabaseSqlLiteHelper;
import models.Post;

public class FormPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_post);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // guardar en base de datos
                DatabaseSqlLiteHelper helper = new DatabaseSqlLiteHelper(getApplicationContext(), "android", null, 1);


                SQLiteDatabase database = helper.getWritableDatabase();

                ContentValues values = new ContentValues();


                Post post = new Post();
                post.user = ((EditText)findViewById(R.id.txtUsuario)).getText().toString();
                post.title = ((EditText)findViewById(R.id.txtTitle)).getText().toString();
                post.content = ((EditText) findViewById(R.id.txtContent)).getText().toString();

                values.put("user", post.user);
                values.put("title", post.title);
                values.put("content", post.content);

                database.insert("posts", null, values);

                Toast.makeText(getApplicationContext(), "Se guardo correctamente", Toast.LENGTH_SHORT).show();

                database.close();

            }
        });
    }

}
