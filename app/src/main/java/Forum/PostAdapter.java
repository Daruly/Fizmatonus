package Forum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

import company.hakatonus.R;

public class  PostAdapter extends ArrayAdapter<Post> {
    private Context context;
    private int resource;
    private ArrayList<Post> postArrayList;
    private TextView postTitle;
    private TextView postDesc;
    private TextView author;
    private TextView likeCounter;
    private ImageView image;
    public PostAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Post> objects) {
        super(context,resource,objects);
        this.context=context;
        this.resource=resource;
        this.postArrayList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(this.context).inflate(resource, null);
        //fill in the card for each post
        postTitle = v.findViewById(R.id.tile_post_title);
        postDesc = v.findViewById(R.id.tile_post_desc);
        author = v.findViewById(R.id.tile_post_author);
        likeCounter = v.findViewById(R.id.tile_post_likesCounter);
        Post post = this.getItem(position);
        postTitle.setText(post.getCategory());
        postDesc.setText(post.getDesc());
        //author.setText() <-- ТУТ С ЮЗЕРА ИМЯ ВЗЯТЬ, Я В ДБ ВПИШУ ПРОСТО МНЕ ПОХУЙ
        likeCounter.setText(Integer.toString(post.getLikes()));
        return v;
    }
}
