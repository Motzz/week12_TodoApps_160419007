package id.ac.ubaya.informatika.todoapp_week8_160419007.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity/*(tableName = "todoTable")//ini buat kalo tabel di database*/
data class Todo(
        @ColumnInfo(name="title")//ini buat kalo tabel di database
        var title:String,
        @ColumnInfo(name="notes")//ini buat kalo tabel di database
        var notes:String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}
