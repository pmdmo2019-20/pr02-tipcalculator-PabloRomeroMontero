package es.iessaladillo.pedrojoya.tipcalculator.model

import java.lang.Math.ceil


// TipCalculator class. Its constructor receives bill, percentage and diners

class TipCalculator(bill: Float, percentage: Float, diners: Int) {
    val bill: Float
    val percentage: Float
    val diners: Int

    init {
        this.bill = bill
        this.percentage = percentage
        this.diners = diners
    }

    fun calculateTip(): Float{
       return (bill * (percentage / 100))
    }

    fun calculateTotal(): Float {
        return bill + calculateTip()
    }


    fun calculatePerDiner(): Float {
        if (diners != 0)
            return calculateTotal() / diners
        else
            return 0f

    }

    fun calculatePerDinerRounded(): Float{
        if (diners != 0){
            return ceil(calculatePerDiner().toDouble()).toFloat()
        }else{
            return 0f
        }
    }


}
