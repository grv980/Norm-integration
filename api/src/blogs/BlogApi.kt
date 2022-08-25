package blogs

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import javax.inject.Inject

@Controller("/api/v1/blogs")
class BlogApi @Inject constructor(
    private val blogController: BlogController
) {
    @Get("/")
    fun getAll(): HttpResponse<Any> {
        val blogs = blogController.getAll()
        return blogs.getHttpResponse()
    }
}

@Controller("/api/v1/users")
class CreateUserApi @Inject constructor(
    private val blogController: BlogController
) {
    @Post("/")
    fun createUser(userName:String): HttpResponse<Any> {
        val user = blogController.createUser(userName)
        return user.getHttpResponse()
    }


}

@Controller("/api/v1/user/{userId}/blog")
class CreateUserBlogApi @Inject constructor(
    private val blogController: BlogController
) {
    @Post("/")
    fun createUserBlog(userId:Long,title:String,content:String): HttpResponse<Any> {
        val blog = blogController.createUserBlog(userId, title, content)
        return blog.getHttpResponse()
    }


}

@Controller("/api/v1/user/{userId}/blog/{blogId}")
class updateUserBlogApi @Inject constructor(
    private val blogController: BlogController
) {
    @Post("/")
    fun updateUserBlog(userId:Long,blogId:Long,title:String,content:String): HttpResponse<Any> {
        val blog = blogController.updateUserBlog(userId, blogId, title, content)
        return blog.getHttpResponse()
    }


}


@Controller("/api/v1/user/{userId}/blogs")
class displayUserBlogApi @Inject constructor(
    private val blogController: BlogController
) {
    @Get("/")
    fun displayUserBlog(userId: Long):HttpResponse<Any>{
        val blog = blogController.displayUserBlog(userId)
        return blog.getHttpResponse()
    }
}

@Controller("/api/v1/user/{userId}/blog/{blogId")
class deleteUserBlogApi @Inject constructor(
    private val blogController: BlogController

){
    @Delete("/")
    fun deleteUserBlog(userId: Long,blogId: Long):HttpResponse<Any>{
        val blog = blogController.deleteUserBlog(userId, blogId)
        return blog.getHttpResponse()

    }}

@Controller("/api/v1/users")
class displayAllUser @Inject constructor(
    private val blogController: BlogController
) {

    @Get("/")
    fun displayAllUser():HttpResponse<Any>{
        val blog = blogController.displayAllUser()
        return blog.getHttpResponse()
    }
}
