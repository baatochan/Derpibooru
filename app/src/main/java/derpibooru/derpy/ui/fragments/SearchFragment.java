package derpibooru.derpy.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import derpibooru.derpy.R;
import derpibooru.derpy.data.server.DerpibooruUser;
import derpibooru.derpy.ui.MainActivity;
import derpibooru.derpy.ui.SearchResultActivity;
import derpibooru.derpy.ui.views.FloatingSearchView;

public class SearchFragment extends NavigationDrawerUserFragment {
    @Bind(R.id.floatingSearchView) FloatingSearchView mSearchView;
    @Bind(R.id.textSearchHelp) TextView mHelpView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, v);
        initializeSearchView();
        initializeHelpView();
        return v;
    }

    @Override
    protected void onUserRefreshed(DerpibooruUser user) { }

    private void initializeSearchView() {
        mSearchView.setFloatingSearchViewListener(new FloatingSearchView.FloatingSearchViewListener() {
            @Override
            public void onSearchAction(String query) {
                Intent i = new Intent(getContext(), SearchResultActivity.class);
                i.putExtra(SearchResultActivity.EXTRAS_SEARCH_QUERY, query);
                i.putExtra(MainActivity.EXTRAS_USER, getUser());
                startActivity(i);
            }
        });
    }

    private void initializeHelpView() {
        mHelpView.setText(Html.fromHtml(getString(R.string.search_help)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
