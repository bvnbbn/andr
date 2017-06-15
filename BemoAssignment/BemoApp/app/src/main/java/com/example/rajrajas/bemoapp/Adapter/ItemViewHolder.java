package com.example.rajrajas.bemoapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.rajrajas.bemoapp.Data.MovieItem;
import com.example.rajrajas.bemoapp.databinding.MovieListBinding;
import com.example.rajrajas.bemoapp.dummy;

/**
 * Created by rajrajas on 5/30/2017.
 */

 class ItemViewHolder extends RecyclerView.ViewHolder
{
    private MovieListBinding movieListBinding;
    private Context context;
    private LruCache<String, Bitmap> mMemoryCache;
    private Dialog dialog;

    ItemViewHolder(Context context, MovieListBinding binding)
    {
        super(binding.getRoot());
        this.movieListBinding = binding;
        this.context=context;
        }


    void  bind(MovieItem item_data)
    {
        movieListBinding.setItem(item_data);
        movieListBinding.MovieReleaseDate.setText("Released on "+item_data.getMovie_release_date());

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap)
            {
                return bitmap.getByteCount() / 1024;
            }
        };

        final Bitmap bitmap = getBitmapFromMemCache(item_data.getMovie_Poster_path());
        if (bitmap != null)
        {
            movieListBinding.MovieImg.setImageBitmap(bitmap);
        } else
            {
//                Picasso.with(context)
//                        .load(dummy.Image_url_part+item_data.getMovie_Poster_path())
//                        .into(movieListBinding.MovieImg);
               // dialog_Show();
                Glide.with(context).load(dummy.Image_url_part+item_data.getMovie_Poster_path())
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .dontAnimate()
                        .listener(new RequestListener<String, GlideDrawable>()
                        {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                               // dialog.dismiss();
                                return false;
                            }

                        })
                        .into(movieListBinding.MovieImg);

        }

    }

    private Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

}

