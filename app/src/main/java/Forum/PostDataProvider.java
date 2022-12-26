package Forum;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Forum.models.OnPostsRetrievedListener;
import Forum.models.OnSinglePostRetrievedListener;

public class PostDataProvider {
    private FirebaseDatabase db;
    private DatabaseReference forum;
    private DatabaseReference posts;

    public PostDataProvider() {
        db = FirebaseDatabase.getInstance();
        forum = db.getReference().child("Forum");
        posts = forum.child("Posts");
    }
    /*
    @param Context is used to display snack-bar
     */
    public void addPost(Post post, Context context){
        DatabaseReference push = posts.push();
        post.setPostId(push.getKey());
        push.setValue(post);
    }

    public void getSinglePost(OnSinglePostRetrievedListener listener){
        Query query = posts.orderByChild("postId");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Post post =new Post();
                for( DataSnapshot postData : snapshot.getChildren()){
                    post = postData.getValue(Post.class);
                }
                listener.OnPostRetrieved(post);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getPosts(OnPostsRetrievedListener listener){
        ArrayList<Post> postArrayList= new ArrayList<>();
        Query query = posts.orderByChild("postId");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postArrayList.clear();
                for(DataSnapshot postData : snapshot.getChildren()){
                    Post post = postData.getValue(Post.class);
                    if(post!=null){
                        postArrayList.add(post);
                    }

                }
                listener.OnPostsRetrieved(postArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getPostsByCategory(OnPostsRetrievedListener listener, String category){
        ArrayList<Post> postArrayList= new ArrayList<>();
        Query query = posts.orderByChild("category").equalTo(category);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postArrayList.clear();
                for(DataSnapshot postData : snapshot.getChildren()){
                    Post post = postData.getValue(Post.class);
                    if(post!=null){
                        postArrayList.add(post);
                    }

                }
                listener.OnPostsRetrieved(postArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void updatePost(Post post){
        posts.child(post.getPostId()).setValue(post);
    }

    public void deletePost(Post post){
        posts.child(post.getPostId()).removeValue();
    }
}
