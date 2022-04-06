package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.MoviesGenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesGenreDao {

    @Query("SELECT * FROM movies_genre")
    fun getMoviesGenre(): Flow<List<MoviesGenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviesGenre(moviesGenre: List<MoviesGenreEntity>)

    @Query("DELETE FROM movies_genre")
    suspend fun deleteMoviesGenre()

    @Transaction
    suspend fun insertAndDeleteMoviesGenre(moviesGenre: List<MoviesGenreEntity>) {
        deleteMoviesGenre()
        insertMoviesGenre(moviesGenre)
    }

}