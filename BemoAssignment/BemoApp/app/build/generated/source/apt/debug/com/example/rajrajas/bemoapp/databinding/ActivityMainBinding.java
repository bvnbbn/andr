package com.example.rajrajas.bemoapp.databinding;
import com.example.rajrajas.bemoapp.R;
import com.example.rajrajas.bemoapp.BR;
import android.view.View;
public class ActivityMainBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 1);
        sViewsWithIds.put(R.id.item_list, 2);
        sViewsWithIds.put(R.id.bottom_lay, 3);
        sViewsWithIds.put(R.id.prev_bt, 4);
        sViewsWithIds.put(R.id.first_id, 5);
        sViewsWithIds.put(R.id.second_id, 6);
        sViewsWithIds.put(R.id.third_id, 7);
        sViewsWithIds.put(R.id.fourth_id, 8);
        sViewsWithIds.put(R.id.fifth_id, 9);
        sViewsWithIds.put(R.id.next_bt, 10);
    }
    // views
    public final android.support.constraint.ConstraintLayout bottomLay;
    public final android.widget.Button fifthId;
    public final android.widget.Button firstId;
    public final android.widget.Button fourthId;
    public final android.support.v7.widget.RecyclerView itemList;
    public final android.widget.RelativeLayout mainContainer;
    public final android.widget.Button nextBt;
    public final android.widget.Button prevBt;
    public final android.widget.Button secondId;
    public final android.widget.Button thirdId;
    public final android.support.v7.widget.Toolbar toolbar;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMainBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.bottomLay = (android.support.constraint.ConstraintLayout) bindings[3];
        this.fifthId = (android.widget.Button) bindings[9];
        this.firstId = (android.widget.Button) bindings[5];
        this.fourthId = (android.widget.Button) bindings[8];
        this.itemList = (android.support.v7.widget.RecyclerView) bindings[2];
        this.mainContainer = (android.widget.RelativeLayout) bindings[0];
        this.mainContainer.setTag(null);
        this.nextBt = (android.widget.Button) bindings[10];
        this.prevBt = (android.widget.Button) bindings[4];
        this.secondId = (android.widget.Button) bindings[6];
        this.thirdId = (android.widget.Button) bindings[7];
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

    public static ActivityMainBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMainBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.example.rajrajas.bemoapp.R.layout.activity_main, root, attachToRoot, bindingComponent);
    }
    public static ActivityMainBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMainBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.example.rajrajas.bemoapp.R.layout.activity_main, null, false), bindingComponent);
    }
    public static ActivityMainBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMainBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_main_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityMainBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}