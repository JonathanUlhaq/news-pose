package id.prj.fl.newspose.features.bookmarks.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.prj.fl.newspose.features.bookmarks.data.entities.BookMarksEntity

@Dao
interface BookMarksDao {
    @Query("SELECT * FROM bookmark_table LIMIT :pageSize OFFSET :pageOffset")
    suspend fun getAllBookMarks(
        pageSize: Int,
        pageOffset: Int
    ): List<BookMarksEntity>

    @Query("SELECT * FROM bookmark_table WHERE uri = :uri LIMIT 1")
    suspend fun getBookMarkByUri(uri: String): BookMarksEntity

    @Query("SELECT EXISTS(SELECT 1 FROM bookmark_table WHERE uri = :uri LIMIT 1)")
    suspend fun isBookMarked(uri: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookMark(bookMark: BookMarksEntity)

    @Query("DELETE FROM bookmark_table WHERE uri = :uri")
    suspend fun removeBookMark(uri: String)
}