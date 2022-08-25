package blogs.core.services

import blogs.core.models.Blog
import blogs.core.models.User

interface BlogRepository {
    fun fetchBlogById(blogId: Long): List<Blog>
    fun createUser(username:String):List<User>
    fun createUserBlog(userId:Long,title:String,content:String):List<Blog>
    fun updateUserBlog(userId:Long,title:String,content:String):List<Blog>
    fun deleteUserBlog(userId:Long,blog:Long):List<Blog>
    fun displayUserBlog(userId: Long):List<Blog>
    fun displayAllUser():List<User>
    fun getUser(userId: Long):List<User>
     fun getAll(): List<Blog>
}
