package vunt.com.vn.httpurlconnectionjson;

import java.io.Serializable;

public class GithubRepo implements Serializable {
    private static final String NO_DESCRIPTION = "No desscription";
    private String mName;
    private String mFullNane;
    private GithubUser mOwner;
    private String mHtmlUrl;
    private String mDescription;
    private String mDownloadUrl;
    private String mCreateDate;
    private String mUpdateDate;
    private String mPushDate;
    private int mSize;
    private int mWatchersCount;
    private int mForkCount;
    private String mLaguage;

    public GithubRepo(String name, String createDate, String laguage) {
        mName = name;
        mCreateDate = createDate;
        mLaguage = laguage;
    }

    public String getName() {
        return mName;
    }

    public GithubRepo setName(String name) {
        mName = name;
        return this;
    }

    public String getFullNane() {
        return mFullNane;
    }

    public GithubRepo setFullNane(String fullNane) {
        mFullNane = fullNane;
        return this;
    }

    public GithubUser getOwner() {
        return mOwner;
    }

    public GithubRepo setOwner(GithubUser owner) {
        mOwner = owner;
        return this;
    }

    public String getHtmlUrl() {
        return mHtmlUrl;
    }

    public GithubRepo setHtmlUrl(String htmlUrl) {
        mHtmlUrl = htmlUrl;
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public GithubRepo setDescription(String description) {
        mDescription = description != null ? description : NO_DESCRIPTION;
        return this;
    }

    public String getDownloadUrl() {
        return mDownloadUrl;
    }

    public GithubRepo setDownloadUrl(String downloadUrl) {
        mDownloadUrl = downloadUrl;
        return this;
    }

    public String getCreateDate() {
        return mCreateDate;
    }

    public GithubRepo setCreateDate(String createDate) {
        mCreateDate = createDate;
        return this;
    }

    public String getUpdateDate() {
        return mUpdateDate;
    }

    public GithubRepo setUpdateDate(String updateDate) {
        mUpdateDate = updateDate;
        return this;
    }

    public String getPushDate() {
        return mPushDate;
    }

    public GithubRepo setPushDate(String pushDate) {
        mPushDate = pushDate;
        return this;
    }

    public int getSize() {
        return mSize;
    }

    public GithubRepo setSize(int size) {
        mSize = size;
        return this;
    }

    public int getWatchersCount() {
        return mWatchersCount;
    }

    public GithubRepo setWatchersCount(int watchersCount) {
        mWatchersCount = watchersCount;
        return this;
    }

    public int getForkCount() {
        return mForkCount;
    }

    public GithubRepo setForkCount(int forkCount) {
        mForkCount = forkCount;
        return this;
    }

    public String getLaguage() {
        return mLaguage;
    }

    public GithubRepo setLaguage(String laguage) {
        mLaguage = laguage;
        return this;
    }
}
