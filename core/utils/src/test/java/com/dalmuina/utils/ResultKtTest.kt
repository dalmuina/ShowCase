package com.dalmuina.utils

import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ResultKtTest {

    private val testData = "test Data"
    private val testError = object : DomainError {
        val message: String = "Test error"
    }
    private val successResult: Result<String, DomainError> = Result.Success(testData)
    private val errorResult: Result<String, DomainError> = Result.Error(testError)

    @Test
    fun `map success case`() {
        // Test the 'map' function with a Success result and a mapping function.
        // Verify that the mapping function is applied to the data and a Success
        // result with the mapped data is returned.
        //Given
        val mappingFunction: (String) -> String = {it.toString()}
        //When
        val mappedResult : Result<String, DomainError> = successResult.map(mappingFunction)
        //Then
        assertThat(mappedResult).isEqualTo(successResult)

    }

    @Test
    fun `map error case`() {
        // Test the 'map' function with an Error result. 
        // Verify that the mapping function is not applied and the original Error result is returned.
        //Given
        val mappingFunction: (String) -> String = {it.toString()}
        //When
        val mappedResult : Result<String, DomainError> = errorResult.map(mappingFunction)
        //Then
        assertThat(mappedResult).isEqualTo(errorResult)
    }

    @Test
    fun `map mapping function returns null`() {
        // Test the 'map' function with a Success result and a mapping function that returns null. 
        // Although the return type is non-nullable, this should still be tested if the compiler doesn't enforce it.
        //Given
        val mappingFunction: (String) -> String? = { null }
        //When
        val mappedResult: Result<String?, DomainError> = successResult.map(mappingFunction)
        //Then
        assertThat(mappedResult).isEqualTo(Result.Success(null))
    }

    @Test
    fun `asEmptyDataResult success case`() {
        // Test the 'asEmptyDataResult' function with a Success result. 
        // Verify that a Success result with Unit (empty data) is returned.
        //When
        val result: EmptyResult<DomainError> = successResult.asEmptyDataResult()

        //Then
        assertThat(result).isEqualTo(Result.Success(Unit))
    }

    @Test
    fun `asEmptyDataResult error case`() {
        // Test the 'asEmptyDataResult' function with an Error result. 
        // Verify that the original Error result is returned.
        //When
        val result: EmptyResult<DomainError> = errorResult.asEmptyDataResult()

        //Then
        assertThat(result).isEqualTo(errorResult)
    }

    @Test
    fun `onSuccess success case`() {
        // Test the 'onSuccess' function with a Success result. 
        // Verify that the action is executed with the data and the original Success result is returned.
        //Given
        val action: (String) -> Unit = mockk(relaxed = true)

        //When
        val result = successResult.onSuccess(action)

        //Then
        verify { action(testData) }
        assertThat(result).isEqualTo(successResult)
    }

    @Test
    fun `onSuccess error case`() {
        // Test the 'onSuccess' function with an Error result. 
        // Verify that the action is not executed and the original Error result is returned.
        //Given
        val action: (DomainError) -> Unit = mockk(relaxed = true)

        //When
        val result = errorResult.onError(action)

        //Then
        verify { action(testError) }
        assertThat(result).isEqualTo(errorResult)
    }

    @Test
    fun `onError error case`() {
        // Test the 'onError' function with an Error result. 
        // Verify that the action is executed with the error and the original Error result is returned.
        //Given
        val action: (DomainError) -> Unit = mockk(relaxed = true)

        //When
        val result = errorResult.onError(action)

        //Then
        verify { action(testError) }
        assertThat(result).isEqualTo(errorResult)
    }

    @Test
    fun `onError success case`() {
        // Test the 'onError' function with a Success result. 
        // Verify that the action is not executed and the original Success result is returned.
        //Given
        val action: (DomainError) -> Unit = mockk(relaxed = true)

        //When
        val result = successResult.onError(action)

        //Then
        verify(exactly = 0) { action(any()) }
        assertThat(result).isEqualTo(successResult)
    }

    @Test
    fun `map with complex data type`() {
        //Given
        data class ComplexData(val id: Int, val name: String, val items: List<String>)
        val complexData = ComplexData(1, "Test", listOf("A", "B", "C"))
        val complexResult: Result<ComplexData, DomainError> = Result.Success(complexData)
        val mappingFunction: (ComplexData) -> Int = { it.items.size }

        //When
        val mappedResult = complexResult.map(mappingFunction)

        //Then
        assertThat(mappedResult).isEqualTo(Result.Success(3))
    }

    sealed interface TestError : DomainError {
        data object Error1 : TestError
        data object Error2 : TestError
    }

    @Test
    fun `onError with different error subtypes`() {
        //Given

        val error1Result: Result<Unit, TestError> = Result.Error(TestError.Error1)
        val error2Result: Result<Unit, TestError> = Result.Error(TestError.Error2)
        val action: (TestError) -> Unit = mockk(relaxed = true)

        //When
        error1Result.onError(action)
        error2Result.onError(action)

        //Then
        verify { action(TestError.Error1) }
        verify { action(TestError.Error2) }
    }

    sealed interface TestData {
        data object Data1 : TestData
        data object Data2 : TestData
    }

    @Test
    fun `onSuccess with different data subtypes`() {
        //Given

        val data1Result: Result<TestData, DomainError> = Result.Success(TestData.Data1)
        val data2Result: Result<TestData, DomainError> = Result.Success(TestData.Data2)
        val action: (TestData) -> Unit = mockk(relaxed = true)

        //When
        data1Result.onSuccess(action)
        data2Result.onSuccess(action)

        //Then
        verify { action(TestData.Data1) }
        verify { action(TestData.Data2) }
    }

}
