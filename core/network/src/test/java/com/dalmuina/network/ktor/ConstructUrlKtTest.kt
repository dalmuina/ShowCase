package com.dalmuina.network.ktor

import org.junit.Test

class ConstructUrlKtTest {

    @Test
    fun `URL already contains BASE URL`() {
        // Test that the function returns the original URL when it already contains the BuildConfig.BASE_URL.
        // TODO implement test
    }

    @Test
    fun `URL starts with a slash`() {
        // Test that the function correctly prepends BuildConfig.BASE_URL and 
        // removes the leading slash when the URL starts with a slash.
        // TODO implement test
    }

    @Test
    fun `URL doesn t start with a slash`() {
        // Test that the function correctly prepends BuildConfig.BASE_URL 
        // when the URL does not start with a slash.
        // TODO implement test
    }

    @Test
    fun `Empty URL`() {
        // Test the behavior when an empty string is passed as the URL. 
        // Should it prepend BuildConfig.BASE_URL or handle it in a specific way?
        // TODO implement test
    }

    @Test
    fun `URL is just a slash`() {
        // Test the behavior when the URL is just a single slash character. 
        // Should it prepend BuildConfig.BASE_URL or return BuildConfig.BASE_URL itself?
        // TODO implement test
    }

    @Test
    fun `BASE URL is empty`() {
        // Edge case: Check the function's behavior if BuildConfig.BASE_URL is an empty string. 
        //  It might need special handling to avoid unexpected results.
        // TODO implement test
    }

    @Test
    fun `BASE URL ends with a slash and URL starts with a slash`() {
        // Edge case: Test URL construction when BuildConfig.BASE_URL ends with a slash 
        // and the provided URL also starts with a slash, 
        // ensuring no double slashes occur in the final URL.
        // TODO implement test
    }

    @Test
    fun `BASE URL ends without slash and URL starts without slash`() {
        //Edge case: check if URL is constructed as BASE_URL + URL when BASE_URL ends 
        // without a slash and the provided URL also starts without a slash.
        // TODO implement test
    }

    @Test
    fun `Long URL`() {
        // Test with a very long URL to check for any potential buffer overflow issues 
        // or performance implications with large strings.
        // TODO implement test
    }

}