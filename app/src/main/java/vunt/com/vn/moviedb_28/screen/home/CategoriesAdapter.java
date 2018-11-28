package vunt.com.vn.moviedb_28.screen.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import vunt.com.vn.moviedb_28.R;
import vunt.com.vn.moviedb_28.data.model.Movie;
import vunt.com.vn.moviedb_28.databinding.ItemMovieBinding;
import vunt.com.vn.moviedb_28.util.Constant;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private List<Movie> mMovies;

    public CategoriesAdapter(List<Movie> movies) {
        mMovies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemMovieBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_movie,
                viewGroup,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mMovies.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding mBinding;
        private ItemMovieListViewModel mItemMovieListViewModel;

        public ViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mItemMovieListViewModel = new ItemMovieListViewModel();
            mBinding.setViewModel(mItemMovieListViewModel);
        }

        public void bindData(Movie movie) {
            mItemMovieListViewModel.setMovie(movie);
            mBinding.executePendingBindings();
        }
    }

    public void addData(List<Movie> movies) {
        int posotionStart = mMovies.size();
        mMovies.addAll(movies);
        notifyItemRangeInserted(posotionStart, movies.size());
    }

    public void addData(Movie movie) {
        mMovies.add(movie);
        notifyItemInserted(mMovies.size() - Constant.INDEX_UNIT);
    }

    public void replaceData(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }
}
