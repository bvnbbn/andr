package com.example.rajrajas.bemoapp.databinding;
import com.example.rajrajas.bemoapp.R;
import com.example.rajrajas.bemoapp.BR;
import android.view.View;
public class MovieListBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.main_container, 4);
        sViewsWithIds.put(R.id.Movie_img, 5);
        sViewsWithIds.put(R.id.desc_layout, 6);
        sViewsWithIds.put(R.id.Movie_ReleaseDate, 7);
        sViewsWithIds.put(R.id.rating_circle, 8);
    }
    // views
    public final android.widget.ImageView MovieImg;
    public final android.widget.TextView MovieOverview;
    public final android.widget.TextView MovieReleaseDate;
    public final android.widget.TextView MovieTitle;
    public final android.widget.LinearLayout descLayout;
    public final android.support.constraint.ConstraintLayout mainContainer;
    private final android.support.v7.widget.CardView mboundView0;
    public final android.widget.ImageView ratingCircle;
    public final android.widget.TextView userRating;
    // variables
    private com.example.rajrajas.bemoapp.Data.MovieItem mItem;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public MovieListBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.MovieImg = (android.widget.ImageView) bindings[5];
        this.MovieOverview = (android.widget.TextView) bindings[2];
        this.MovieOverview.setTag(null);
        this.MovieReleaseDate = (android.widget.TextView) bindings[7];
        this.MovieTitle = (android.widget.TextView) bindings[1];
        this.MovieTitle.setTag(null);
        this.descLayout = (android.widget.LinearLayout) bindings[6];
        this.mainContainer = (android.support.constraint.ConstraintLayout) bindings[4];
        this.mboundView0 = (android.support.v7.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.ratingCircle = (android.widget.ImageView) bindings[8];
        this.userRating = (android.widget.TextView) bindings[3];
        this.userRating.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
            case BR.item :
                setItem((com.example.rajrajas.bemoapp.Data.MovieItem) variable);
                return true;
        }
        return false;
    }

    public void setItem(com.example.rajrajas.bemoapp.Data.MovieItem Item) {
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }
    public com.example.rajrajas.bemoapp.Data.MovieItem getItem() {
        return mItem;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String itemMovieName = null;
        com.example.rajrajas.bemoapp.Data.MovieItem item = mItem;
        java.lang.String itemMovieVoteAverage = null;
        java.lang.String itemMovieOverview = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.movie_Name
                    itemMovieName = item.getMovie_Name();
                    // read item.movie_Vote_average
                    itemMovieVoteAverage = item.getMovie_Vote_average();
                    // read item.movie_Overview
                    itemMovieOverview = item.getMovie_Overview();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.MovieOverview, itemMovieOverview);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.MovieTitle, itemMovieName);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.userRating, itemMovieVoteAverage);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static MovieListBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static MovieListBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<MovieListBinding>inflate(inflater, com.example.rajrajas.bemoapp.R.layout.movie_list, root, attachToRoot, bindingComponent);
    }
    public static MovieListBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static MovieListBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.example.rajrajas.bemoapp.R.layout.movie_list, null, false), bindingComponent);
    }
    public static MovieListBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static MovieListBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/movie_list_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new MovieListBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}