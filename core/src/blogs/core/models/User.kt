package blogs.core.models

import java.sql.Timestamp

 data class User (
    val id:Long,
    val name:String,

    val created_at: Timestamp,
    val updated_at: Timestamp
 )
