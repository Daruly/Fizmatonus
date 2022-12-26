package company.hakatonus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.Instant;
import java.util.ArrayList;

import Forum.Post;
import Forum.PostDataProvider;

public class ForumPostAddActivity extends AppCompatActivity {
    private String[] options;
    private ArrayAdapter adapter;
    private AutoCompleteTextView autoCompleteTextView;
    private String category;
    private String author;
    private Button btnPost;
    private PostDataProvider postDataProvider;
    private EditText lblDesc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_post_add);
        postDataProvider =new PostDataProvider();
        options = getResources().getStringArray(R.array.dropdown_menu_options);
        adapter = new ArrayAdapter(this, R.layout.addpost_dropdown_item, options);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);
        category="Новости";
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                category = item;
                Toast.makeText(ForumPostAddActivity.this, category, Toast.LENGTH_SHORT).show();
            }
        });
        lblDesc = findViewById(R.id.edittext_post_desc);
        btnPost = findViewById(R.id.forum_addpost_btnAddPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc;
                if(lblDesc.getText().toString().isEmpty()){
                    lblDesc.setError("Empty input!");
                }else{
                    desc=lblDesc.getText().toString();
                    Post post = new Post();
                    post.setDesc(desc);
                    post.setCategory(category);
                    long unixtime = System.currentTimeMillis()/1000L;
                    post.setUnixTime(unixtime);
                    postDataProvider.addPost(post, ForumPostAddActivity.this);
                    Toast.makeText(ForumPostAddActivity.this, "Пост добавлен!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}