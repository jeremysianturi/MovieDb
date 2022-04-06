package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

    @Query("SELECT * FROM review")
    fun getReview(): Flow<List<ReviewEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(review: List<ReviewEntity>)

    @Query("DELETE FROM review")
    suspend fun deleteReview()

    @Transaction
    suspend fun insertAndDeleteReview(review: List<ReviewEntity>) {
        deleteReview()
        insertReview(review)
    }

}