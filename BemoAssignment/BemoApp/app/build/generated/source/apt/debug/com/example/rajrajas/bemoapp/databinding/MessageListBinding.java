package com.example.rajrajas.bemoapp.databinding;
import com.example.rajrajas.bemoapp.R;
import com.example.rajrajas.bemoapp.BR;
import android.view.View;
public class MessageListBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    public final android.support.constraint.ConstraintLayout mainContainer;
    public final android.widget.TextView messageText;
    public final android.widget.TextView nameText;
    // variables
    private com.example.rajrajas.bemoapp.Data.Message mItem;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public MessageListBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.mainContainer = (android.support.constraint.ConstraintLayout) bindings[0];
        this.mainContainer.setTag(null);
        this.messageText = (android.widget.TextView) bindings[2];
        this.messageText.setTag(null);
        this.nameText = (android.widget.TextView) bindings[1];
        this.nameText.setTag(null);
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
                setItem((com.example.rajrajas.bemoapp.Data.Message) variable);
                return true;
        }
        return false;
    }

    public void setItem(com.example.rajrajas.bemoapp.Data.Message Item) {
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }
    public com.example.rajrajas.bemoapp.Data.Message getItem() {
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
        com.example.rajrajas.bemoapp.Data.Message item = mItem;
        java.lang.String itemMessage = null;
        java.lang.String itemName = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.message
                    itemMessage = item.getMessage();
                    // read item.name
                    itemName = item.getName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.messageText, itemMessage);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.nameText, itemName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static MessageListBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static MessageListBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<MessageListBinding>inflate(inflater, com.example.rajrajas.bemoapp.R.layout.message_list, root, attachToRoot, bindingComponent);
    }
    public static MessageListBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static MessageListBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.example.rajrajas.bemoapp.R.layout.message_list, null, false), bindingComponent);
    }
    public static MessageListBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static MessageListBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/message_list_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new MessageListBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}