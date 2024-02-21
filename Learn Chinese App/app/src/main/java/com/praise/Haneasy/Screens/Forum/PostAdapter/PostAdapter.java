package com.praise.Haneasy.Screens.Forum.PostAdapter;

import static com.praise.Haneasy.Splash.APP_URL;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.praise.Haneasy.R;
import com.praise.Haneasy.Screens.Learning.Learning_Adapter.LearningAdapter;
import com.praise.Haneasy.Screens.MyClickInterface;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    Context context;
    ArrayList<PostEntities> postEntities;
    MyClickInterface clickInterface;


    public PostAdapter(Context context, ArrayList<PostEntities> postEntities, MyClickInterface clickInterface) {
        this.context = context;
        this.postEntities = postEntities;
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card, parent, false);
        PostAdapter.ViewHolder viewHolder = new PostAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.username.setText("from: " + postEntities.get(position).getUserName());
        holder.postDesc.setText(Html.fromHtml(postEntities.get(position).getPostDesc()));
        holder.postDate.setText(postEntities.get(position).getPostDate());
        holder.count.setText("Comments: "+getCommentCount(postEntities.get(position).getId()));
    }

    private String getCommentCount(int id) {
        String[] field = new String[1];
        field[0] = "id";
        String[] data = new String[1];
        data[0] = id + "";
        String count = "0";
        PutData putData = new PutData(APP_URL + "commentCount.php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                count = putData.getResult();
                System.out.println(count);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(putData.getResult());
                    String question = jsonObject.get("data").toString();
                    System.out.println(question);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        org.json.JSONObject obj = (org.json.JSONObject) jsonArray.get(i);
                         count = obj.getString("count(post_id)");
                        Log.d("details",count);

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }



            }
        }
        return count;
    }

    @Override
    public int getItemCount() {
        return postEntities.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView username, postDesc, postDate, count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.post_user);
            postDate = itemView.findViewById(R.id.post_date);
            postDesc = itemView.findViewById(R.id.post_desc);
            count = itemView.findViewById(R.id.coment_count);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
