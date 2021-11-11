package id.ac.ubaya.informatika.todoapp_week8_160419007.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity/*(tableName = "todoTable")//ini buat kalo tabel di database*/
data class Todo(
        @ColumnInfo(name="title")//ini buat kalo tabel di database
        var title:String,
        @ColumnInfo(name="notes")//ini buat kalo tabel di database
        var notes:String,
        @ColumnInfo(name ="priority")
        var priority:Int,
        @ColumnInfo(name ="is_done")
        var is_done:Int,
        @ColumnInfo(name="todo_date")
        var todo_date:Int
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}
