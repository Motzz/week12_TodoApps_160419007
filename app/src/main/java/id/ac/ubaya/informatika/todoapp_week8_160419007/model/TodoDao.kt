package id.ac.ubaya.informatika.todoapp_week8_160419007.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo:Todo)//bisa lebih dari 1 todo

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    suspend fun selectTodo(id:Int): Todo

    @Query("SELECT * FROM todo WHERE is_done=0")
    suspend fun selectIsDone(): List<Todo>

    @Query("UPDATE todo SET title=:title,notes=:notes,priority=:priority WHERE uuid=:uuid")
    suspend fun update(title:String,notes:String,priority:Int,uuid:Int)

    @Query("UPDATE todo SET is_done=1 WHERE uuid=:uuid")
    suspend fun updateDone(uuid:Int)

    @Delete
    suspend fun deleteTodo(todo:Todo)

}