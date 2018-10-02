package vunt.com.vn.httpurlconnectionjson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListRepoFragment mListRepoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        List<GithubRepo> repos =
                (List<GithubRepo>) getIntent().getSerializableExtra(SplashActivity.EXTRA_GIT_REPO);
        mListRepoFragment = ListRepoFragment.newInstance(repos);
        getFragmentManager().beginTransaction().add(R.id.container, mListRepoFragment).commit();
    }
}
