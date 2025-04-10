package com.dalmuina.utils

import android.content.Context
import android.content.res.Resources
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NetworkErrorToStringKtTest {

    private lateinit var context : Context

    @Before
    fun setUp(){
        context = ApplicationProvider.getApplicationContext<Context>()
    }

    @Test
    fun testTimeoutErrorToString() {
        val result = NetworkError.REQUEST_TIMEOUT.toString(context)
        assertThat(result).isEqualTo(context.getString(R.string.error_request_timeout))
    }

    @Test
    fun testTooManyRequestsErrorToString() {
        val result = NetworkError.TOO_MANY_REQUESTS.toString(context)
        assertThat(result).isEqualTo(context.getString(R.string.too_many_requests))
    }

    @Test
    fun testNoInternetErrorToString() {
        val result = NetworkError.NO_INTERNET.toString(context)
        assertThat(result).isEqualTo(context.getString(R.string.error_no_internet))
    }

    @Test
    fun testServerErrorToString() {
        val result = NetworkError.SERVER_ERROR.toString(context)
        assertThat(result).isEqualTo(context.getString(R.string.error_unknown))
    }

    @Test
    fun testSerializationErrorToString() {
        val result = NetworkError.SERIALIZATION.toString(context)
        assertThat(result).isEqualTo(context.getString(R.string.error_serialization))
    }

    @Test
    fun testUnknownErrorToString() {
        val result = NetworkError.UNKNOWN.toString(context)
        assertThat(result).isEqualTo(context.getString(R.string.error_unknown))
    }


    @Test(expected = Resources.NotFoundException::class)
    fun testMissingStringResourceThrowsException()  {
        val mockContext = mockk<Context>()
        every { mockContext.getString(any()) } throws Resources.NotFoundException()
        NetworkError.UNKNOWN.toString(mockContext)
    }

}
