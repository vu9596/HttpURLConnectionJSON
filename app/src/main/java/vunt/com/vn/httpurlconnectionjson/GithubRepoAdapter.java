package vunt.com.vn.httpurlconnectionjson;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GithubRepoAdapter extends RecyclerView.Adapter<GithubRepoAdapter.ViewHolder> {

    private List<GithubRepo> mRepos;

    public GithubRepoAdapter(List<GithubRepo> repos) {
        mRepos = repos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_repo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mRepos.get(position));
    }

    @Override
    public int getItemCount() {
        return  mRepos != null ? mRepos.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mLanguage;
        private TextView mOwner;
        private ImageView mAvatar;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.text_repo_name);
            mLanguage = itemView.findViewById(R.id.text_repo_language);
            mOwner = itemView.findViewById(R.id.text_repo_owner);
            mAvatar = itemView.findViewById(R.id.image_avatar);
        }

        public void bindData(GithubRepo repo) {
            mName.setText(repo.getName());
            mLanguage.setText(repo.getLaguage());
            mOwner.setText(repo.getOwner().getLogin());
            Glide.with(itemView.getContext())
                    .load(repo.getOwner().getAvatar())
                    .into(mAvatar);
        }
    }
}
