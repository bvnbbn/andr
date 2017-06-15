package com.example.rajrajas.bemoapp.databinding;
import com.example.rajrajas.bemoapp.R;
import com.example.rajrajas.bemoapp.BR;
import android.view.View;
public class ChatlayoutBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 1);
        sViewsWithIds.put(R.id.nested_view, 2);
        sViewsWithIds.put(R.id.item_list, 3);
        sViewsWithIds.put(R.id.footer, 4);
        sViewsWithIds.put(R.id.message_text, 5);
    }
    // views
    public final android.widget.LinearLayout footer;
    public final android.support.v7.widget.RecyclerView itemList;
    public final android.widget.RelativeLayout mainContainer;
    public final android.widget.EditText messageText;
    public final android.support.v4.widget.NestedScrollView nestedView;
    public final android.support.v7.widget.Toolbar toolbar;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ChatlayoutBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.footer = (android.widget.LinearLayout) bindings[4];
        this.itemList = (android.support.v7.widget.RecyclerView) bindings[3];
        this.mainContainer = (android.widget.RelativeLayout) bindings[0];
        this.mainContainer.setTag(null);
        this.messageText = (android.widget.EditText) bindings[5];
        this.nestedView = (android.support.v4.widget.NestedScrollView) bindings[2];
        this.toolbar = (android.support.v7.widget.Toolbar) bindings[1];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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
        }
        return false;
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ChatlayoutBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ChatlayoutBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ChatlayoutBinding>inflate(inflater, com.example.rajrajas.bemoapp.R.layout.chatlayout, root, attachToRoot, bindingComponent);
    }
    public static ChatlayoutBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ChatlayoutBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.example.rajrajas.bemoapp.R.layout.chatlayout, null, false), bindingComponent);
    }
    public static ChatlayoutBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ChatlayoutBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/chatlayout_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ChatlayoutBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}