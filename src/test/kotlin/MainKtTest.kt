import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calcCommission_MastercardBeforeLimitMonth() {
        val type = "Mastercard"
        val prevTransfersInMonth = 50_000
        val prevTransfersInDay = 1_000
        val amount = 6_000

        val result = calcCommission(amount, type, prevTransfersInMonth, prevTransfersInDay)
        assertEquals(10, result)
    }

    @Test
    fun calcCommission_MaestroBeforeLimitMonth() {
        val type = "Maestro"
        val prevTransfersInMonth = 50_000
        val prevTransfersInDay = 50_000
        val amount = 6_000

        val result = calcCommission(amount, type, prevTransfersInMonth, prevTransfersInDay)
        assertEquals(20, result)
    }

    @Test
    fun calcCommission_MastercardAfterLimitMonth() {
        val type = "Mastercard"
        val prevTransfersInMonth = 70_000
        val prevTransfersInDay = 1_000
        val amount = 6_000

        val result = calcCommission(amount, type, prevTransfersInMonth, prevTransfersInDay)
        assertEquals(56, result)
    }

    @Test
    fun calcCommission_MaestroAfterLimitMonth() {
        val type = "Maestro"
        val prevTransfersInMonth = 70_000
        val prevTransfersInDay = 50_000
        val amount = 6_000

        val result = calcCommission(amount, type, prevTransfersInMonth, prevTransfersInDay)
        assertEquals(56, result)
    }

    @Test
    fun calcCommission_VisaMinimalComms() {
        val type = "Visa"
        val prevTransfersInMonth = 70_000
        val prevTransfersInDay = 1_000
        val amount = 3_000

        val result = calcCommission(amount, type, prevTransfersInMonth, prevTransfersInDay)
        assertEquals(35, result)
    }

    @Test
    fun calcCommission_MirMinimalComms() {
        val type = "Мир"
        val prevTransfersInMonth = 70_000
        val prevTransfersInDay = 1_000
        val amount = 3_000

        val result = calcCommission(amount, type, prevTransfersInMonth, prevTransfersInDay)
        assertEquals(35, result)
    }

    @Test
    fun calcCommission_VisaComms() {
        val type = "Visa"
        val amount = 6_000
        val prevTransfersInMonth = 70_000
        val prevTransfersInDay = 1_000

        val result = calcCommission(amount, type, prevTransfersInMonth, prevTransfersInDay)
        assertEquals(45, result)
    }

    @Test
    fun calcCommission_MirComms() {
        val type = "Мир"
        val prevTransfersInMonth = 70_000
        val prevTransfersInDay = 1_000
        val amount = 5_000

        val result = calcCommission(amount, type, prevTransfersInMonth, prevTransfersInDay)
        assertEquals(37, result)
    }

    @Test
    fun calcCommission_VKPay() {
        val type = "VK Pay"
        val prevTransfersInMonth = 1_000
        val prevTransfersInDay = 1_000
        val amount = 2_000

        val result = calcCommission(amount, type, prevTransfersInMonth, prevTransfersInDay)
        assertEquals(0, result)
    }

    @Test
    fun calcCommission_Creditcard() {
        val type = "Creditcard"
        val prevTransfersInMonth = 70_000
        val prevTransfersInDay = 1_000
        val amount = 10_000

        val result = calcCommission(amount, type, prevTransfersInMonth, prevTransfersInDay)
        assertEquals(0, result)
    }

    @Test
    fun calcCommission_EmptyType() {
        val type = ""
        val prevTransfersInMonth = 70_000
        val prevTransfersInDay = 1_000
        val amount = 20_000

        val result = calcCommission(amount, type, prevTransfersInMonth, prevTransfersInDay)
        assertEquals(0, result)
    }
}