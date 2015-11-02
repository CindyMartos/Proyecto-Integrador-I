package mendoza.luigui.holamundo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapters.PostAdapter;
import database.DatabaseSqlLiteHelper;
import models.Post;

public class ListPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_post);
        setListContent();

    }

    private void setListContent() {

        PostAdapter adapter = new PostAdapter(this, R.layout.simple_list_item_post, GetPosts());
        ListView list = (ListView)findViewById(R.id.listPost);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetalleActivity.class);

                String user = ((TextView)view.findViewById(R.id.txtUsuario)).getText().toString();
                String title = ((TextView)view.findViewById(R.id.txtTitle)).getText().toString();
                String content = ((TextView)view.findViewById(R.id.txtContent)).getText().toString();

                intent.putExtra("CurrentPostUser", user);
                intent.putExtra("CurrentPostTitle", title);
                intent.putExtra("CurrentPostContent", content);
                startActivity(intent);
            }
        });

    }

    private List<Post> GetPosts() {

        List<Post> posts = new ArrayList<>();

        DatabaseSqlLiteHelper helper = new DatabaseSqlLiteHelper(this, "android", null, 1);

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * from posts;", null);

        while(c.moveToNext())
        {
            Post post = new Post();
            post.id = c.getInt(0);
            post.user = c.getString(1);
            post.title = c.getString(2);
            post.content = c.getString(3);

            posts.add(post);
        }

        return posts;

    }

}
