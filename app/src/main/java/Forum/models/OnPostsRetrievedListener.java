package Forum.models;

import java.util.ArrayList;

import Forum.Post;

public interface OnPostsRetrievedListener {
    void OnPostsRetrieved(ArrayList<Post> posts);
}
