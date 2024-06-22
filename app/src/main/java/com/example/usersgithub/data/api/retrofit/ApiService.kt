import com.example.usersgithub.data.api.response.DetailUserResponse
import com.example.usersgithub.data.api.response.FollowingFollowersResponseItem
import com.example.usersgithub.data.api.response.GithubResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    fun getUsers(
        @Query("q") login: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String
    ): Call<List<FollowingFollowersResponseItem>>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String
    ): Call<List<FollowingFollowersResponseItem>>



}