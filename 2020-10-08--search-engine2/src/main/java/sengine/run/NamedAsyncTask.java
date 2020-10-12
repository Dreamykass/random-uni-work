package sengine.run;

public interface NamedAsyncTask<T> extends AsyncTask<T> {
    String getName();
}
