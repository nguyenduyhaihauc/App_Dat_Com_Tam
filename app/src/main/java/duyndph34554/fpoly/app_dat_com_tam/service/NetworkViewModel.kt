package duyndph34554.fpoly.app_dat_com_tam.service

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkViewModel(application: Application) : AndroidViewModel(application) {
// view model nhưng là view model của hệ thống android
    private val _networkState = MutableLiveData<Boolean>()
    //state của mạng đc quan sát bởi live data
    val networkState: LiveData<Boolean> get() = _networkState
    // state lấy ở trên, dùng để thao tác với ứng dụng

    private val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
// instance của connect manager
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        // call back của network, sẽ gọi lại mỗi khi state thay đổi đến gán lại state
        override fun onAvailable(network: android.net.Network) {
            _networkState.postValue(true)
        }

        override fun onLost(network: android.net.Network) {
            _networkState.postValue(false)
        }
    }

    // lấy state của mạng trước khi vào ứng dụng, sau đó tùy biến notification
    init {
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkCallback)

        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        _networkState.postValue(networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true)
    }

    // hàm hủy đăng kí network callback
    override fun onCleared() {
        super.onCleared()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

}

