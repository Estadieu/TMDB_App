import androidx.room.Query
import com.example.my_application1.ui.Api.Tmdbapi
import com.example.my_application1.ui.Model.TmdbMovieResult
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class FakeTmdbApi : Tmdbapi {
    val moshi: Moshi = Moshi.Builder().build()
    val jsonAdapter: JsonAdapter<TmdbMovieResult> = moshi.adapter(TmdbMovieResult::class.java)

    // Faux Json 
    val jsonresult = "{\"page\":1,\"results\":[{\"adult\":false, ... "

    override
    suspend fun lastmovies(@Query("api_key") api_key: String): TmdbMovieResult {
        val res = jsonAdapter.fromJson(jsonresult)
        if (res != null) return res
        else return TmdbMovieResult()
    }
}