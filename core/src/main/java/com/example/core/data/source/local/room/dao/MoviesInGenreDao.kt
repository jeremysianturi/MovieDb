package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.MoviesInGenreEntity
import com.example.core.data.source.local.entity.PopularMoviesGridEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesInGenreDao {

    @Query("SELECT * FROM movies_in_genre")
    fun getMoviesInGenre(): Flow<List<MoviesInGenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesInGenre(moviesInGenre: List<MoviesInGenreEntity>)

    @Query("DELETE FROM movies_in_genre")
    suspend fun deleteMoviesInGenre()

    @Transaction
    suspend fun insertAndDeleteMoviesInGenre(moviesInGenre: List<MoviesInGenreEntity>) {
        deleteMoviesInGenre()
        insertMoviesInGenre(moviesInGenre)
    }

    @Transaction
    @Query("SELECT * FROM movies_in_genre WHERE original_title LIKE '%'|| :search || '%'")
    fun getSearchMoviesInGenre(search: String): Flow<List<MoviesInGenreEntity>>

}