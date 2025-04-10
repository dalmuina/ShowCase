package com.dalmuina.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ResultKtTest {

    @Test
    fun `map success case`() {
        // Test the 'map' function with a Success result and a mapping function.
        // Verify that the mapping function is applied to the data and a Success result with the mapped data is returned.
        //Giver
        val initialData = "Test"
        val successResult: Result<String, DomainError> = Result.Success(initialData)
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
        // TODO implement test
    }

    @Test
    fun `map mapping function returns null`() {
        // Test the 'map' function with a Success result and a mapping function that returns null. 
        // Although the return type is non-nullable, this should still be tested if the compiler doesn't enforce it.
        // TODO implement test
    }

    @Test
    fun `asEmptyDataResult success case`() {
        // Test the 'asEmptyDataResult' function with a Success result. 
        // Verify that a Success result with Unit (empty data) is returned.
        // TODO implement test
    }

    @Test
    fun `asEmptyDataResult error case`() {
        // Test the 'asEmptyDataResult' function with an Error result. 
        // Verify that the original Error result is returned.
        // TODO implement test
    }

    @Test
    fun `onSuccess success case`() {
        // Test the 'onSuccess' function with a Success result. 
        // Verify that the action is executed with the data and the original Success result is returned.
        // TODO implement test
    }

    @Test
    fun `onSuccess error case`() {
        // Test the 'onSuccess' function with an Error result. 
        // Verify that the action is not executed and the original Error result is returned.
        // TODO implement test
    }

    @Test
    fun `onSuccess action throws exception`() {
        // Test the 'onSuccess' function with a Success result and an action that throws an exception. 
        // This tests exception handling within the 'onSuccess' function.  
        // It should not crash and should return the original Success result.
        // TODO implement test
    }

    @Test
    fun `onError error case`() {
        // Test the 'onError' function with an Error result. 
        // Verify that the action is executed with the error and the original Error result is returned.
        // TODO implement test
    }

    @Test
    fun `onError success case`() {
        // Test the 'onError' function with a Success result. 
        // Verify that the action is not executed and the original Success result is returned.
        // TODO implement test
    }

    @Test
    fun `onError action throws exception`() {
        // Test the 'onError' function with an Error result and an action that throws an exception. 
        // This tests exception handling within the 'onError' function. 
        // It should not crash and should return the original Error result.
        // TODO implement test
    }

    @Test
    fun `map with complex data type`() {
        // Test 'map' with a Success result containing a complex data type (e.g., a list or a custom class) and a mapping function that modifies it.  
        // Check if the modification is correctly reflected in the result.
        // TODO implement test
    }

    @Test
    fun `onError with different error subtypes`() {
        // Test 'onError' with Error results containing different subtypes of 'DomainError'. 
        // This ensures the action is correctly invoked regardless of the specific error.
        // TODO implement test
    }

    @Test
    fun `onSuccess with different data subtypes`() {
        // Test 'onSuccess' with Success results containing different subtypes of 'T'. 
        // This ensures the action is correctly invoked regardless of the specific data.
        // TODO implement test
    }

}
