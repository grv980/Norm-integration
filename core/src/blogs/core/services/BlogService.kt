package blogs.core.services

import blogs.core.models.Blog
import blogs.core.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogService @Inject constructor(
    private val blogRepository: BlogRepository
) {
    fun getAll(): List<Blog> = blogRepository.getAll()
    fun createUser(username: String): List<User> = blogRepository.createUser(username)
    fun createUserBlog(userId: Long, title: String, content: String): List<Blog> =
        blogRepository.createUserBlog(userId, title, content)

    fun updateUserBlog(userId: Long, title: String, content: String): List<Blog> =
        blogRepository.updateUserBlog(userId, title, content)

    fun deleteUserBlog(userId: Long, blog: Long): List<Blog> = blogRepository.deleteUserBlog(userId, blog)
    fun displayUserBlog(userId: Long): List<Blog> = blogRepository.displayUserBlog(userId)
    fun displayAllUser(): List<User> = blogRepository.displayAllUser()
    fun getUser(userId: Long): List<User> = blogRepository.getUser(userId)
}
