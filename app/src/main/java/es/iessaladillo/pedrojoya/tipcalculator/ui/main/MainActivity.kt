package es.iessaladillo.pedrojoya.tipcalculator.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.tipcalculator.R
import es.iessaladillo.pedrojoya.tipcalculator.model.TipCalculator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initsViews()
    }

    private fun initsViews() {
        txtBill.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateAll(true)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        txtDiners.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateAll(false)
            }

        })
        txtPercentage.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateAll(true)
            }
        })

        btnResetTip.setOnClickListener { reset(true) }
        btnResetDiners.setOnClickListener { reset(false) }
    }

    private fun calculateAll(b: Boolean) {

        validarDatos()

        val tipCalculator = TipCalculator(
            txtBill.text.toString().toFloat(),
            txtPercentage.text.toString().toFloat(),
            txtDiners.text.toString().toInt()
        )

        if (b) {
            txtTip.setText(String.format("%.2f", tipCalculator.calculateTip()))
            txtTotal.setText(String.format("%.2f", tipCalculator.calculateTotal()))
            txtPerDiner.setText(String.format("%.2f", tipCalculator.calculatePerDiner()))
            txtPerDinerRounded.setText(
                String.format(
                    "%.2f",
                    tipCalculator.calculatePerDinerRounded()
                )
            )
        } else {
            txtPerDiner.setText(String.format("%.2f", tipCalculator.calculatePerDiner()))
            txtPerDinerRounded.setText(
                String.format(
                    "%.2f",
                    tipCalculator.calculatePerDinerRounded()
                )
            )
        }
    }

    private fun validarDatos() {

        if (txtBill.text.toString().equals(""))
            txtBill.setText(R.string.normalValueEditText)

        if (txtPercentage.text.toString().equals(""))
            txtPercentage.setText(R.string.percentage_defect_tip)

        if (txtDiners.text.toString().equals(""))
            txtDiners.setText(R.string.dinersDefectValue)

    }


    private fun reset(b: Boolean) {
        if (b) {
            txtBill.setText(R.string.normalValueEditText)
            txtPercentage.setText(R.string.percentage_defect_tip)
        } else {
            txtDiners.setText(R.string.dinersDefectValue)
            txtPerDiner.setText(R.string.normalValueEditText)
            txtPerDinerRounded.setText(R.string.number_diners)
        }

    }
}
