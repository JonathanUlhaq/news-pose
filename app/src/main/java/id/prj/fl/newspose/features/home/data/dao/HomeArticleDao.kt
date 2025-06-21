package id.prj.fl.newspose.features.home.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.prj.fl.newspose.features.home.data.entities.ArticleEntity

@Dao
interface HomeArticleDao {
    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("DELETE FROM articles")
    suspend fun clearArticles()
}
