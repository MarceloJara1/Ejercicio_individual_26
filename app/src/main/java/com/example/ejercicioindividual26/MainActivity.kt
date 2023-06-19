package com.example.ejercicioindividual26

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.ejercicioindividual26.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var saldo: Double = 15000.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)

            when(selectedRadioButton){
                binding.radioSaldo->{
                    mostrarSaldo()
                    ocultarTextField()
                }
                binding.radioDeposito ->{
                    mostrarSaldo()
                    mostrarTextFieldDeposito()
                }
                binding.radioRetiro ->{
                    mostrarSaldo()
                    mostrarTextFieldRetiro()
                }

            }
        }

        binding.btnOk.setOnClickListener{
            val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)

            when(selectedRadioButton){

                binding.radioDeposito-> {
                    depositar()
                    mostrarSaldo()
                }
                binding.radioRetiro->{
                    retirar()
                    mostrarSaldo()}
                binding.radioSalir-> finish()

            }
        }
    }

    private fun mostrarSaldo(){
        binding.saldoContainer.visibility = View.VISIBLE
        binding.saldoValue.text = saldo.toString()
    }

    private fun mostrarTextFieldDeposito(){
        binding.textFieldDeposito.visibility = View.VISIBLE
        binding.textFieldRetiro.visibility = View.GONE
    }

    private fun mostrarTextFieldRetiro(){
        binding.textFieldRetiro.visibility = View.VISIBLE
        binding.textFieldDeposito.visibility = View.GONE
    }

    private fun ocultarTextField(){
        binding.textFieldDeposito.visibility = View.GONE
        binding.textFieldRetiro.visibility = View.GONE
    }

    private fun depositar(){
        binding.textFieldDeposito.editText?.text.toString().toDoubleOrNull()?.let { monto ->
            saldo += monto }
    }

    private fun retirar(){
        binding.textFieldRetiro.editText?.text.toString().toDoubleOrNull()?.let { monto ->
            if (monto <= saldo){
                saldo -= monto
            }else{
                Toast.makeText(this, "No se puede retirar un saldo mayor al disponible", Toast.LENGTH_SHORT).show()
            }
        }
    }
}