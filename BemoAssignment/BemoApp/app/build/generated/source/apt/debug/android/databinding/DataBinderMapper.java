
package android.databinding;
import com.example.rajrajas.bemoapp.BR;
class DataBinderMapper  {
    final static int TARGET_MIN_SDK = 16;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.example.rajrajas.bemoapp.R.layout.chatlayout:
                    return com.example.rajrajas.bemoapp.databinding.ChatlayoutBinding.bind(view, bindingComponent);
                case com.example.rajrajas.bemoapp.R.layout.message_list:
                    return com.example.rajrajas.bemoapp.databinding.MessageListBinding.bind(view, bindingComponent);
                case com.example.rajrajas.bemoapp.R.layout.activity_main:
                    return com.example.rajrajas.bemoapp.databinding.ActivityMainBinding.bind(view, bindingComponent);
                case com.example.rajrajas.bemoapp.R.layout.movie_list:
                    return com.example.rajrajas.bemoapp.databinding.MovieListBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 1241565816: {
                if(tag.equals("layout/chatlayout_0")) {
                    return com.example.rajrajas.bemoapp.R.layout.chatlayout;
                }
                break;
            }
            case 1211945356: {
                if(tag.equals("layout/message_list_0")) {
                    return com.example.rajrajas.bemoapp.R.layout.message_list;
                }
                break;
            }
            case 423753077: {
                if(tag.equals("layout/activity_main_0")) {
                    return com.example.rajrajas.bemoapp.R.layout.activity_main;
                }
                break;
            }
            case -53575805: {
                if(tag.equals("layout/movie_list_0")) {
                    return com.example.rajrajas.bemoapp.R.layout.movie_list;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"item"};
    }
}