package com.example.toncontest

import com.example.toncontest.data.ton.client.tonClient
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        runBlocking {
            var acc = tonClient.getAccount("aGVscHVrcmFpbi50b24=")
        }
    }
}
