const val MAX_LIMIT_IN_MONTH_CARDS = 600_000
const val MAX_LIMIT_IN_DAY_CARDS = 150_000
const val MAX_LIMIT_IN_MONTH_VKPAY = 40_000
const val MAX_LIMIT_IN_DAY_VKPAY = 15_000
const val LIMIT_IN_MONTH_WITHOUT_COMMISSION = 75_000
const val COMMISSION_PERCENT_VISA_MIR = 0.75
const val COMMISSION_PERCENT_MASTERCARD_MAESTRO = 0.6
const val MINIMAL_COMMISSION_VISA_MIR = 35
const val ADDITIONAL_COMMISSION_MASTERCARD_MAESTRO = 20

fun main() {
    val type = "Mastercard"
    val prevTransfersInMonth = 70_000
    val amount = 6_000

    if (checkLimitss(amount, type, prevTransfersInMonth))
        println("При переводе $amount руб. комиссия составит ${calcCommission(amount, type, prevTransfersInMonth)} руб.")
}

fun calcCommission(amount: Int, type: String = "VK Pay", prevTransfersInMonth: Int = 0, prevTransfersInDay: Int = 0): Int {
    if (type == "Mastercard" || type == "Maestro")
        return if ((prevTransfersInMonth+amount) > LIMIT_IN_MONTH_WITHOUT_COMMISSION) (amount * COMMISSION_PERCENT_MASTERCARD_MAESTRO / 100 + ADDITIONAL_COMMISSION_MASTERCARD_MAESTRO).toInt() else 0
    if (type == "Visa" || type == "Мир") {
            val commissionTotal = amount * COMMISSION_PERCENT_VISA_MIR / 100
            return if (commissionTotal < MINIMAL_COMMISSION_VISA_MIR) MINIMAL_COMMISSION_VISA_MIR else (commissionTotal).toInt()
        }
    return 0
}

fun checkLimits(amount: Int, type: String = "VK Pay", prevTransfersInMonth: Int = 0, prevTransfersInDay: Int = 0): Boolean {
    if (type == "VK Pay")
        when {
            (prevTransfersInMonth+amount) > MAX_LIMIT_IN_MONTH_VKPAY -> {
                println("Вы превышаете лимит $MAX_LIMIT_IN_MONTH_VKPAY в месяц на ${(prevTransfersInMonth+amount)-MAX_LIMIT_IN_MONTH_VKPAY}")
                return false
            }
            (prevTransfersInDay+amount) > MAX_LIMIT_IN_DAY_VKPAY -> {
                println("Вы превышаете лимит $MAX_LIMIT_IN_DAY_VKPAY в сутки на ${(prevTransfersInDay+amount)-MAX_LIMIT_IN_DAY_VKPAY}")
                return false
            }
        }
    else
        when {
            (prevTransfersInMonth+amount) > MAX_LIMIT_IN_MONTH_CARDS -> {
                println("Вы превышаете лимит $MAX_LIMIT_IN_MONTH_CARDS в месяц на ${(prevTransfersInMonth+amount)-MAX_LIMIT_IN_MONTH_CARDS}")
                return false
            }
            (prevTransfersInDay+amount) > MAX_LIMIT_IN_DAY_CARDS -> {
                println("Вы превышаете лимит $MAX_LIMIT_IN_DAY_CARDS в сутки на ${(prevTransfersInDay+amount)-MAX_LIMIT_IN_DAY_CARDS}")
                return false
            }
        }
    return true
}