package vunt.com.vn.httpurlconnectionjson;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListRepoFragment extends Fragment {
    private static final String BUNDLE_GIT_REPOS = "bundle_git_repos";
    private View mView;
    private GithubRepoAdapter mRepoAdapter;
    private List<GithubRepo> mRepos;

    public static ListRepoFragment newInstance(List<GithubRepo> repos) {
        ListRepoFragment listRepoFragment = new ListRepoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_GIT_REPOS, (Serializable) repos);
        listRepoFragment.setArguments(bundle);
        return listRepoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepos = new ArrayList<>();
        if (getArguments() != null) {
            mRepos.addAll((Collection<? extends GithubRepo>)
                    getArguments().getSerializable(BUNDLE_GIT_REPOS));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_list_repo, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRepoAdapter = new GithubRepoAdapter(mRepos);
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = mView.findViewById(R.id.recycler_list_repo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mRepoAdapter);
    }
}
