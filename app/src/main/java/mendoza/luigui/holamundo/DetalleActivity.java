package mendoza.luigui.holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        String user = getIntent().getExtras().getString("CurrentPostUser");
        String title = getIntent().getExtras().getString("CurrentPostTitle");
        String content = getIntent().getExtras().getString("CurrentPostContent");

        EditText etUser = ((EditText) findViewById(R.id.editText));
        etUser.setText(user.toString());

        EditText etTitle = ((EditText) findViewById(R.id.editText2));
        etTitle.setText(title.toString());

        EditText etContent = ((EditText) findViewById(R.id.editText3));
        etContent.setText(content.toString());


    }
}
