package com.marwatsoft.speedtestmaster.ui.main

import android.app.Application
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marwatsoft.speedtestmaster.R
import com.marwatsoft.speedtestmaster.helpers.SettingsHelper
import com.marwatsoft.speedtestmaster.network.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import pk.farimarwat.speedtest.Servers
import pk.farimarwat.speedtest.models.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val mConnectivityManager: ConnectivityManager,
    private val mContext:Application,
    private val mSettings:SettingsHelper
): ViewModel() {
    val mSTProvider by lazy { MutableLiveData<STProvider>(null) }
    val mListSTServer by lazy { MutableStateFlow<ApiStatus>(ApiStatus.Loading) }
    var mServers:List<STServer>? = null
    var mServerType = SettingsHelper.Servers_PUBLIC
   var mNetworkCallback:ConnectivityManager.NetworkCallback? = null
    val mSTServerSelected by lazy { MutableLiveData<STServer>(null) }

    val exp = CoroutineExceptionHandler { coroutineContext, throwable ->
        val msg = throwable.message
        msg?.let {
            mListSTServer.value = ApiStatus.Error(it)
        }
    }
    fun getServerType() = viewModelScope.launch(Dispatchers.IO + exp) {
        mSettings.servertype.collect{
            mServerType = it
            loadServers()
        }
    }
    fun loadServers() = viewModelScope.launch(Dispatchers.IO + exp) {

        val serversbuilder = Servers.Builder()
            .setServerType(mServerType)
            .build()
        serversbuilder.listServers(object : Servers.ServerStatusListener{
            override fun onLoading() {
                mListSTServer.value = ApiStatus.Loading
            }

            override fun onSuccess(response: ServersResponse) {
                mSTProvider.postValue(response.provider)
                response.servers?.let {
                    mListSTServer.value = ApiStatus.Success(it)
                }
            }

            override fun onError(error: String) {
                Timber.e(error)
                mListSTServer.value = ApiStatus.Error(
                    mContext.getString(R.string.error_internet)
                )
            }

        })
    }

    override fun onCleared() {
        super.onCleared()
        mNetworkCallback?.let {
            mConnectivityManager.unregisterNetworkCallback(it)
        }
    }
}