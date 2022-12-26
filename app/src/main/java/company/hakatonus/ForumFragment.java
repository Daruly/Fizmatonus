package company.hakatonus;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.airbnb.paris.Paris;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Forum.Post;
import Forum.PostAdapter;
import Forum.PostDataProvider;
import Forum.models.OnPostsRetrievedListener;

public class ForumFragment extends Fragment {
    private static int currentSection=1;
    //currentSection == 1 <-> Discussions; 2 <-> Connections; 3 <-> Stories; 4 <-> Reviews
    private Button selectorBtn;
    private BottomAppBar bottomAppBar;
    private FloatingActionButton fab;
    private ListView postsListView;
    private ArrayList<Post> postArrayList;
    private PostAdapter postAdapter;
    private PostDataProvider provider;
    private OnPostsRetrievedListener onPostsRetrievedListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forum, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        selectorBtn = v.findViewById(R.id.fragment_forum_selector_button);
        bottomAppBar=v.findViewById(R.id.fragment_forum_selector);
        postsListView= v.findViewById(R.id.postsListView);
        postArrayList=new ArrayList<>();
        provider = new PostDataProvider();
        postAdapter=new PostAdapter(v.getContext(), R.layout.tile_post, postArrayList);
        postsListView.setAdapter(postAdapter);
        onPostsRetrievedListener = new OnPostsRetrievedListener() {
            @Override
            public void OnPostsRetrieved(ArrayList<Post> posts) {
                postArrayList.clear();
                postArrayList.addAll(posts);
                postAdapter.notifyDataSetChanged();
            }
        };
        fab = v.findViewById(R.id.fragment_forum_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(), ForumPostAddActivity.class);
                startActivity(intent);
            }
        });
        selectorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*for(int i=0;i<=200;i++){
                    int dimensionInPixel = i;
                    int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimensionInPixel, getResources().getDisplayMetrics());
                    bottomAppBar.getLayoutParams().height=dimensionInDp;
                    bottomAppBar.requestLayout();
                }*/
                showBottomDialog(v.findViewById(R.id.fragment_forum_linear_container));
            }
        });

        provider.getPostsByCategory(onPostsRetrievedListener, "Discussion");
    }


    private void showBottomDialog(View v){
        final Dialog dialog = new Dialog(v.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_forum_bottomsheetlayout);

        Button discussions = dialog.findViewById(R.id.fragment_forum_bottomsheetlayout_discussions);
        Button connections = dialog.findViewById(R.id.fragment_forum_bottomsheetlayout_connections);
        Button stories = dialog.findViewById(R.id.fragment_forum_bottomsheetlayout_stories);
        Button reviews = dialog.findViewById(R.id.fragment_forum_bottomsheetlayout_reviews);

        discussions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSection=1;
                int dimensionInSp = 20;
                int dimensionInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dimensionInSp, getResources().getDisplayMetrics());
                Paris.styleBuilder(discussions).add(R.style.selector_button).textSize(dimensionInPx).fontFamilyRes(R.font.inter_bold).apply();
                provider.getPostsByCategory(onPostsRetrievedListener, "Discussion");
                selectorBtn.setText("Новости");
                dialog.dismiss();
            }
        });
        connections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSection=2;
                int dimensionInSp = 20;
                int dimensionInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dimensionInSp, getResources().getDisplayMetrics());
                Paris.styleBuilder(connections).add(R.style.selector_button).textSize(dimensionInPx).fontFamilyRes(R.font.inter_bold).backgroundTint(3459).apply();
                provider.getPostsByCategory(onPostsRetrievedListener, "Connection");
                selectorBtn.setText("Домашнее Задание");
                dialog.dismiss();
            }
        });
        stories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSection=3;
                provider.getPostsByCategory(onPostsRetrievedListener, "Story");
                selectorBtn.setText("Школьные Истории");
                dialog.dismiss();
            }
        });
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provider.getPostsByCategory(onPostsRetrievedListener, "Review");
                currentSection=4;
                selectorBtn.setText("Обсуждение");
                dialog.dismiss();
            }
        });




        dialog.show();
        dialog.getWindow().setDimAmount(0);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().getAttributes().windowAnimations = R.style.BottomDialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.y=-100;
        dialog.getWindow().setAttributes(params);
    }

}