package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.TrailerVideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrailerVideoDao {

    @Query("SELECT * FROM trailer_video")
    fun getTrailerVideo(): Flow<List<TrailerVideoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrailerVideo(trailerVideo: List<TrailerVideoEntity>)

    @Query("DELETE FROM trailer_video")
    suspend fun deleteTrailerVideo()

    @Transaction
    suspend fun insertAndDeleteTrailerVideo(trailerVideo: List<TrailerVideoEntity>) {
        deleteTrailerVideo()
        insertTrailerVideo(trailerVideo)
    }

}