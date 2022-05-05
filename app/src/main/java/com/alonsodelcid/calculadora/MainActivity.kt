package com.alonsodelcid.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private var num1 = 0.0
    private var num2 = 0.0
    private var operacion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtResultado.text = "0"
        operacion = SIN_OPERACION

        btnUno.setOnClickListener { numberPressed("1") }
        btnDos.setOnClickListener { numberPressed("2") }
        btnTres.setOnClickListener { numberPressed("3") }
        btnCuatro.setOnClickListener { numberPressed("4") }
        btnCinco.setOnClickListener { numberPressed("5") }
        btnSeis.setOnClickListener { numberPressed("6") }
        btnSiete.setOnClickListener { numberPressed("7") }
        btnOcho.setOnClickListener { numberPressed("8") }
        btnNueve.setOnClickListener { numberPressed("9") }
        btnCero.setOnClickListener { numberPressed("0") }
        btnPunto.setOnClickListener { numberPressed(".") }

        btnBorrar.setOnClickListener { resetAll() }
        btnSalir.setOnClickListener { finish() }

        btnSumar.setOnClickListener { operationPressed(SUMA) }
        btnRestar.setOnClickListener { operationPressed(RESTA) }
        btnMultiplicar.setOnClickListener { operationPressed(MULTIPLICACION) }
        btnDividir.setOnClickListener { operationPressed(DIVISION) }
        btnPotencia.setOnClickListener { operationPressed(POTENCIACION) }
        btnRaiz.setOnClickListener { operationPressed(RADICACION) }
        btnPorcentaje.setOnClickListener { operationPressed(PORCENTAJE) }


        btnIgual.setOnClickListener { resolvePressed() }
    }

    private fun numberPressed(num: String){
        if(txtResultado.text == "0" && num != ".") {
            txtResultado.text = "$num"
        } else {
            txtResultado.text = "${txtResultado.text}$num"
        }

        if(operacion == SIN_OPERACION){
            num1 = txtResultado.text.toString().toDouble()
        } else {
            num2 = txtResultado.text.toString().toDouble()
        }
    }

    private fun operationPressed(operacion: Int){
        this.operacion = operacion
        num1 = txtResultado.text.toString().toDouble()

        txtResultado.text = "0"
    }

    private fun resolvePressed(){

        val result = when(operacion) {
            SUMA -> num1 + num2
            RESTA -> num1 - num2
            MULTIPLICACION -> num1 * num2
            DIVISION -> num1 / num2
            //otras operaciones
            POTENCIACION -> num1.pow(num2)
            RADICACION -> sqrt(num1)
            PORCENTAJE -> num1 / 100
            else -> 0
        }

        num1 = result as Double

        txtResultado.text = if("$result".endsWith(".0")) { "$result".replace(".0","") } else { "%.2f".format(result) }
    }

    private fun resetAll(){
        txtResultado.text = "0"
        num1 = 0.0
        num2 = 0.0
    }

    companion object {
        const val SUMA = 1
        const val RESTA = 2
        const val MULTIPLICACION = 3
        const val DIVISION = 4
        const val POTENCIACION = 5
        const val RADICACION = 6
        const val PORCENTAJE = 7
        const val SIN_OPERACION = 0
    }
}