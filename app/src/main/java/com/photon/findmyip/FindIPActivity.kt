package com.photon.findmyip

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.photon.findmyip.composables.LocationComposable
import com.photon.findmyip.composables.ShowError
import com.photon.findmyip.composables.ShowProgress
import com.photon.findmyip.ui.theme.FindMyIPTheme
import com.photon.findmyip.viewmodel.FindIpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindIPActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: FindIpViewModel by viewModels()
        setContent {
            FindMyIPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isNetworkAvailable(applicationContext)) {
                        LaunchedEffect(key1 = Unit) {
                            viewModel.fetchData()
                        }

                        val uiState = viewModel.state.value

                        println("==>> progress : ${uiState is FindIpViewModel.UiState.Loading}")

                        when (uiState) {
                            is FindIpViewModel.UiState.Loading -> ShowProgress()
                            is FindIpViewModel.UiState.Error -> ShowError(errorMsg = uiState.error)
                            is FindIpViewModel.UiState.Success -> uiState.data?.let {
                                LocationComposable(
                                    data = it
                                )
                            }
                        }

                    } else {
                        ShowError(errorMsg = "No network")
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FindMyIPTheme {
        Greeting("Android")
    }
}

private fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            ?: return false
    return when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}