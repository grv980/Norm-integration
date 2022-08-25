package blogs

import utility.Response

interface BlogController {
    fun getAll(): Response<Any>

    fun fetchBlogById(blogId: Long): Response<Any>
    fun createUser(userName: String): Response<Any>


    fun createUserBlog(userId: Long, title: String, content: String): Response<Any>
    fun updateUserBlog(userId: Long, blogId: Long, title: String, content: String): Response<Any>

    fun displayUserBlog(userId: Long): Response<Any>
    fun deleteUserBlog(userId: Long, blogId: Long): Response<Any>
    fun displayAllUser(): Response<Any>
}
