package vunt.com.vn.httpurlconnectionjson;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity implements OnLoadingApiListener {
    public static final String EXTRA_GIT_REPO = "extra_get_repos";
    public static final String KEY_REPO_NAME = "name";
    public static final String KEY_REPO_FULL_NAME = "full_name";
    public static final String KEY_REPO_HTML_URL = "html_url";
    public static final String KEY_REPO_DESCRIPTION = "description";
    public static final String KEY_REPO_URL = "url";
    public static final String KEY_REPO_CREATED_AT = "created_at";
    public static final String KEY_REPO_UPDATED_AT = "updated_at";
    public static final String KEY_REPO_PUSHED_AT = "pushed_at";
    public static final String KEY_REPO_SIZE = "size";
    public static final String KEY_REPO_WATHCHERS_COUNT = "watchers_count";
    public static final String KEY_REPO_FORKS_COUNT = "forks_count";
    public static final String KEY_REPO_LANGUAGE = "language";
    public static final String KEY_REPO_OWNER = "owner";
    public static final String KEY_OWNER_LOGIN = "login";
    public static final String KEY_OWNER_TYPE = "type";
    public static final String KEY_OWNER_AVATAR = "avatar_url";
    public static final String REQUEST_METHOD = "GET";
    public static final int connectTimeout = 15000;
    public static final int readTimeout = 15000;
    private int WHAT_FINISH_LOADING_API = 9596;
    private static final String TOAST_LOADING_SUCCESS = "Loading successful";
    public static final String GITHUB_API = "https://api.github.com/users/google/repos";
    private List<GithubRepo> mRepos;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == WHAT_FINISH_LOADING_API) {
                startActivity(getMainIntent(SplashActivity.this, mRepos));
                SplashActivity.this.finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mRepos = new ArrayList<>();
        new LoadApiAsyncTask(this).execute(GITHUB_API);
    }

    @Override
    public void onLoadingSuccess() {
        Toast.makeText(this, TOAST_LOADING_SUCCESS, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReadApiFail(Exception e) {
        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        SplashActivity.this.finish();
    }

    public class LoadApiAsyncTask extends AsyncTask<String, Integer, List<GithubRepo>> {
        private OnLoadingApiListener mListener;
        private Exception mException;

        public LoadApiAsyncTask(OnLoadingApiListener listener) {
            mListener = listener;
        }

        @Override
        protected List<GithubRepo> doInBackground(String... strings) {
            String jsonString = getJsonFromURL(strings[0]);
            mRepos.addAll(convertJson2Object(jsonString));
            return mRepos;
        }

        @Override
        protected void onPostExecute(List<GithubRepo> repos) {
            super.onPostExecute(repos);
            if (mException == null) {
                mListener.onLoadingSuccess();
                mHandler.sendEmptyMessage(WHAT_FINISH_LOADING_API);
            } else {
                mListener.onReadApiFail(mException);
            }
        }

        private String getJsonFromURL(String api) {
            try {
                HttpURLConnection urlConnection = null;
                URL url = new URL(api);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod(REQUEST_METHOD);
                urlConnection.setConnectTimeout(connectTimeout);
                urlConnection.setReadTimeout(readTimeout);
                urlConnection.connect();
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                urlConnection.disconnect();
                return sb.toString();
            } catch (IOException e) {
                this.mException = e;
                return null;
            }
        }

        private List<GithubRepo> convertJson2Object(String jsonString) {
            List<GithubRepo> repos = new ArrayList<>();
            try {
                JSONArray repoJson = new JSONArray(jsonString);
                for (int i = 0; i < repoJson.length(); i++) {
                    JSONObject repoObject = repoJson.getJSONObject(i);
                    String name = repoObject.getString(KEY_REPO_NAME);
                    String fullName = repoObject.getString(KEY_REPO_FULL_NAME);
                    String htmlUrl = repoObject.getString(KEY_REPO_HTML_URL);
                    String description = repoObject.getString(KEY_REPO_DESCRIPTION);
                    String downloadUrl = repoObject.getString(KEY_REPO_URL);
                    String createDate = repoObject.getString(KEY_REPO_CREATED_AT);
                    String updateDate = repoObject.getString(KEY_REPO_UPDATED_AT);
                    String pushDate = repoObject.getString(KEY_REPO_PUSHED_AT);
                    int size = repoObject.getInt(KEY_REPO_SIZE);
                    int watchersCount = repoObject.getInt(KEY_REPO_WATHCHERS_COUNT);
                    int forkCount = repoObject.getInt(KEY_REPO_FORKS_COUNT);
                    String laguage = repoObject.getString(KEY_REPO_LANGUAGE);
                    JSONObject ownerObject = repoObject.getJSONObject(KEY_REPO_OWNER);
                    String loginOwner = ownerObject.getString(KEY_OWNER_LOGIN);
                    String avaterOwner = ownerObject.getString(KEY_OWNER_AVATAR);
                    String typeOwner = ownerObject.getString(KEY_OWNER_TYPE);
                    GithubUser user = new GithubUser(loginOwner);
                    user.setAvatar(avaterOwner);
                    user.setType(typeOwner);
                    GithubRepo repo = new GithubRepo(name, createDate, laguage);
                    repo.setOwner(user);
                    repo.setFullNane(fullName);
                    repo.setHtmlUrl(htmlUrl);
                    repo.setDescription(description);
                    repo.setDownloadUrl(downloadUrl);
                    repo.setUpdateDate(updateDate);
                    repo.setPushDate(pushDate);
                    repo.setSize(size);
                    repo.setWatchersCount(watchersCount);
                    repo.setForkCount(forkCount);
                    repos.add(repo);
                }
            } catch (JSONException e) {
                this.mException = e;
            }
            return repos;
        }
    }

    public static Intent getMainIntent(Context context, List<GithubRepo> repos) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_GIT_REPO, (Serializable) repos);
        return intent;
    }
}
