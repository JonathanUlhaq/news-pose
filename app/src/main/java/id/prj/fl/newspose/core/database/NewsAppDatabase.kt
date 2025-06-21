package id.prj.fl.newspose.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.prj.fl.newspose.features.bookmarks.data.dao.BookMarksDao
import id.prj.fl.newspose.features.bookmarks.data.entities.BookMarksEntity
import id.prj.fl.newspose.features.home.data.dao.HomeArticleDao
import id.prj.fl.newspose.features.home.data.entities.ArticleEntity

@Database(
    entities = [BookMarksEntity::class, ArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewsAppDatabase : RoomDatabase() {
    abstract fun bookMarksDao(): BookMarksDao
    abstract fun homeArticleDao(): HomeArticleDao
}