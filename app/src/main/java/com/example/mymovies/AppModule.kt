package com.example.mymovies

import com.example.core.domain.usecase.banner.BannerInteractor
import com.example.core.domain.usecase.banner.BannerUsecase
import com.example.core.domain.usecase.comingsoon.ComingSoonInteractor
import com.example.core.domain.usecase.comingsoon.ComingSoonUsecase
import com.example.core.domain.usecase.detailmovie.DetailMovieInteractor
import com.example.core.domain.usecase.detailmovie.DetailMovieUsecase
import com.example.core.domain.usecase.moviesgenre.MoviesGenreInteractor
import com.example.core.domain.usecase.moviesgenre.MoviesGenreUsecase
import com.example.core.domain.usecase.moviesingenre.MoviesInGenreInteractor
import com.example.core.domain.usecase.moviesingenre.MoviesInGenreUsecase
import com.example.core.domain.usecase.popularmovies.PopularMoviesInteractor
import com.example.core.domain.usecase.popularmovies.PopularMoviesUsecase
import com.example.core.domain.usecase.popularmoviesgrid.PopularMoviesGridInteractor
import com.example.core.domain.usecase.popularmoviesgrid.PopularMoviesGridUsecase
import com.example.core.domain.usecase.review.ReviewInteractor
import com.example.core.domain.usecase.review.ReviewUsecase
import com.example.core.domain.usecase.trailervideo.TrailerVideoInteractor
import com.example.core.domain.usecase.trailervideo.TrailerVideoUsecase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideBannerUsecase(bannerInteractor: BannerInteractor): BannerUsecase

    @Binds
    abstract fun providePopularMoviesUsecase(popularMoviesInteractor: PopularMoviesInteractor): PopularMoviesUsecase

    @Binds
    abstract fun provideComingSoonUsecase(comingSoonInteractor: ComingSoonInteractor): ComingSoonUsecase

    @Binds
    abstract fun provideDetailMovieUsecase(detailMovieInteractor: DetailMovieInteractor): DetailMovieUsecase

    @Binds
    abstract fun providePopularMoviesGridUsecase(popularMoviesGridInteractor: PopularMoviesGridInteractor): PopularMoviesGridUsecase

    @Binds
    abstract fun provideMoviesGenreUsecase(moviesGenreInteractor: MoviesGenreInteractor): MoviesGenreUsecase

    @Binds
    abstract fun provideMoviesInGenreUsecase(moviesInGenreInteractor: MoviesInGenreInteractor): MoviesInGenreUsecase

    @Binds
    abstract fun reviewUsecase(reviewInteractor: ReviewInteractor): ReviewUsecase

    @Binds
    abstract fun trailerVideoUsecase(trailerVideoInteractor: TrailerVideoInteractor): TrailerVideoUsecase
}