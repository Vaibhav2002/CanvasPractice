package dev.vaibhav.canvaspractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import dev.vaibhav.canvaspractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var colorPickerDialog: MaterialColorPickerDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpColorPickerDialog()
        initListeners()
    }

    private fun initListeners() = binding.apply {
        selectColorBtn.setOnClickListener { colorPickerDialog.show() }
        slider.addOnChangeListener { slider, value, fromUser ->
            squircleView.setBrushWidth(value.times(100))
        }
    }

    private fun setUpColorPickerDialog() {
        colorPickerDialog = MaterialColorPickerDialog.Builder(this)
            .setColorShape(ColorShape.SQAURE)
            .setColorListener { color: Int, coloHex: String ->
                binding.squircleView.setBrushColor(color)
            }
            .build()
    }
}
