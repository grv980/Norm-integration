package db.blogs

import blogs.core.models.Blog
import blogs.core.models.User

import blogs.core.services.BlogRepository
import java.sql.Timestamp
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class BlogRepositoryImpl(
    @Inject private val dataSource: DataSource
) : BlogRepository {
    override fun getAll(): List<Blog> {
        val query = dataSource.connection.prepareStatement("SELECT * FROM blogs")
        val result = query.executeQuery()
        val blogs = mutableListOf<Blog>()

        while (result.next()) {
            val id = result.getInt("id").toLong()
            val name = result.getString("title") as String
            val content = result.getString("content") as String
            val created_at = result.getString("created_at") as Timestamp
            val updated_at =result.getString("updated_at") as Timestamp
            val user_id = result.getLong("user_id")
            val published = result.getBoolean("published")




            blogs.add(Blog(id, name, content,created_at,updated_at,user_id,published))
        }
        return blogs
    }



        override fun fetchBlogById(blogId: Long): List<Blog> {


            val query = dataSource.connection.prepareStatement("SELECT * FROM blogs where id=${blogId}")
            val result = query.executeQuery()
            val blogs = mutableListOf<Blog>()

            while (result.next()) {
                val id = result.getInt("id").toLong()
                val name = result.getString("title") as String
                val content = result.getString("content") as String
                val createdAt = result.getTimestamp("created_At") as Timestamp
                val updatedAt = result.getTimestamp("updated_at") as Timestamp
                val userId = result.getLong("user_id")
                val isPublish = result.getBoolean("is_publish")
                blogs.add(Blog(id, name, content, createdAt, updatedAt, userId, isPublish))

            }
            return blogs
        }


        override fun createUser(username: String): List<User> {
            val query =
                dataSource.connection.prepareStatement("INSERT INTO users(id,name,created_at,updated_at) VALUES(?,?,?)")

            query.setString(1, username)
            query.setTimestamp(2, Timestamp(System.currentTimeMillis()))
            query.setTimestamp(3, Timestamp(System.currentTimeMillis()))
            query.executeUpdate()

            val displayData = dataSource.connection.prepareStatement("SELECT * FROM users")
            val result = query.executeQuery()
            val userdata = mutableListOf<User>()

            while (result.next()) {
                val id = result.getInt("id").toLong()
                val name = result.getString("title") as String
                val createdAT = result.getTimestamp("created_at") as Timestamp
                val updatedAt = result.getTimestamp("updated_at") as Timestamp

                userdata.add(User((id), name, createdAT, updatedAt))
            }
            return userdata
        }

        override fun createUserBlog(userId: Long, title: String, content: String): List<Blog> {
            val UserQuery = dataSource.connection.prepareStatement("SELECT * FROM users WHERE id =${userId}")
            val result = UserQuery.executeQuery()
            val blog = mutableListOf<Blog>()

            if (result.next()) {
                val insertQuery =
                    dataSource.connection.prepareStatement("INSERT INTO blogs(title,content,created_at,updated_at,user_id)VALUES(?,?,?,?,?)")
                insertQuery.setString(1, title)
                insertQuery.setString(2, content)
                insertQuery.setTimestamp(3, Timestamp(System.currentTimeMillis()))
                insertQuery.setTimestamp(4, Timestamp(System.currentTimeMillis()))
                insertQuery.setLong(5, userId)
                insertQuery.executeUpdate()
                val updateUser =
                    dataSource.connection.prepareStatement("UPDATE users SET updated_at=? WHERE id =${userId}")
                updateUser.setTimestamp(1, Timestamp(System.currentTimeMillis()))
                updateUser.executeUpdate()
                val displayInsertData = dataSource.connection.prepareStatement("SELECT * FROM blogs ORDER BY id")
                val result = displayInsertData.executeQuery()


                while (result.next()) {
                    val id = result.getInt("id").toLong()
                    val isPublish = result.getBoolean("is_publish")
                    blog.add(
                        Blog(
                            id, title, content, Timestamp(System.currentTimeMillis()),
                            Timestamp(System.currentTimeMillis()), userId, isPublish
                        )
                    )
                }

            }
            return blog

        }

        override fun updateUserBlog(userId: Long, title: String, content: String): List<Blog> {
            val displayBlog = dataSource.connection.prepareStatement("SELECT * FROM blogs where user_id =$userId")
            val result = displayBlog.executeQuery()
            val blog = mutableListOf<Blog>()
            if (result.next()) {
                val isPublish = true

                val updateQuery =
                    dataSource.connection.prepareStatement("UPDATE blogs SET title=?,content=?,is_publish=?,updated_at=?,where id = ${userId}")
                updateQuery.setString(1, title)
                updateQuery.setString(2, content)
                updateQuery.setBoolean(3, isPublish)
                updateQuery.setTimestamp(4, Timestamp(System.currentTimeMillis()))
                val result = updateQuery.executeUpdate()
                return updateUserBlog(userId, title, content)

            }
            return blog
        }

        override fun deleteUserBlog(userId: Long, blogId: Long): List<Blog> {
            val displayBlog = dataSource.connection.prepareStatement("SELECT * FROM blogs where user_id = $userId")
            val result = displayBlog.executeQuery()
            if (result.next()) {
                val updateQuery = dataSource.connection.prepareStatement("DELETE FROM blogs where id = ? and user_id=?")
                updateQuery.setLong(1, blogId)
                updateQuery.setLong(2, userId)
                val res = updateQuery.executeUpdate()
                return fetchBlogById(blogId)
            } else {
                val blog = mutableListOf<Blog>()
                blog.add(
                    Blog(
                        0,
                        "",
                        "",
                        Timestamp(System.currentTimeMillis()),
                        Timestamp(System.currentTimeMillis()),
                        0,
                        false
                    )
                )
                return blog

            }


        }

        override fun displayUserBlog(userId: Long): List<Blog> {
            val displayBlog = dataSource.connection.prepareStatement("SELECT * FROM blogs where id = ${userId}")
            val result = displayBlog.executeQuery()
            val blogs = mutableListOf<Blog>()
            while (result.next()) {
                val id = result.getInt("id").toLong()
                val name = result.getString("title") as String
                val content = result.getString("content") as String
                val createdAt = result.getTimestamp("created_At") as Timestamp
                val updatedAt = result.getTimestamp("updated_at") as Timestamp
                val userId = result.getLong("user_id")
                val isPublish = result.getBoolean("is_publish")
                blogs.add(Blog(id, name, content, createdAt, updatedAt, userId, isPublish))


            }
            return blogs
        }

        override fun getUser(userId: Long): List<User> {
            val displayUser = dataSource.connection.prepareStatement("SELECT * FROM users where id = ${userId}")
            val result = displayUser.executeQuery()
            val user = mutableListOf<User>()
            while (result.next()) {
                val id = result.getInt("id").toLong()
                val name = result.getString("title") as String
                val createdAt = result.getTimestamp("created_At") as Timestamp
                val updatedAt = result.getTimestamp("updated_at") as Timestamp

                user.add(User(id, name, createdAt, updatedAt))
            }
            return user
        }

        override fun displayAllUser(): List<User> {
            val displayUser = dataSource.connection.prepareStatement("SELECT * FROM users")
            val result = displayUser.executeQuery()
            val user = mutableListOf<User>()
            while (result.next()) {
                val id = result.getInt("id").toLong()
                val name = result.getString("title") as String
                val createdAt = result.getTimestamp("created_At") as Timestamp
                val updatedAt = result.getTimestamp("updated_at") as Timestamp

                user.add(User(id, name, createdAt, updatedAt))
            }
            return user
        }
    }



