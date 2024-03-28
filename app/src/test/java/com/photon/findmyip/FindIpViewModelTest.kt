package com.photon.findmyip

import com.photon.findmyip.model.Location
import com.photon.findmyip.repository.FindIpRepositoryImpl
import com.photon.findmyip.viewmodel.FindIpViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class FindIpViewModelTest {

    private lateinit var viewModel: FindIpViewModel

    @Mock
    lateinit var repo: FindIpRepositoryImpl

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun setUp() {
        viewModel = FindIpViewModel(repo)
    }

    @Test
    fun fetchDataSuccess() {
       val mainModelData = Location(
           "115.99.109.240",
           "115.99.108.0/23",
           "IPv4",
           "Bengaluru",
           "Karnataka",
           "KA",
           "IN",
           "India",
           "IN",
           "IND",
           "New Delhi",
           ".in",
           "AS",
           false,
           "560002",
           12.9634,
           77.5855,
           "Asia/Kolkata",
           "+0530",
           "+91",
           "INR",
           "Rupee",
           "en-IN,hi,bn,te,mr,ta,ur,gu,kn,ml,or,pa,as,bh,sat,ks,ne,sd,kok,doi,mni,sit,sa,fr,lus,inc",
           3287590.0,
           1352617328,
           "AS17488",
           "Hathway IP Over Cable Internet"
       )

    }
}