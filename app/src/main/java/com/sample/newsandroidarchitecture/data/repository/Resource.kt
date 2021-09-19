package app.tubbr.data.repository

sealed class Resource<T>(
    val data: T? = null,
    val errorCode: Int? = null,
    val errorMessage: String? = null,
    var loading: Boolean = false
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Failed<T>(message: String) : Resource<T>(errorMessage = message)
    class Loading<T>(loading: Boolean) : Resource<T>(loading = loading)

}
